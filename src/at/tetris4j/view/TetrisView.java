package at.tetris4j.view;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.AnsiConsole;
import org.jnativehook.GlobalScreen;

import at.tetris4j.controller.IController;
import at.tetris4j.helpers.Utils;
import at.tetris4j.model.IModel;
import at.tetris4j.model.components.BoardPresentation;
import at.tetris4j.view.utils.GameState;
import at.tetris4j.view.utils.GlobalKeyListener;
import at.tetris4j.view.utils.TetrisKey;

public class TetrisView implements IConsoleView {

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
			AnsiConsole.out.println(Ansi.ansi().fg(Color.YELLOW).a("\t\t" + s)
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
			AnsiConsole.out.println(Ansi.ansi().fg(Color.BLUE).a("\t" + s)
					.reset());
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
			break;
		case FUNCTIONKEY_1:
			if (gameState == GameState.MainMenu)
				controller.singleplayerPressed();
			else if (gameState == GameState.NetworkInput) {
				gameState = GameState.WaitingForConnection;
				System.out.println("START SERVER");
			}
			break;
		case FUNCTIONKEY_2:
			if (gameState == GameState.MainMenu)
				controller.multiplayerPressed();
			else if (gameState == GameState.NetworkInput) {
				System.out.println("PLEASE TYPE IN YOUR DESIRED IP ADDRESS:");
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void updateScreen(IModel model) {
		if(gameState == GameState.SinglePlayer){
			AnsiConsole.out.print(Ansi.ansi().cursor(lineCount + 5, 0));

			BoardPresentation boardPresentation = model.getGameBoard();

			AnsiConsole.out.print(boardPresentation.getOutput());
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
}
