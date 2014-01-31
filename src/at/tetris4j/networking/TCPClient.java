package at.tetris4j.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import at.tetris4j.model.components.BoardPresentation;

public class TCPClient implements Runnable {

	private final int PORT = 3000;
	private InetAddress remoteIp;
	private InetAddress localIp;
	private BoardPresentation boardPresentation;
	private BoardPresentation otherBoardPresentation;
	private Socket client;
	private PrintStream out;
	private BufferedReader in;
	private Thread networkThread;
	private boolean isRunning;

	public TCPClient(InetAddress serverIp) {
		try {
			this.remoteIp = serverIp;
			client = new Socket(remoteIp, PORT);
			localIp = client.getLocalAddress();
			System.out.println("Successfully connected ");
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
	
	public TCPClient(){
		networkThread = new Thread(this);
		isRunning = true;
		networkThread.start();
	}

	@Override
	public void run() {
		if(remoteIp == null){
			waitForIncomingConnections();
		}
		
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

	private void waitForIncomingConnections() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT, 1, InetAddress.getLocalHost());
			System.out.println();
			System.out.println();
			System.out.println("Started listening socket at "+ serverSocket.getInetAddress()); 
			while(remoteIp == null){
				remoteIp = serverSocket.accept().getLocalAddress();
			}
			serverSocket.close();
			client = new Socket(remoteIp, PORT);
			localIp = client.getLocalAddress();
			isRunning = true;
			System.out.println("Successfully connected ");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public BoardPresentation getOtherBoardPresentation() {
		return otherBoardPresentation;
	}

	public void setBoardPresentation(BoardPresentation boardPresentation) {
		this.boardPresentation = boardPresentation;
	}

	public InetAddress getRemoteIp() {
		return remoteIp;
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
