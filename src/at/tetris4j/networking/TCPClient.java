package at.tetris4j.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

import at.tetris4j.model.components.BoardPresentation;

public class TCPClient {

	private final int PORT = 3000;
	private InetAddress serverIp;
	private boolean inNetworkMode;
	private BoardPresentation boardPresentation;
	private BoardPresentation otherBoardPresentation;
	private Socket client;
	private PrintStream out;
	private BufferedReader in;

	public TCPClient(InetAddress serverIp) {
		try {
			client = new Socket(serverIp, PORT);
			System.out.println("Client connected ");
			out = new PrintStream(client.getOutputStream());
			in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
		} catch (Exception err) {
			System.err.println("* err" + err);
		}
	}

	public void run() {
		out.print(boardPresentation.toString());
		out.flush();
		// reading the response using input stream
		StringBuilder sb = new StringBuilder();
		try {
			String s;
			while ((s = in.readLine()) != null) {
				sb.append(s);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Failed to read inputStream!");
		}
		otherBoardPresentation = new BoardPresentation(sb.toString());
	}

	public boolean isInNetworkMode() {
		return inNetworkMode;
	}

	public void setInNetworkMode(boolean inNetworkMode) {
		this.inNetworkMode = inNetworkMode;
	}

	public BoardPresentation getOtherBoardPresentation() {
		return otherBoardPresentation;
	}

	public void setBoardPresentation(BoardPresentation boardPresentation) {
		this.boardPresentation = boardPresentation;
	}

	public InetAddress getServerIp() {
		return serverIp;
	}
	
	public void closeStreams() {
		try {
			in.close();
			out.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Failed to close streams!");
		}
	}

}
