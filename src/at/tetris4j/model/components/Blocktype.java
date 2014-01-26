package at.tetris4j.model.components;

import java.util.Random;

/**
 * Enum to define the different forms of Tetris-Blocks.
 * @author Manuel Zametter
 *
 */
public enum Blocktype {
	I,
	J,
	L,
	O,
	S,
	T,
	Z;
	
	private static Random random = new Random();
	
	/**
	 * Method to get the basic presentation form of a Tetris-Block.
	 * @return
	 * A string[] which represents the form of the block.
	 */
	public String[] getBasicBlock(){		
		switch(this){
		case I:
			return new String[]{"#",
								"#",
								"#",
								"#",
								"#"};
		case J:
			return new String[]{" #",
								" #",
								" #",
								"##"};
		case L:
			return new String[]{"#",
								"#",
								"#"
							   ,"##"};
		case O:
			return new String[]{"##",
								"##"};
		case S:
			return new String[]{" ##",
								"##"};
		case T:
			return new String[]{" # ",
								"###"};
		case Z:
			return new String[]{"##",
								" ##"};
		default:
			return null;
		}
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
