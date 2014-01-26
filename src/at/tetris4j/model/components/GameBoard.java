package at.tetris4j.model.components;

import java.util.ArrayList;

import at.tetris4j.resources.AnsiCodes;

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
	private ArrayList<Block> blockList;
	private String[] gameBoard;
	
	private int posX;
	private int posY;
	
	public GameBoard(int width, int height){
		this.width = width;
		this.height = height;
		this.blockList = new ArrayList<Block>();
		this.gameBoard = new String[height];
		
		posX = 5;
		posY = 0;
	}
	
	/**
	 * Checks whether a specific block can be turned.
	 * @param block
	 * The block, which should be turned.
	 * @return
	 * True if the block can be turned, otherwise false.
	 */
	private boolean canBlockTurn(Block block){
		//TODO: Check if specific block can turn.
		return true;
	}
	
	/**
	 * Check whether a specific block can be moved.
	 * @param block
	 * The block, which should be moved.
	 * @return
	 * True if the block can be moved, otherwise false.
	 */
	private boolean canBlockMove(Block block){
		//TODO: Check whether a specific block can move.
		return true;
	}
	
	/**
	 * Remove filled Rows from the GameBoard.
	 */
	private void removeFilledRows(){
		//TODO: Search filled rows and remove them. 
	}
	
	/**
	 * Update the position of the Blocks.
	 */
	private void updateBlockPositions(){
		//TODO: Update Block Positions.
	}
	
	/**
	 * Get a BoardPresentation to visualize the current GameState. 
	 * @return
	 * The current BoardPresentation
	 */
	public BoardPresentation getGameBoard(){
		//TODO: Return correct BoardPresentation.
		StringBuilder sb = new StringBuilder();
		
		sb.append(AnsiCodes.ANSI_CLS);
		sb.append("\n");
		sb.append(HORLINE);
		sb.append("\n");
		
		for (int i = 0; i < height; i++) {
			if (posY % height == i) {
				char[] chars = LINE.toCharArray();
				chars[posX] = 'X';
				sb.append(chars);
				sb.append("\n");
			} else {
				sb.append(LINE);
				sb.append("\n");
			}
		}
		sb.append(HORLINE);
		sb.append("\n");
		posY++;
		return new BoardPresentation(sb.toString());
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ArrayList<Block> getBlockList() {
		return blockList;
	}
}
