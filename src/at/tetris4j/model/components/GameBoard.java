package at.tetris4j.model.components;

import java.util.ArrayList;

import at.tetris4j.resources.AnsiCodes;
import at.tetris4j.view.utils.TetrisKey;

/**
 * Class representing a simple Tetris-GameBoard.
 * 
 * @author Manuel Zametter
 * 
 */
public class GameBoard {
	private static final String LINE = "|                                    |";
	private static final String HORLINE = "--------------------------------------";
	private int width;
	private int height;
	private Block currentBlock;
	private ArrayList<String> gameBoard;
	private ArrayList<Block> playedBlocks;

	public GameBoard(int width, int height) {
		this.width = width;
		this.height = height;
		currentBlock = new Block();
		gameBoard = new ArrayList<String>();
		gameBoard.add(HORLINE);
		for (int i = 0; i < height; i++) {
			gameBoard.add(LINE);
		}
		gameBoard.add(HORLINE);
		playedBlocks = new ArrayList<Block>();
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
		// TODO: Check whether a specific block can move.
		switch (direction) {
		case DOWN:
			if (currentBlock.getY() < this.height) {
				String lastLine = currentBlock.getPresentation()[currentBlock
						.getPresentation().length - 1];
				String lineBelowLastLine = gameBoard
						.get(currentBlock.getY() + currentBlock.getPresentation().length +1);
				for (int i = 0; i < lastLine.length(); i++) {
					if (lineBelowLastLine.charAt(currentBlock.getX() + i) == '#'
							|| lineBelowLastLine
									.charAt(currentBlock.getX() + i) == '-') {
						return false;
					}
				}
				return true;
			}
			return false;
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
	private void removeFilledRows() {
		// TODO: Search filled rows and remove them.
	}

	/**
	 * Moves the current block in a specified direction, if it is possible.
	 * 
	 * @param direction
	 *            A TetrisKey specifying the direction, in which the current
	 *            block should be moved. Possible Inputs: LEFT;RIGHT;DOWN;
	 */
	public void moveCurrentBlock(TetrisKey direction) {
		switch (direction) {
		case DOWN:
			currentBlock.setY(currentBlock.getY() + 1);
			break;
		case LEFT:
			currentBlock.setX(currentBlock.getX() - 1);
			break;
		case RIGHT:
			currentBlock.setX(currentBlock.getX() + 1);
			break;
		default:
			break;
		}
	}

	/**
	 * Turn the current block, if it is possible.
	 */
	public void turnCurrentBlock() {
		if (canCurrentBlockTurn()) {
			// TODO: Turn the current block;
		}
	}

	/**
	 * Update the position of the current block.
	 */
	private void updateCurrentBlockPosition() {
		if (canCurrentBlockMove(TetrisKey.DOWN)) {
			moveCurrentBlock(TetrisKey.DOWN);
		} else {
			playedBlocks.add(currentBlock);
			currentBlock = new Block();
		}
	}

	/**
	 * Get a BoardPresentation to visualize the current GameState.
	 * 
	 * @return The current BoardPresentation
	 */
	public BoardPresentation getGameBoard() {
		// TODO: Return correct BoardPresentation.
		gameBoard.clear();

		StringBuilder sb = new StringBuilder();
		sb.append(AnsiCodes.ANSI_CLS);
		gameBoard.add(HORLINE);

		for (int i = 0; i < height; i++) {
			if (currentBlock.getY() == i) {
				String[] presentation = currentBlock.getPresentation();
				for (int j = 0; j < presentation.length; j++) {
					char[] chars = LINE.toCharArray();
					for (int k = 0; k < presentation[j].length(); k++) {
						chars[currentBlock.getX() + k] = presentation[j]
								.charAt(k);
					}
					gameBoard.add(String.valueOf(chars));
					i++;
				}
			} else {
				gameBoard.add(LINE);
			}
		}
		gameBoard.add(HORLINE);

		// for (Block b : playedBlocks) {
		// String[] presentation = b.getPresentation();
		// for (int j = 0; j < presentation.length; j++) {
		// char[] chars = gameBoard.get(b.getY()+j).toCharArray();
		// for (int k = 0; k < presentation[j].length(); k++) {
		// chars[b.getX() + k] = presentation[j].charAt(k);
		// }
		// gameBoard.set(b.getX(), String.valueOf(chars));
		// }
		// }

		for (String s : gameBoard) {
			sb.append(s);
			sb.append("\n");
		}

		updateCurrentBlockPosition();
		removeFilledRows();
		return new BoardPresentation(sb.toString());
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
