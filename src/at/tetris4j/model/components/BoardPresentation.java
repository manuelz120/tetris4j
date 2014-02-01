package at.tetris4j.model.components;

public class BoardPresentation {
	
	private String output;
	
	private int[][] presentation;
	
	public BoardPresentation(int[][] presentation){
		this.presentation = presentation;
	}
	
	public BoardPresentation(String output){
		this.output = output;
	}
	
	public String getOutput(){
		return this.output;
	}
	
	public int[][] getPresentation(){
		return this.presentation;
	}
}
