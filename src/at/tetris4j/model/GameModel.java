package at.tetris4j.model;

import at.tetris4j.model.components.BoardPresentation;
import at.tetris4j.model.components.GameBoard;

public class GameModel implements IModel {

	private static final int _HEIGHT = 20;
	private GameBoard gameBoardPlayer1;
	private GameBoard gameBoardPlayer2;
	private boolean isMultiplayer;

	public GameModel(){
		this.gameBoardPlayer1 = new GameBoard(20);
	}
	
	@Override
	public void updateGame() {
		gameBoardPlayer1.updateGameBoard();
	}
	
	@Override
	public BoardPresentation getGameBoard() {
		if(isMultiplayer){
			return new BoardPresentation(" ");
		} else {
			return this.gameBoardPlayer1.getBoardPresentation();
		}
	}

	@Override
	public void moveLeft() {
		gameBoardPlayer1.moveLeft();
	}

	@Override
	public void moveRight() {
		gameBoardPlayer1.moveRight();
	}

	@Override
	public void turn() {
		gameBoardPlayer1.turnCurrentBlock();
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
	
	public boolean isMultiplayer() {
		return isMultiplayer;
	}

	public void setMultiplayer(boolean isMultiplayer) {
		this.isMultiplayer = isMultiplayer;
	}

	@Override
	public void startNewGame() {
		this.gameBoardPlayer1 = new GameBoard(_HEIGHT);		
	}

}
