package at.tetris4j.controller;

import java.util.Timer;
import java.util.TimerTask;

import org.jnativehook.GlobalScreen;

import at.tetris4j.model.IModel;
import at.tetris4j.view.IView;
import at.tetris4j.view.TetrisView;

public class GameController implements IController{

	private static final int REFRESH_RATE = 100;
	
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
	public void leftPressed() {
		model.moveLeft();
	}

	@Override
	public void rightPressed() {
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
	public void stopPressed() {
		this.gameRunning = false;
		GlobalScreen.unregisterNativeHook();
	}
	
	@Override
	public void pausePressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resumePressed() {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void wPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void singleplayerPressed() {
		startSingleplayerMode();
	}

	@Override
	public void multiplayerPressed() {
		view.showNetworkInfoScreen();
	}

	@Override
	public void startSingleplayerMode() {
		final Timer updateTimer = new Timer();
		updateTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				model.updateGame();
				
				if (!gameRunning) {
					//TODO cancel is not working correctly
					updateTimer.cancel();
				}
			}
		}, REFRESH_RATE, REFRESH_RATE);
		
		
		new Thread(new Runnable() {
			//TODO extract the thread out of this method
			@Override
			public void run() {
				
				while (gameRunning) {
					
					view.updateScreen(model);
					
					try {
						Thread.sleep(REFRESH_RATE);
					} catch (InterruptedException e) {
						gameRunning = false;
					}
				}
			}
			
		}).start();
	}

	@Override
	public void startMultiplayerMode() {
		
	}

	@Override
	public void serverPressed() {
		
	}

	@Override
	public void hostPressed() {
		
	}

}
