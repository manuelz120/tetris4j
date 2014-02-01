package at.tetris4j.model;

import java.net.InetAddress;

import at.tetris4j.model.components.BoardPresentation;

public interface IModel {
	
	void pauseGame();
	void resumeGame();
	void stopGame();
	void startNewGame();
	boolean isGameOver();
	
	void updateGame();
	BoardPresentation getGameBoard();
	
	void moveLeft();
	void moveRight();
	void moveDown();
	void turn();
	
	
	void startNewMultiplayerGame(InetAddress ip);
	void startNewMultiplayerGame();
	BoardPresentation getOtherBoardPresentation();
	boolean isConnectionEstablished();
}
