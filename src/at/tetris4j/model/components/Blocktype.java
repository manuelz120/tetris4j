package at.tetris4j.model.components;

import java.util.Random;

/**
 * Enum to define the different forms of Tetris-Blocks.
 * @author Manuel Zametter
 *
 */
public enum Blocktype {
	I (new String[]{"#",
					"#",
					"#",
					"#",
					"#"}),
					
	J (new String[]{" #",
					" #",
					" #",
					"##"}),
					
	L (new String[]{"#",
					"#",
					"#",
					"##"}),
					
	O (new String[]{"##",
					"##"}),
					
	S (new String[]{" ##",
					"##"}),
					
	T (new String[]{" # ",
					"###"}),
					
	Z ( new String[]{"##",
					 " ##"});
	
	private String[] form;
	
	private Blocktype(String[] form) {
		this.form = form;
	}
	
	private static Random random = new Random();
	
	
	/**
	 * Method to get the basic presentation form of a Tetris-Block.
	 * @return
	 * A string[] which represents the form of the block.
	 */
	public String[] getBasicBlock(){		
		return form;
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
