package at.tetris4j.model.components;

import java.util.Random;

import at.tetris4j.commons.TetrisColor;

/**
 * A Class representing a Tetris-Block.
 * @author Manuel Zametter
 * @author Florian Genser
 *
 */
public class Block {	
	private int x;
	private int y;
	
	private int[][] block;
	
	/**
	 * Initializes a random tetris-block.
	 */
	private Block(Blocktype blocktype, int width){
		
		TetrisColor color = blocktype.getColor();
		int rgb = color.getRGB();
		int[][] template = blocktype.getBasicBlock();
		int normalizedWidth = width - template[0].length + 1;

		x = calculateStartPosition(normalizedWidth);
		
		block = createBlock(template, rgb);
		
	}
	
	public static Block getRandomBlock(int width) {
		
		Blocktype blocktype = Blocktype.getRandomBlockType();
		
		Block block = new Block(blocktype, width);
		
		return block;
		
	}
	
	private int calculateStartPosition(int width) {
		Random random = new Random();
		
		int position = random.nextInt(width);
		
		return position;
	}
	
	private int[][] createBlock(int[][] template, int rgbValue) {
		
		final int heigth = template.length;
		final int width = template[0].length;
		
		int[][] block = new int[heigth][width];
		
		for (int i = 0; i < block.length; i++) {
			
			for (int j = 0; j < block[i].length; j++) {
				
				if (template[i][j] != 0) {
					block[i][j] = rgbValue;
				} else {
					block[i][j] = 0;
				}
			}
		}
		
		return block;
	}
	
	public void turn(){
		int height = block.length;
		int width = block[0].length;
		int[][] newBlock = new int[width][height];
		
		for (int x = 0; x < width; x++) {
			for (int y = 0, i = height - 1; y < height; y++, i--) {
				
				newBlock[x][y] = block[i][x];
			}
		}
		
		block = newBlock;
		
	}

	public int[][] getBlock(){
		return this.block;
	}
	
	public int getWidth() {
		return block[0].length;
	}
	
	public int getHeight() {
		return block.length;
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
	

}
