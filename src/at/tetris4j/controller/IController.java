package at.tetris4j.controller;

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
	void onePressed();
	void twoPressed();
	
	void startGame();
	void startSingleplayerMode();
	void startMultiplayerMode();
}
