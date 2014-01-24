package at.tetris4j.view;

public interface View {

	void render();
	
	@Deprecated
	int getPosX();
	
	@Deprecated
	void setPosX(int x);
	
	@Deprecated
	int getPosY();
	
	@Deprecated
	void setPosY(int y);
}
