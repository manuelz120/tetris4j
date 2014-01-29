package at.tetris4j.view;

import at.tetris4j.view.utils.TetrisKey;

public interface IConsoleView extends IView {

	void keyPressed(TetrisKey key);
}
