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
	
	public Block(){
		//TODO: Initialize a random block;
	}
	
	public void turn(){
		//TODO: Turn the block in one direction;
	}
	
	public String[] getPresentation(){
		return this.presentation;
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
