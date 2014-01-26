package at.tetris4j.model.components;

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
}
