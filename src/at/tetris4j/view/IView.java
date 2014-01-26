package at.tetris4j.view;

import at.tetris4j.view.utils.TetrisKey;

public interface IView {

	void render();
	
	void moveRight();
	
	void moveLeft();
	
	void KeyPressed(TetrisKey key);
	
}
