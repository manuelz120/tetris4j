package at.tetris4j.view;

import at.tetris4j.model.IModel;


public interface IView {

	void updateScreen(IModel model);
	
	void showStartScreen();
}
