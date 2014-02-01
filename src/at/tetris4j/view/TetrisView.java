package at.tetris4j.view;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.AnsiConsole;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import at.tetris4j.controller.IController;
import at.tetris4j.helpers.Utils;
import at.tetris4j.model.IModel;
import at.tetris4j.model.components.BoardPresentation;
import at.tetris4j.view.utils.GameState;
import at.tetris4j.view.utils.GlobalKeyListener;
import at.tetris4j.view.utils.TetrisKey;

public class TetrisView implements IConsoleView {

	private static final String IP_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
											+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
											+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
											+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	private final IController controller;
	private GameState gameState = GameState.MainMenu;

	public TetrisView(IController controller) {
		this.controller = controller;
		GlobalScreen.getInstance().addNativeKeyListener(new GlobalKeyListener(this));
	}

	@Override
	public void showStartScreen() {
		gameState = GameState.MainMenu;
		AnsiConsole.out.print(Ansi.ansi().cursor(0, 0));
		AnsiConsole.out.print(Ansi.ansi().eraseScreen());

		String[] heading = Utils.readLines("/heading.txt");
		for (String s : heading) {
			AnsiConsole.out.println(Ansi.ansi().fg(Color.YELLOW).a("\t" + s).reset());
		}
		AnsiConsole.out.println();
		AnsiConsole.out.println();
		AnsiConsole.out.println();

		String[] menu = Utils.readLines("/menu.txt");
		for (String s : menu) {
			AnsiConsole.out.println(Ansi.ansi().fg(Color.BLUE).a(s).reset());
		}
	}

	@Override
	public void keyPressed(TetrisKey key) {
		switch (key) {
		case UP:
			if (gameState == GameState.SinglePlayer
					|| gameState == GameState.Multiplayer)
				controller.upPressed();
			break;
		case DOWN:
			if (gameState == GameState.SinglePlayer
					|| gameState == GameState.Multiplayer)
				controller.downPressed();
			break;
		case LEFT:
			if (gameState == GameState.SinglePlayer
					|| gameState == GameState.Multiplayer)
				controller.moveLeft();
			break;
		case RIGHT:
			if (gameState == GameState.SinglePlayer
					|| gameState == GameState.Multiplayer)
				controller.moveRight();
			break;
		case PAUSE:
			controller.pauseGame();
			break;
		case RESUME:
			controller.resumeGame();
			break;
		case STOP:
			controller.stopGame();
			break;
		case FUNCTIONKEY_1:
			if (gameState == GameState.MainMenu) {
				gameState = GameState.SinglePlayer;
				controller.startSinglePlayerMode();
				AnsiConsole.out.print(Ansi.ansi().eraseScreen());
			} else if (gameState == GameState.NetworkInput) {
				gameState = GameState.Multiplayer;
				controller.startMultiplayerMode();
			}
			break;
		case FUNCTIONKEY_2:
			if (gameState == GameState.MainMenu) {
				gameState = GameState.Multiplayer;
				controller.multiplayerPressed();
			} else if (gameState == GameState.NetworkInput) {
				getIpFromUser();
			}
			break;
		default:
			break;
		}
	}

	private void getIpFromUser() {
		GlobalScreen.unregisterNativeHook();
		Scanner scanner = new Scanner(System.in);
		
		String ip;
		do {
			System.out.println();
			System.out.println("PLEASE TYPE IN YOUR OPPONENTS IP ADDRESS: ");
			ip = scanner.next();
		}			
		while(!ip.matches(IP_PATTERN));
		
		try {
			InetAddress ipAddress = InetAddress.getByName(ip);
			System.out.println(ipAddress.toString());
			controller.startMultiplayerMode(ipAddress);
			this.gameState = GameState.Multiplayer;
		} catch (UnknownHostException e) {
			System.out.println("could not find host " + e);
			controller.stopGame();
		} finally {
			scanner.close();
			registerNativeHook();
		}
	}

	@Override
	public void updateScreen(IModel model) {
		if (gameState == GameState.SinglePlayer) {
			AnsiConsole.out.print(Ansi.ansi().cursor(0, 0));

			BoardPresentation boardPresentation = model.getGameBoard();

			AnsiConsole.out.print(boardPresentation.getOutput());
		} else if (gameState == GameState.Multiplayer) {
			AnsiConsole.out.print(Ansi.ansi().cursor(0, 0));
			AnsiConsole.out.print(Ansi.ansi().eraseScreen());
			BoardPresentation player1BoardPresentation = model.getGameBoard();
			AnsiConsole.out.print(player1BoardPresentation.getOutput());
			AnsiConsole.out.print(Ansi.ansi().cursor(0, 50));
			BoardPresentation player2BoardPresentation = model.getOtherBoardPresentation();
			if(player2BoardPresentation != null)
				AnsiConsole.out.print(player2BoardPresentation.getOutput());
		}
	}

	@Override
	public void showNetworkInfoScreen() {
		gameState = GameState.NetworkInput;
		AnsiConsole.out.print(Ansi.ansi().cursor(0, 0));
		AnsiConsole.out.print(Ansi.ansi().eraseScreen());
		String[] networkInfo = Utils.readLines("/network.txt");
		for (String s : networkInfo) {
			AnsiConsole.out.println(Ansi.ansi().fg(Color.GREEN).a("\t\t" + s).reset());
		}
	}

	@Override
	public void showGameOverScreen() {
		AnsiConsole.out.print(Ansi.ansi().cursor(0, 0));
		AnsiConsole.out.print(Ansi.ansi().eraseScreen());
		
		String[] goodbyeString = Utils.readLines("/gameover.txt");
		for (String s : goodbyeString) {
			AnsiConsole.out.println(Ansi.ansi().fg(Color.RED).a("\t" + s).reset());
		}
		
		unregisterNativeHook();
	}
	
	@Override
	public void showGoodbyeScreen() {
		AnsiConsole.out.print(Ansi.ansi().cursor(0, 0));
		AnsiConsole.out.print(Ansi.ansi().eraseScreen());
		
		String[] goodbyeString = Utils.readLines("/goodbye.txt");
		for (String s : goodbyeString) {
			AnsiConsole.out.println(Ansi.ansi().fg(Color.GREEN).a("\t" + s).reset());
		}
		
		unregisterNativeHook();
	}

	
	private void registerNativeHook() {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			System.out.println("native hook could not be registered");
			controller.stopGame();
		}
		
	}

	private void unregisterNativeHook() {
		GlobalScreen.unregisterNativeHook();
	}
}