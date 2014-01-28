package at.tetris4j.model.components;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import at.tetris4j.resources.AnsiCodes;
import at.tetris4j.view.utils.TetrisKey;

/**
 * Class representing a simple Tetris-GameBoard.
 * 
 * @author Manuel Zametter
 * 
 */
public class GameBoard {
	private static final int REFRESH_RATE = 500;
	private static final String LINE = "|                                    |";
	private static final String HORLINE = "--------------------------------------";
	private int width;
	private int height;
	private Block currentBlock;
	private ArrayList<String> gameBoard;
	private ArrayList<String> oldBoard;
	private Timer updateTimer;
	private BoardPresentation boardPresentation;

	public GameBoard(int height) {
		oldBoard = new ArrayList<String>();
		this.height = height;
		currentBlock = new Block();
		initializeGameBoard(height);
		initializeUpdateTimer();
	}

	private void initializeUpdateTimer() {
		updateTimer = new Timer();
		updateTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				updateCurrentBlockPosition();
				removeFilledRows();
			}
		}, REFRESH_RATE, REFRESH_RATE);
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
				int start = currentBlock.getX();
				int end = currentBlock.getWidth() + currentBlock.getX();
				String lineBelowLastLine = gameBoard.get(
						currentBlock.getY()
								+ currentBlock.getPresentation().length + 1)
						.substring(start, end);
				if (lineBelowLastLine.contains("#")
						|| lineBelowLastLine.contains("-")) {
					currentBlock = new Block();
					oldBoard = new ArrayList<String>(gameBoard);
					return false;
				}
				return true;
			}
			return false;
		case LEFT:
			if (currentBlock.getX() - 1 > 0) {
				return true;
			}
			return false;
		case RIGHT:
			if (currentBlock.getX() + currentBlock.getWidth() < LINE.length() - 1) {
				return true;
			}
			return false;
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
		if (canCurrentBlockMove(direction)) {
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
		updateBoardPresentation();
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

		StringBuilder sb = new StringBuilder();
		sb.append(AnsiCodes.ANSI_CLS);
		gameBoard = new ArrayList<String>(oldBoard);
		String[] presentation = currentBlock.getPresentation();

		for (int j = 0; j < presentation.length; j++) {
			char[] chars = oldBoard.get(currentBlock.getY() + 1 +j).toCharArray();

			for (int k = 0; k < presentation[j].length(); k++) {
				chars[currentBlock.getX() + k] = presentation[j].charAt(k);
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
}
