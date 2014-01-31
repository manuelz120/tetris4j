package at.tetris4j.model;

import java.net.InetAddress;

import at.tetris4j.model.components.BoardPresentation;

public interface IModel {
	
	void updateGame();
	
	public BoardPresentation getGameBoard();
	
	public void moveLeft();
	
	public void moveRight();
	
	public void moveDown();
	
	public void turn();
	
	public void pause();
	
	public void resume();
	
	public void stop();
	
	public void startNewGame();
	
	public void startNewMultiplayerGame(InetAddress ip);
	
	public BoardPresentation getOtherBoardPresentation();
}
