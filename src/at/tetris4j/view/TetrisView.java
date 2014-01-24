package at.tetris4j.view;

import org.fusesource.jansi.AnsiConsole;

import at.tetris4j.resources.AnsiCodes;

public class TetrisView implements View {
	
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
	
	private void updateScreen(){

		while (true) {
			AnsiConsole.out.println(AnsiCodes.ANSI_CLS);
			AnsiConsole.out.println("--------------------------------------");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 30; i++) {
				if (posY % 30 == i) {
					char[] chars = LINE.toCharArray();
					for(int j = 0; j<chars.length; j++){
						if(j==posX){
							sb.append('X');
						}else{
							sb.append(chars[j]);
						}
					}
					AnsiConsole.out.println(sb.toString());
				} else {
					AnsiConsole.out
							.println(LINE);
				}
			}
			AnsiConsole.out.println("--------------------------------------");
			posY++;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
