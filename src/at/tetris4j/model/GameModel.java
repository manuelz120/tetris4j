package at.tetris4j.model;

import at.tetris4j.model.components.BoardPresentation;
import at.tetris4j.model.components.GameBoard;

public class GameModel implements IModel {

	private GameBoard gameBoard;
	
	public GameModel(){
		this.gameBoard = new GameBoard(30, 50);
	}
	
	@Override
	public BoardPresentation getGameBoard() {
		return this.gameBoard.getGameBoard();
	}

	@Override
	public void moveLeft() {
	}

	@Override
	public void moveRight() {
	}

	@Override
	public void turn() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void stop() {
	}

	@Override
	public void moveDown() {
		
	}

}
