package at.tetris4j.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

import at.tetris4j.model.components.BoardPresentation;

public class TCPClient implements Runnable {

	private final int PORT = 3000;
	private InetAddress serverIp;
	private InetAddress localIp;
	private boolean inNetworkMode;
	private BoardPresentation boardPresentation;
	private BoardPresentation otherBoardPresentation;
	private Socket client;
	private PrintStream out;
	private BufferedReader in;
	private Thread networkThread;
	private boolean isRunning;

	public TCPClient(InetAddress serverIp) {
		try {
			client = new Socket(serverIp, PORT);
			localIp = client.getLocalAddress();
			System.out.println("Client connected ");
			out = new PrintStream(client.getOutputStream());
			in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
		} catch (Exception err) {
			System.err.println("* err" + err);
		}
		networkThread = new Thread(this);
		isRunning = true;
		networkThread.start();
	}

	@Override
	public void run() {
		while (isRunning) {
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
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
			isRunning = false;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Failed to close streams!");
		}
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public InetAddress getLocalIp() {
		return localIp;
	}

}
