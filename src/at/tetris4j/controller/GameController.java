package at.tetris4j.controller;

import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

import at.tetris4j.model.IModel;
import at.tetris4j.view.IView;
import at.tetris4j.view.TetrisView;

public class GameController implements IController{

	private static final int REFRESH_TIME = 100;
	
	private static final int DEFAULT_STEP_TIME = 500;
	
	private final IModel model;
	private final IView view;
	
	private boolean gameRunning;
	
	public GameController(IModel model) {
		
		this.model = model;
		
		this.view = new TetrisView(this);
		
		this.gameRunning = false;
	}
	
	@Override
	public void startGame() {
		this.gameRunning = true;
		
		view.showStartScreen();
	}
	
	@Override
	public void moveLeft() {
		model.moveLeft();
	}

	@Override
	public void moveRight() {
		model.moveRight();		
	}

	@Override
	public void upPressed() {
		model.turn();
	}

	@Override
	public void downPressed() {
		model.moveDown();		
	}

	@Override
	public void stopGame() {
		this.gameRunning = false;
		view.showGoodbyeScreen();
	}
	
	@Override
	public void pauseGame() {
		//TODO implement pause
	}

	@Override
	public void resumeGame() {
		//TODO implement resume
	}

	@Override
	public void startSinglePlayerMode() {

		new Thread(new GameLoop()).start();
		
		new Thread(new RenderLoop()).start();
	}
	
	@Override
	public void multiplayerPressed() {
		view.showNetworkInfoScreen();
	}
	
	private void startMultiplayer() {

		view.clear();
		
		final Timer updateTimer = new Timer();
		updateTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if(model.isConnectionEstablished()){
					model.updateGame();
					
					if (model.isGameOver()) {
						view.showGoodbyeScreen();
						gameRunning = false;
					}
				}
				if (!gameRunning) {
					updateTimer.cancel();
				}
			}
		}, DEFAULT_STEP_TIME, DEFAULT_STEP_TIME);
		
		
		new Thread(new RenderLoop()).start();
		
		
	}

	@Override
	public void startMultiplayerMode(InetAddress ip) {
		model.startNewMultiplayerGame(ip);
		startMultiplayer();
	}

	@Override
	public void startMultiplayerMode() {
		model.startNewMultiplayerGame();
		startMultiplayer();
	}
	
	private class GameLoop implements Runnable {
		
		@Override
		public void run() {
			
			while (gameRunning) {
				
				model.updateGame();

				if (model.isGameOver()) {
					view.showGameOverScreen();
					gameRunning = false;
				}
				
				try {
					Thread.sleep(DEFAULT_STEP_TIME);
				} catch (InterruptedException e) {
					gameRunning = false;
				}
				
			}
		}
	}
	
	private class RenderLoop implements Runnable {
		
		@Override
		public void run() {
			
			while (gameRunning) {
				
				view.updateScreen(model);
				
				try {
					Thread.sleep(REFRESH_TIME);
				} catch (InterruptedException e) {
					gameRunning = false;
				}
				
			}
		}
	}
}
