package at.tetris4j.model;

import at.tetris4j.model.components.BoardPresentation;
import at.tetris4j.model.components.GameBoard;

public class GameModel implements IModel {

	private GameBoard gameBoard;
	
	public GameModel(){
		this.gameBoard = new GameBoard(30);
	}
	
	@Override
	public void updateGame() {
		gameBoard.updateGameBoard();
	}
	
	@Override
	public BoardPresentation getGameBoard() {
		return this.gameBoard.getBoardPresentation();
	}

	@Override
	public void moveLeft() {
		gameBoard.moveLeft();
	}

	@Override
	public void moveRight() {
		gameBoard.moveRight();
	}

	@Override
	public void turn() {
		gameBoard.turnCurrentBlock();
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
