package at.tetris4j.view;

import org.fusesource.jansi.AnsiConsole;

import at.tetris4j.resources.AnsiCodes;
import at.tetris4j.view.utils.TetrisKey;

public class TetrisView implements IView {
	
	private static final int _GAMEHEIGHT = 30;
	private static final String LINE = "|                                    |";
	private int posX;
	private int posY;
	

	public TetrisView(){
		posX = 5;
		posY = 0;
	}
	
	@Override
	public void render(){
		updateScreen();
	}
	
	@Override
	public void KeyPressed(TetrisKey key) {
		// TODO Auto-generated method stub
		
	}
	
	private void updateScreen(){
		String horLine = "--------------------------------------";

		while (true) {
			StringBuilder sb = new StringBuilder();
			
			sb.append(AnsiCodes.ANSI_CLS);
			sb.append("\n");
			sb.append(horLine);
			sb.append("\n");
			
			for (int i = 0; i < _GAMEHEIGHT; i++) {
				if (posY % _GAMEHEIGHT == i) {
					char[] chars = LINE.toCharArray();
					chars[posX] = 'X';
					sb.append(chars);
					sb.append("\n");
				} else {
					sb.append(LINE);
					sb.append("\n");
				}
			}
			sb.append(horLine);
			sb.append("\n");
			
			AnsiConsole.out.println(sb.toString());
			
			posY++;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void moveLeft() {
		posX--;
	}
	
	@Override
	public void moveRight() {
		posX++;
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
}
