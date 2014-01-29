package at.tetris4j.model.components;

import java.util.ArrayList;

import at.tetris4j.view.utils.TetrisKey;

/**
 * Class representing a simple Tetris-GameBoard.
 * 
 * @author Manuel Zametter
 * 
 */
public class GameBoard {
	
	private static final String LINE =    "|                   |";
	private static final String HORLINE = "---------------------";
	private int width;
	private int height;
	private Block currentBlock;
	private ArrayList<String> gameBoard;
	private ArrayList<String> oldBoard;
	private BoardPresentation boardPresentation;

	public GameBoard(int height) {
		oldBoard = new ArrayList<String>();
		this.height = height;
		currentBlock = new Block();
		initializeGameBoard(height);
	}

	private void initializeGameBoard(int height) {
		gameBoard = new ArrayList<String>();
		gameBoard.add(HORLINE);
		for (int i = 0; i < height; i++) {
			gameBoard.add(LINE);
		}
		gameBoard.add(HORLINE);
		oldBoard = new ArrayList<String>(gameBoard);
		updateBoardPresentation();
	}

	/**
	 * Checks whether the current block can turn
	 * 
	 * @return True if the current block can turn, otherwise false.
	 */
	private boolean canCurrentBlockTurn() {
		// TODO: Check if specific block can turn.
		return true;
	}

	/**
	 * Checks whether the current block can move in a specified direction.
	 * 
	 * @param direction
	 *            A TetrisKey, used to specify a direction. Possible inputs:
	 *            LEFT;RIGHT;DOWN
	 * @return True if the current block can move in the specified direction,
	 *         otherwise false.
	 */
	private boolean canCurrentBlockMove(TetrisKey direction) {
		switch (direction) {
		case DOWN:
			if (currentBlock.getY() < this.height) {
				String[] presentation = currentBlock.getPresentation();
				for (int i = 0; i < presentation.length; i++) {
					String currentLine = currentBlock.getPresentation()[i];
					int start = currentBlock.getX()+currentLine.indexOf("#");
					int end = currentBlock.getX()+currentLine.lastIndexOf("#")+1;
					String lineBelowCurrentLine = oldBoard.get(
							currentBlock.getY() + i + 2).substring(start, end);
					if (lineBelowCurrentLine.contains("#")
							|| lineBelowCurrentLine.contains("-")) {
						currentBlock = new Block();
						oldBoard = new ArrayList<String>(gameBoard);
						return false;
					}
				}
				return true;
			}
			return false;
		case LEFT:
			if (currentBlock.getX() - 1 > 0) {
				String[] presentation = currentBlock.getPresentation();
				for (int i = 0; i < presentation.length; i++) {
					String currentLine = currentBlock.getPresentation()[i];
					int start = currentBlock.getX()+currentLine.indexOf("#");
					String currentBoardLine = oldBoard.get(
							currentBlock.getY() + i + 1);
					if (currentBoardLine.charAt(start-1) != ' ') {
						return false;
					}
				}
				return true;
			} 
			return false;
		case RIGHT:
			if (currentBlock.getX() + currentBlock.getWidth() < LINE.length() - 1) {
				String[] presentation = currentBlock.getPresentation();
				for (int i = 0; i < presentation.length; i++) {
					String currentLine = currentBlock.getPresentation()[i];
					int end = currentBlock.getX()+currentLine.lastIndexOf("#")+1;
					String currentBoardLine = oldBoard.get(
							currentBlock.getY() + i + 1);
					if (currentBoardLine.charAt(end) != ' ') {
						return false;
					}
				}
				return true;
			}
			return false;
		default:
			break;
		}

		return true;
	}

	public void updateGameBoard() {
		updateCurrentBlockPosition();
		removeFilledRows();
	}
	
	/**
	 * Remove filled Rows from the GameBoard.
	 */
	private void removeFilledRows() {
		// TODO: Test it!
		for (int i = 1; i < height + 1; i++) {
			if (!oldBoard.get(i).contains(" ") && oldBoard.get(i).contains("|")) {
				oldBoard.remove(i);
				oldBoard.add(1, LINE);
			}
		}
	}

	/**
	 * Moves the current block in a specified direction, if it is possible.
	 * 
	 * @param direction
	 *            A TetrisKey specifying the direction, in which the current
	 *            block should be moved. Possible Inputs: LEFT;RIGHT;DOWN;
	 */
	private void moveCurrentBlock(TetrisKey direction) {
		if (canCurrentBlockMove(direction)) {
			switch (direction) {
			case DOWN:
				currentBlock.moveDown();
				break;
			case LEFT:
				currentBlock.moveLeft();
				break;
			case RIGHT:
				currentBlock.moveRight();
				break;
			default:
				break;
			}
		}
		updateBoardPresentation();
	}

	/**
	 * Turn the current block, if it is possible.
	 */
	public void turnCurrentBlock() {
		if (canCurrentBlockTurn()) {
			// TODO: Turn the current block;
			currentBlock.turn();
			updateBoardPresentation();
		}
	}

	/**
	 * Update the position of the current block.
	 */
	private void updateCurrentBlockPosition() {
		moveCurrentBlock(TetrisKey.DOWN);
	}

	/**
	 * Get a BoardPresentation to visualize the current GameState.
	 * 
	 * @return The current BoardPresentation
	 */
	public BoardPresentation getBoardPresentation() {
		return this.boardPresentation;
	}

	private void updateBoardPresentation() {
		gameBoard.clear();

		//TODO building the string should not be done here
		
		StringBuilder sb = new StringBuilder();
		gameBoard = new ArrayList<String>(oldBoard);
		String[] presentation = currentBlock.getPresentation();

		for (int j = 0; j < presentation.length; j++) {
			char[] chars = oldBoard.get(currentBlock.getY() + 1 + j).toCharArray();

			for (int k = 0; k < presentation[j].length(); k++) {
				if(presentation[j].charAt(k)  == '#'){
					chars[currentBlock.getX() + k] = presentation[j].charAt(k);					
				}
			}
			gameBoard.set(currentBlock.getY() + 1 + j, String.valueOf(chars));
		}

		for (String s : gameBoard) {
			sb.append(s);
			sb.append("\n");
		}

		this.boardPresentation = new BoardPresentation(sb.toString());
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void moveLeft() {
		// TODO remove TetrisKey
		moveCurrentBlock(TetrisKey.LEFT);
	}
	
	public void moveRight() {
		// TODO remove TetrisKey
		moveCurrentBlock(TetrisKey.RIGHT);
	}
}
