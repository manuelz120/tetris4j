package at.tetris4j.core;

import org.fusesource.jansi.AnsiConsole;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import at.tetris4j.view.TetrisView;
import at.tetris4j.view.IView;
import at.tetris4j.view.utils.GlobalKeyListener;

public class Main {

	public static void main(String[] args) {

		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}
		
		IView view  = new TetrisView();

		// Construct the example object and initialize native hook.
		GlobalScreen.getInstance()
				.addNativeKeyListener(new GlobalKeyListener(view));

		AnsiConsole.systemInstall();
		
		view.render();

	}
}
