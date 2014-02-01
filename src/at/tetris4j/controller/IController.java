package at.tetris4j.controller;

import java.net.InetAddress;

public interface IController {
	
	void moveLeft();
	void moveRight();
	void upPressed();
	void downPressed();
	
	void startSinglePlayerMode();
	void multiplayerPressed();
	
	void startGame();
	void stopGame();
	void pauseGame();
	void resumeGame();
	
	void startMultiplayerMode(InetAddress ip);
	void startMultiplayerMode();
}
