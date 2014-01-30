package at.tetris4j.view;

import java.io.File;
import java.io.IOException;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.AnsiConsole;
import org.jnativehook.GlobalScreen;

import at.tetris4j.controller.IController;
import at.tetris4j.helpers.Utils;
import at.tetris4j.model.IModel;
import at.tetris4j.model.components.BoardPresentation;
import at.tetris4j.view.utils.GlobalKeyListener;
import at.tetris4j.view.utils.TetrisKey;

public class TetrisView implements IConsoleView {
	
	private final IController controller;

	public TetrisView(IController controller){
		this.controller = controller;
		
		GlobalScreen.getInstance().addNativeKeyListener(new GlobalKeyListener(this));
	}
	
	@Override
	public void showStartScreen() {
		AnsiConsole.out.print(Ansi.ansi().eraseScreen());
		try {
			String[] heading = Utils.readLines(System.getProperty("user.dir") + File.separator + "assets" + File.separator + "heading.txt");
			for(String s : heading){
				AnsiConsole.out.println(Ansi.ansi().fg(Color.YELLOW).a("\t\t"+s).reset());
			}
			AnsiConsole.out.println();
			AnsiConsole.out.println();
			AnsiConsole.out.println();
			String[] menu = Utils.readLines(System.getProperty("user.dir") + File.separator + "assets" + File.separator + "menu.txt");
			for(String s : menu){
				AnsiConsole.out.println(Ansi.ansi().fg(Color.BLUE).a("\t"+s).reset());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyPressed(TetrisKey key) {
		switch (key){
		case UP:
			controller.upPressed();
			break;
		case DOWN:
			controller.downPressed();
			break;
		case LEFT:
			controller.leftPressed();
			break;
		case RIGHT:
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
		case ONE:
			controller.onePressed();
			break;
		case TWO:
			break;
		default:
			break;
		}
	}
	
	@Override
	public void updateScreen(IModel model) {
		AnsiConsole.out.print(Ansi.ansi().cursor(0,0));
		
		BoardPresentation boardPresentation = model.getGameBoard();
		
		AnsiConsole.out.print(boardPresentation.getOutput());
	}
}
