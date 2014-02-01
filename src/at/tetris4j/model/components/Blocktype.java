package at.tetris4j.model.components;

import java.util.Arrays;
import java.util.Random;

/**
 * Enum to define the different forms of Tetris-Blocks.
 * @author Manuel Zametter
 *
 */
public enum Blocktype {
	I (new int[][]{{1},{1},{1},{1}}),
					
	J (new int[][]{{0,1},
				   {0,1},
				   {1,1}}),
					
	L (new int[][]{{1,0},
				   {1,0},
			       {1,1}}),
					
	O (new int[][]{{1,1},
				   {1,1}}),
					
	S (new int[][]{{0,1,1},
				   {1,1,0}}),
					
	T (new int[][]{{0,1,0},
				   {1,1,1}}),
					
	Z ( new int[][]{{1,1,0},
					{0,1,1}});
	
	private int[][] form;
	
	private Blocktype(int[][] form) {
		this.form = form;
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
