package at.tetris4j.model.components;

import java.util.Arrays;
import java.util.Random;

import at.tetris4j.commons.TetrisColor;

/**
 * Enum to define the different forms of Tetris-Blocks.
 * @author Manuel Zametter
 *
 */
public enum Blocktype {
	I (new int[][]{{1},{1},{1},{1}}, TetrisColor.CYAN),
					
	J (new int[][]{{0,1},
				   {0,1},
				   {1,1}}, TetrisColor.BLUE),
					
	L (new int[][]{{1,0},
				   {1,0},
			       {1,1}}, TetrisColor.YELLOW),
					
	O (new int[][]{{1,1},
				   {1,1}}, TetrisColor.WHITE),
					
	S (new int[][]{{0,1,1},
				   {1,1,0}}, TetrisColor.GREEN),
					
	T (new int[][]{{0,1,0},
				   {1,1,1}}, TetrisColor.MAGENTA),
					
	Z ( new int[][]{{1,1,0},
					{0,1,1}}, TetrisColor.RED);
	
	private int[][] form;
	private TetrisColor color;
	
	private Blocktype(int[][] form, TetrisColor color) {
		this.form = form;
		this.color = color;
	}
	
	private static Random random = new Random();
	
	
	/**
	 * Method to get the basic presentation form of a Tetris-Block.
	 * @return
	 * A string[] which represents the form of the block.
	 */
	public int[][] getBasicBlock(){	
		return createCopyOfBlock(form);
	}
	
	public TetrisColor getColor(){	
		return color;
	}
	
	private int[][] createCopyOfBlock(int[][] block) {
		
		final int height = block.length;
		
		int[][] copy = new int[height][];
		
		for (int i = 0; i < height; i++) {
			
			copy[i] = Arrays.copyOf(block[i], block[i].length);
			
		}
		
		return copy;
	}
	
	public static Blocktype getRandomBlockType(){
		switch(random.nextInt(7)){
		case 0:
			return I;
		case 1:
			return J;
		case 2:
			return L;
		case 3:
			return O;
		case 4:
			return S;
		case 5:
			return T;
		case 6:
			return Z;
		default:
			return null;
		}
	}
}
