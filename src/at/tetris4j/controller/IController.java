package at.tetris4j.controller;

import java.net.InetAddress;

public interface IController {
	void leftPressed();
	void rightPressed();
	void upPressed();
	void downPressed();
	void pausePressed();
	void resumePressed();
	void stopPressed();
	void wPressed();
	void aPressed();
	void sPressed();
	void dPressed();
	void singleplayerPressed();
	void multiplayerPressed();
	
	void startGame();
	void startSingleplayerMode();
	void startMultiplayerMode(InetAddress ip);
}
