package at.tetris4j.core;

import org.fusesource.jansi.AnsiConsole;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import at.tetris4j.controller.GameController;
import at.tetris4j.controller.IController;
import at.tetris4j.model.GameModel;
import at.tetris4j.model.IModel;
import at.tetris4j.view.IView;
import at.tetris4j.view.TetrisView;
import at.tetris4j.view.utils.GlobalKeyListener;

public class Main {

	public static void main(String[] args) {
		AnsiConsole.systemInstall();
		
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}
		IModel model = new GameModel();
		IController controller = new GameController(model);
		IView view  = new TetrisView(controller, model);

		// Construct the example object and initialize native hook.
		GlobalScreen.getInstance()
				.addNativeKeyListener(new GlobalKeyListener(view));		
		view.render();

	}
}
