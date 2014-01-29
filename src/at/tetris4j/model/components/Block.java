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
		ArrayList<char[]> presentationList = new ArrayList<char[]>();
		ArrayList<String> turnedPresentation = new ArrayList<String>();
		int maxCount = 0;
		for(String line : presentation){
			if(line.length() > maxCount){
				maxCount = line.length();
			}
			presentationList.add(line.toCharArray());
		}
		
		for(int i = 0; i < maxCount; i++){
			StringBuilder sb = new StringBuilder();
			for(char[] chars : presentationList){
				if(chars.length > maxCount){
					sb.append(chars[i]);
				}
			}
			turnedPresentation.add(sb.toString());
		}
		
		presentation = turnedPresentation.toArray(presentation);
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
