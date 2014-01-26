package at.tetris4j.model;

import at.tetris4j.model.components.BoardPresentation;
import at.tetris4j.model.components.GameBoard;
import at.tetris4j.view.utils.TetrisKey;

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
		gameBoard.moveCurrentBlock(TetrisKey.LEFT);
	}

	@Override
	public void moveRight() {
		gameBoard.moveCurrentBlock(TetrisKey.RIGHT);
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
		gameBoard.moveCurrentBlock(TetrisKey.DOWN);
	}

}
