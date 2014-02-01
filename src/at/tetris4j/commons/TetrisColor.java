package at.tetris4j.commons;

public enum TetrisColor {

	RED(16711680),
	GREEN(32768),
	YELLOW(16776960),
	BLUE(255),
	MAGENTA(16711935),
	CYAN(65535),
	WHITE(16777215);
	
	private int rgb;
	
	private TetrisColor(int rgb) {
		this.rgb = rgb;
	}
	
	public int getRGB() {
		return rgb;
	}
	
	public static TetrisColor getColorByRgb(int rgb) {
		
		TetrisColor[] values = TetrisColor.values();
		for (TetrisColor color : values) {
			if (color.getRGB() == rgb) {
				return color;
			}
		}
		
		return WHITE;
	}
}
