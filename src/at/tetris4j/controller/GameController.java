package at.tetris4j.controller;

import at.tetris4j.model.IModel;

public class GameController implements IController{

	private IModel model;
	
	public GameController(IModel model){
		this.model = model;
	}
	
	@Override
	public void leftPressed() {
		model.moveLeft();
	}

	@Override
	public void rightPressed() {
		model.moveRight();		
	}

	@Override
	public void upPressed() {
		model.turn();
	}

	@Override
	public void downPressed() {
		//model.moveDown();		
	}

	@Override
	public void pausePressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resumePressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void wPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dPressed() {
		// TODO Auto-generated method stub
		
	}

}
