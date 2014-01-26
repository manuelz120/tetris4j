package at.tetris4j.model.components;

import at.tetris4j.resources.AnsiCodes;
import at.tetris4j.view.utils.TetrisKey;

/**
 * Class representing a simple Tetris-GameBoard.
 * @author Manuel Zametter
 *
 */
public class GameBoard {
	private static final String LINE = "|                                    |";
	private static final String HORLINE = "--------------------------------------";
	private int width;
	private int height;
	private Block currentBlock;
	
	public GameBoard(int width, int height){
		this.width = width;
		this.height = height;
		currentBlock = new Block();		
	}
	
	/**
	 * Checks whether the current block can turn
	 * @return
	 * True if the current block can turn, otherwise false.
	 */
	private boolean canCurrentBlockTurn(){
		//TODO: Check if specific block can turn.
		return true;
	}
	
	/**
	 * Checks whether the current block can move in a specified direction.
	 * @param direction
	 * A TetrisKey, used to specify a direction. Possible inputs: LEFT;RIGHT;DOWN
	 * @return
	 * True if the current block can move in the specified direction, otherwise false.
	 */
	private boolean canCurrentBlockMove(TetrisKey direction){
		//TODO: Check whether a specific block can move.
		switch(direction){
		case DOWN:
			break;
		case LEFT:
			break;
		case RIGHT:
			break;
		default:
			break;
		}
		
		return true;
	}
	
	/**
	 * Remove filled Rows from the GameBoard.
	 */
	private void removeFilledRows(){
		//TODO: Search filled rows and remove them. 
	}
	
	/**
	 * Moves the current block in a specified direction, if it is possible.
	 * @param direction
	 * A TetrisKey specifying the direction, in which the current block should be moved. Possible 
	 * Inputs: LEFT;RIGHT;DOWN;
	 */
	public void moveCurrentBlock(TetrisKey direction){
		if(canCurrentBlockMove(direction)){
			switch (direction) {
			case DOWN:
				currentBlock.setY(currentBlock.getY()+1);
				break;
			case LEFT:
				currentBlock.setX(currentBlock.getX()-1);
				break;
			case RIGHT:
				currentBlock.setX(currentBlock.getX()+1);
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * Turn the current block, if it is possible.
	 */
	public void turnCurrentBlock(){
		if(canCurrentBlockTurn()){
			//TODO: Turn the current block;
		}
	}
	
	/**
	 * Update the position of the Blocks.
	 */
	private void updateBlockPositions(){
		if(canCurrentBlockMove(TetrisKey.DOWN)){
			
		}
		//TODO: Update Block Positions.
	}
	
	/**
	 * Get a BoardPresentation to visualize the current GameState. 
	 * @return
	 * The current BoardPresentation
	 */
	public BoardPresentation getGameBoard(){
		//TODO: Return correct BoardPresentation.
		updateBlockPositions();
		removeFilledRows();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(AnsiCodes.ANSI_CLS);
		sb.append("\n");
		sb.append(HORLINE);
		sb.append("\n");
		
		for (int i = 0; i < height; i++) {
			if (currentBlock.getY() == i) {
				String[] presentation = currentBlock.getPresentation();
				for(int j = 0; j<presentation.length; j++){
					char[] chars = LINE.toCharArray();
					for(int k = 0; k<presentation[j].length(); k++){
						chars[currentBlock.getX()+k] = presentation[j].charAt(k);
					}
					sb.append(chars);
					sb.append("\n");
				}
			} else {
				sb.append(LINE);
				sb.append("\n");
			}
		}
		sb.append(HORLINE);
		sb.append("\n");
		moveCurrentBlock(TetrisKey.DOWN);
		return new BoardPresentation(sb.toString());
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
