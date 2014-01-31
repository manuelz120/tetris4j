package at.tetris4j.model;

import java.net.InetAddress;

import at.tetris4j.model.components.BoardPresentation;
import at.tetris4j.model.components.GameBoard;
import at.tetris4j.networking.TCPClient;

public class GameModel implements IModel {

	private static final int _HEIGHT = 20;
	private GameBoard gameBoardPlayer1;
	private BoardPresentation otherBoardPresentation;
	private boolean isMultiplayer;
	private TCPClient tcpClient;

	public GameModel(){
		this.gameBoardPlayer1 = new GameBoard(20);
		otherBoardPresentation = new BoardPresentation("");
	}
	
	@Override
	public void updateGame() {
		gameBoardPlayer1.updateGameBoard();
		if(isMultiplayer){
			tcpClient.setBoardPresentation(gameBoardPlayer1.getBoardPresentation());
			otherBoardPresentation = tcpClient.getOtherBoardPresentation();
		}		
	}
	
	@Override
	public BoardPresentation getGameBoard() {
		return this.gameBoardPlayer1.getBoardPresentation();
	}

	@Override
	public void moveLeft() {
		gameBoardPlayer1.moveLeft();
	}

	@Override
	public void moveRight() {
		gameBoardPlayer1.moveRight();
	}

	@Override
	public void turn() {
		gameBoardPlayer1.turnCurrentBlock();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void stop() {
	}

	@Override
	public void moveDown() {
		gameBoardPlayer1.moveDown();
	}

	@Override
	public void startNewGame() {
		this.gameBoardPlayer1 = new GameBoard(_HEIGHT);		
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
		tcpClient.setBoardPresentation(gameBoardPlayer1.getBoardPresentation());
	}

	@Override
	public void startNewMultiplayerGame() {
		startNewGame();
		isMultiplayer = true;
		tcpClient = new TCPClient();
		tcpClient.setBoardPresentation(gameBoardPlayer1.getBoardPresentation());
	}

	@Override
	public boolean isConnectionEstablished() {
		if(tcpClient == null)
			return false;
		return tcpClient.isConnectionEstablished();
	}

}
