package at.tetris4j.model.components;

import java.awt.Color;

/**
 * A Class representing a Tetris-Block.
 * @author Manuel Zametter
 *
 */
public class Block {	
	private int x;
	private int y;
	private Color color;
	private Blocktype blocktype;
	private String[] presentation;
	
	/**
	 * Initializes a random tetris-block.
	 */
	public Block(){
		blocktype = Blocktype.getRandomBlockType();
		presentation = blocktype.getBasicBlock();
		color = Color.RED;
		x = 5;
	}
	
	public void turn(){
		//TODO: Turn the block in one direction;
	}
	
	public String[] getPresentation(){
		return this.presentation;
	}

	public int getWidth(){
		int maxLength = 0;
		for(String s : presentation){
			if(maxLength < s.length()){
				maxLength = s.length();
			}
		}
		return maxLength;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Blocktype getBlocktype() {
		return blocktype;
	}	
}
