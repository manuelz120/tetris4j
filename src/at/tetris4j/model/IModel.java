package at.tetris4j.model;

import at.tetris4j.model.components.BoardPresentation;

public interface IModel {
	
	public BoardPresentation getGameBoard();
	
	public void moveLeft();
	
	public void moveRight();
	
	public void turn();
	
	public void pause();
	
	public void resume();
	
	public void stop();
}
