package at.tetris4j.view;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	private int lineCount;
	private GameState gameState = GameState.MainMenu;

	public TetrisView(IController controller) {
		this.controller = controller;
		lineCount = 0;
		GlobalScreen.getInstance().addNativeKeyListener(
				new GlobalKeyListener(this));
	}

	@Override
	public void showStartScreen() {
		gameState = GameState.MainMenu;
		AnsiConsole.out.print(Ansi.ansi().cursor(0, 0));
		AnsiConsole.out.print(Ansi.ansi().eraseScreen());

		InputStream in = getClass().getResourceAsStream("/heading.txt");
		BufferedReader input = new BufferedReader(new InputStreamReader(in));

		String[] heading = Utils.readLines(input);
		for (String s : heading) {
			AnsiConsole.out.println(Ansi.ansi().fg(Color.YELLOW).a("\t" + s)
					.reset());
			lineCount++;
		}
		AnsiConsole.out.println();
		AnsiConsole.out.println();
		AnsiConsole.out.println();
		lineCount += 3;

		in = getClass().getResourceAsStream("/menu.txt");
		input = new BufferedReader(new InputStreamReader(in));

		String[] menu = Utils.readLines(input);
		for (String s : menu) {
			AnsiConsole.out.println(Ansi.ansi().fg(Color.BLUE).a(s).reset());
			lineCount++;
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
				controller.leftPressed();
			break;
		case RIGHT:
			if (gameState == GameState.SinglePlayer
					|| gameState == GameState.Multiplayer)
				controller.rightPressed();
			break;
		case W:
			controller.wPressed();
			break;
		case A:
			controller.aPressed();
			break;
		case S:
			controller.sPressed();
			break;
		case D:
			controller.dPressed();
			break;
		case PAUSE:
			controller.pausePressed();
			break;
		case RESUME:
			controller.resumePressed();
			break;
		case STOP:
			controller.stopPressed();
			showGoodbyeScreen();
			break;
		case FUNCTIONKEY_1:
			if (gameState == GameState.MainMenu) {
				gameState = GameState.SinglePlayer;
				controller.singleplayerPressed();
			} else if (gameState == GameState.NetworkInput) {
				gameState = GameState.WaitingForConnection;
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
			scanner.close();
			controller.startMultiplayerMode(ipAddress);
			this.gameState = GameState.Multiplayer;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} finally {
			try {
				GlobalScreen.registerNativeHook();
			} catch (NativeHookException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateScreen(IModel model) {
		if (gameState == GameState.SinglePlayer) {
			AnsiConsole.out.print(Ansi.ansi().cursor(lineCount + 5, 0));

			BoardPresentation boardPresentation = model.getGameBoard();

			AnsiConsole.out.print(boardPresentation.getOutput());
		} else if (gameState == GameState.Multiplayer) {
			AnsiConsole.out.print(Ansi.ansi().eraseScreen());
			AnsiConsole.out.print(Ansi.ansi().cursor(0, 0));
			BoardPresentation player1BoardPresentation = model.getGameBoard();
			AnsiConsole.out.print(player1BoardPresentation.getOutput());
			AnsiConsole.out.print(Ansi.ansi().cursor(0, 50));
			BoardPresentation player2BoardPresentation = model
					.getOtherBoardPresentation();
			AnsiConsole.out.print(player2BoardPresentation.getOutput());
		}
	}

	@Override
	public void showNetworkInfoScreen() {
		gameState = GameState.NetworkInput;
		AnsiConsole.out.print(Ansi.ansi().cursor(0, 0));
		AnsiConsole.out.print(Ansi.ansi().eraseScreen());
		String[] networkInfo;
		InputStream in = getClass().getResourceAsStream("/network.txt");
		BufferedReader input = new BufferedReader(new InputStreamReader(in));
		networkInfo = Utils.readLines(input);
		for (String s : networkInfo) {
			AnsiConsole.out.println(Ansi.ansi().fg(Color.GREEN).a("\t\t" + s)
					.reset());
		}
	}

	@Override
	public void showGoodbyeScreen() {
		AnsiConsole.out.print(Ansi.ansi().cursor(0, 0));
		AnsiConsole.out.print(Ansi.ansi().eraseScreen());
		String[] goodbyeString;
		InputStream in = getClass().getResourceAsStream("/goodbye.txt");
		BufferedReader input = new BufferedReader(new InputStreamReader(in));
		goodbyeString = Utils.readLines(input);
		for (String s : goodbyeString) {
			AnsiConsole.out.println(Ansi.ansi().fg(Color.GREEN).a("\t" + s)
					.reset());
		}
	}

}
