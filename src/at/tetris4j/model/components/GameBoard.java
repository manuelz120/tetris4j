package at.tetris4j.model.components;

import java.util.ArrayList;

/**
 * Class representing a simple Tetris-GameBoard.
 * @author Manuel Zametter
 *
 */
public class GameBoard {
	private int width;
	private int height;
	private ArrayList<Block> blockList;
	private String[] gameBoard;
	
	public GameBoard(int width, int height){
		this.width = width;
		this.height = height;
		this.blockList = new ArrayList<Block>();
		this.gameBoard = new String[height];
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
	private BoardPresentation getGameBoard(){
		//TODO: Return correct BoardPresentation.
		return new BoardPresentation();
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
