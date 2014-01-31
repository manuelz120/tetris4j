package at.tetris4j.model.components;

import java.awt.Color;
import java.util.ArrayList;

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
		x = ((int)(Math.random()*10))+1;
	}
	
	public void turn(){
		//TODO: Turn the block in one direction;		
		
		ArrayList<String> newPresentation = new ArrayList<String>();
		
		
		for(int i = 0; i<presentation[0].length(); i++){
			StringBuilder sb = new StringBuilder();
			for(String s : presentation){
				sb.append(s.charAt(i));
			}
			if(sb.toString() != null){
				newPresentation.add(sb.toString());				
			}
		}
		
		presentation = new String[newPresentation.size()];
		for(int i = 0; i < newPresentation.size(); i++){
			presentation[i] = newPresentation.get(i);
		}
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

	public void moveRight() {
		x++;
	}	
	
	public void moveLeft() {
		x--;
	}	
	
	public int getY() {
		return y;
	}

	public void moveDown() {
		y++;
	}
	
	public Color getColor() {
		return color;
	}

	public Blocktype getBlocktype() {
		return blocktype;
	}


}
