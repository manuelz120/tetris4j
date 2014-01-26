package at.tetris4j.view;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import at.tetris4j.controller.IController;
import at.tetris4j.model.IModel;
import at.tetris4j.model.components.BoardPresentation;
import at.tetris4j.view.utils.TetrisKey;

public class TetrisView implements IView {
	
	private IController controller;
	private IModel model;
	private BoardPresentation boardPresentation;
	

	public TetrisView(IController controller, IModel model){
		this.controller = controller;
		this.model = model;
	}
	
	@Override
	public void render(){
		updateScreen();
	}
	
	@Override
	public void KeyPressed(TetrisKey key) {
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
		default:
			break;
		}
	}
	
	private void updateScreen(){
		while (true) {
			AnsiConsole.out.print(Ansi.ansi().eraseScreen());			
			boardPresentation = model.getGameBoard();
			AnsiConsole.out.print(boardPresentation.getOutput());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
