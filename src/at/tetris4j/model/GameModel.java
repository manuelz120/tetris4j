package at.tetris4j.model;

import java.net.InetAddress;

import at.tetris4j.model.components.BoardPresentation;
import at.tetris4j.model.components.GameBoard;
import at.tetris4j.net.TCPClient;

public class GameModel implements IModel {

	private static final int _HEIGHT = 20;
	private GameBoard gameBoardPlayer;
	private BoardPresentation otherBoardPresentation;
	private boolean isMultiplayer;
	private TCPClient tcpClient;

	public GameModel(){
		this.gameBoardPlayer = new GameBoard(20);
		otherBoardPresentation = new BoardPresentation("");
	}
	
	@Override
	public boolean isGameOver() {
		return gameBoardPlayer.isGameOver();
	}
	
	@Override
	public void updateGame() {
		gameBoardPlayer.updateGameBoard();
		if(isMultiplayer){
			tcpClient.setBoardPresentation(gameBoardPlayer.getBoardPresentation());
			otherBoardPresentation = tcpClient.getOtherBoardPresentation();
		}
	}
	
	@Override
	public BoardPresentation getGameBoard() {
		return this.gameBoardPlayer.getBoardPresentation();
	}

	@Override
	public void moveLeft() {
		gameBoardPlayer.moveLeft();
	}

	@Override
	public void moveRight() {
		gameBoardPlayer.moveRight();
	}

	@Override
	public void turn() {
		gameBoardPlayer.turnCurrentBlock();
	}

	@Override
	public void pauseGame() {
	}

	@Override
	public void resumeGame() {
	}

	@Override
	public void stopGame() {
	}

	@Override
	public void moveDown() {
		gameBoardPlayer.moveDown();
	}

	@Override
	public void startNewGame() {
		this.gameBoardPlayer = new GameBoard(_HEIGHT);		
	}

	@Override
	public BoardPresentation getOtherBoardPresentation() {
		return this.otherBoardPresentation;
	}

	@Override
	public void startNewMultiplayerGame(InetAddress ip) {
		startNewGame();
		isMultiplayer = true;
		tcpClient = new TCPClient(ip);
		tcpClient.setBoardPresentation(gameBoardPlayer.getBoardPresentation());
	}

	@Override
	public void startNewMultiplayerGame() {
		startNewGame();
		isMultiplayer = true;
		tcpClient = new TCPClient();
		tcpClient.setBoardPresentation(gameBoardPlayer.getBoardPresentation());
	}

	@Override
	public boolean isConnectionEstablished() {
		if(tcpClient == null)
			return false;
		return tcpClient.isConnectionEstablished();
	}

}
