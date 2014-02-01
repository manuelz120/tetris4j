package at.tetris4j.model.components;

import java.util.Arrays;

import at.tetris4j.view.utils.TetrisKey;

/**
 * Class representing a simple Tetris-GameBoard.
 * 
 * @author Manuel Zametter
 * 
 */
public class GameBoard {
	
	private static final int DEFAULT_WIDTH = 20;
	
	private boolean isGameOver = false;
	
	private int width;
	private int height;
	private int[][] gameBoard;
	private Block currentBlock;
	private BoardPresentation boardPresentation;

	public GameBoard(int theHeight) {
		this.height = theHeight;
		this.width = DEFAULT_WIDTH;
		currentBlock = Block.getRandomBlock(width);
		gameBoard = new int[height][width];
		updateBoardPresentation();
	}

	private boolean canMoveLeft() {
		
		final int currentY = currentBlock.getY();
		final int currentX = currentBlock.getX();
		final int[][] block = currentBlock.getBlock();
		final int blockHeight = block.length;
		final int blockWidth = block[0].length;
		
		if (currentX - 1 < 0) {
			return false;
		}
		
		for (int j = 0; j < blockWidth; j++) {
			for (int i = 0; i < blockHeight; i++) {
				if (block[i][j] != 0 && gameBoard[i + currentY][j + currentX - 1] != 0) {
					return false;
				}
			}
			
		}
		return true;
	}
	
	private boolean canMoveRight() {
		
		final int currentY = currentBlock.getY();
		final int currentX = currentBlock.getX();
		final int[][] block = currentBlock.getBlock();
		final int blockHeight = block.length;
		final int blockWidth = block[0].length;
		
		if (currentX + blockWidth >= width) {
			return false;
		}
		
		for (int j = blockWidth - 1; j >= 0; j--) {
			for (int i = 0; i < blockHeight; i++) {
				if (block[i][j] != 0 && gameBoard[i + currentY][j + currentX + 1] != 0) {
					return false;
				}
			}
			
		}
		return true;
	}

	public void updateGameBoard() {
		moveCurrentBlock(TetrisKey.DOWN);
	}
	
	
	/**
	 * Moves the current block in a specified direction, if it is possible.
	 * 
	 * @param direction
	 *            A TetrisKey specifying the direction, in which the current
	 *            block should be moved. Possible Inputs: LEFT;RIGHT;DOWN;
	 */
	private void moveCurrentBlock(TetrisKey direction) {
		switch (direction) {
		case DOWN:
			currentBlock.moveDown();
			break;
		case LEFT:
			if (canMoveLeft()) {
				currentBlock.moveLeft();
			}
			break;
		case RIGHT:
			if (canMoveRight()) {
				currentBlock.moveRight();
			}
			break;
		default:
			break;
		}
		updateBoardPresentation();
	}

	/**
	 * Turn the current block, if it is possible.
	 */
	public void turnCurrentBlock() {
		if (canCurrentBlockTurn()) {
			currentBlock.turn();
			updateBoardPresentation();
		}
	}

	
	
	/**
	 * Checks whether the current block can turn
	 * 
	 * @return True if the current block can turn, otherwise false.
	 */
	private boolean canCurrentBlockTurn() {

		int[][] block = currentBlock.getBlock();
		int currentX = currentBlock.getX();
		int currentY = currentBlock.getY();
		int blockWidth = currentBlock.getWidth();
		int blockHeight = currentBlock.getHeight();
		
		if (currentX + blockHeight <= width) {
			
			for (int i = currentY, l = 0; i < blockWidth + currentY; i++, l++) {
				
				for (int j = currentX, n = blockHeight - 1; j <  blockHeight + currentX; j++, n--) {
					
					if (block[n][l] != 0 && gameBoard[i][j] != 0) {
						
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	private void updateGame() {
		
		if (checkForNewBlock()) {
			
			retireCurrentBlock();
			removeFilledRows();
			checkForGameOver();
		}
	}
	
	private boolean checkForNewBlock() {
		
		int[][] myBlock = currentBlock.getBlock();
		int currentX = currentBlock.getX();
		int currentY = currentBlock.getY();
		int blockHeight = myBlock.length;
		int blockWidth = myBlock[0].length;
		
		// checks if block is at bottom
		if (currentY + blockHeight == height) {
			
			return true;
		}
		
		for (int i = blockHeight - 1; i >= 0; i--) {
			
			for (int j = 0; j < blockWidth; j++) {
				
				if (myBlock[i][j] != 0 && gameBoard[i + currentY + 1][j + currentX] != 0) {
					
					return true;
				}
			}
		}
		return false;
	}
	
	private void retireCurrentBlock() {

		insertBlockIntoBoard(currentBlock, gameBoard);
		
		currentBlock = Block.getRandomBlock(width);
		
	}
	
	
	/**
	 * Remove filled Rows from the GameBoard.
	 */
	private void removeFilledRows() {
		for (int i = 0; i < height; i++) {
			boolean rowFinished = true;
			for (int j = 0; j < width; j++) {
				
				if (gameBoard[i][j] == 0) {
					rowFinished = false;
					break;
				}
			}
			if (rowFinished) {
				
				removeRow(i);
			}
		}
	}

	private void removeRow(int rowIndex) {
		
		int[][] newBoard = new int[height][width];
		
		Arrays.fill(newBoard[0], 0);
		for (int i = 1; i <= rowIndex; i++) {
			newBoard[i] = gameBoard[i - 1];
		}
		
		for (int i = rowIndex + 1; i < height; i++) {
			newBoard[i] = gameBoard[i];
		}
		
		gameBoard = newBoard;
	}
	
	private void checkForGameOver() {
		
		for (int i = 0; i < gameBoard[0].length; i++) {
			
			if (gameBoard[0][i] != 0) {
				isGameOver = true;
			}
		}
		
	}

	private void updateBoardPresentation() {
		
		updateGame();
		
		int[][] workingCopyOfBoard = createCopyOfBoard(gameBoard);
		
		insertBlockIntoBoard(currentBlock, workingCopyOfBoard);
		
		this.boardPresentation = new BoardPresentation(workingCopyOfBoard);
	}

	private int[][] createCopyOfBoard(int[][] board) {
		int[][] copyBoard = new int[height][width];
		
		for (int i = 0; i < height; i++) {
			
			System.arraycopy(board[i], 0, copyBoard[i], 0, width);
			
		}
		
		return copyBoard;
	}

	private void insertBlockIntoBoard(Block block, int[][] board) {
		final int[][] blockMatrix = block.getBlock();
		final int xPosition = block.getX();
		final int yPosition = block.getY();
		
		for (int i = 0; i < blockMatrix.length; i++) {
			for (int j = 0; j < blockMatrix[0].length; j++) {
				int value = blockMatrix[i][j];
				if (value != 0) {
					board[yPosition + i][xPosition + j] = value;
				}
				
			}
		}
	}

	
	/**
	 * Get a BoardPresentation to visualize the current GameState.
	 * 
	 * @return The current BoardPresentation
	 */
	public BoardPresentation getBoardPresentation() {
		return this.boardPresentation;
	}
	
	public void moveLeft() {
		moveCurrentBlock(TetrisKey.LEFT);
	}
	
	public void moveRight() {
		moveCurrentBlock(TetrisKey.RIGHT);
	}
	
	public void moveDown() {
		moveCurrentBlock(TetrisKey.DOWN);
	}
	
	public boolean isGameOver() {
		return isGameOver;
	}
}
