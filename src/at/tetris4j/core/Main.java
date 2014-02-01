package at.tetris4j.core;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.Ansi.Erase;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import at.tetris4j.controller.GameController;
import at.tetris4j.controller.IController;
import at.tetris4j.model.GameModel;
import at.tetris4j.model.IModel;

public class Main {

	public static void main(String[] args) {
		
		initializeGame();
		
		IModel model = new GameModel();
		IController controller = new GameController(model);

		controller.startGame();

	}

	private static void initializeGame() {

		AnsiConsole.systemInstall();
		
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		// registers a shutdown hook
		// this shutdown hook is called before the jvm is shutdown and releases the dependencies
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				GlobalScreen.unregisterNativeHook();
				System.out.println("unregister native hook");
				AnsiConsole.out.print(Ansi.ansi().eraseScreen(Erase.FORWARD));
				AnsiConsole.systemUninstall();
				System.out.println("uninstall ansi console");
				// network can be closed here
			}
		});
	}
	
}
