package at.tetris4j.resources;

/**
 * A resource class to store Ansi-Codes used for formating console output.
 * @author Manuel Zametter
 *
 */
public class AnsiCodes {
	public static final String ANSI_CLS = "\u001b[2J";
	public static final String ANSI_DELETE_LINE = "\u001b[K";
	public static final String ANSI_CURSOR_UP = "\u001b[A";
	public static final String ANSI_CURSOR_DOWN = "\u001b[B";
	public static final String ANSI_CURSOR_FORWARD = "\u001b[C";
	public static final String ANSI_CURSOR_BACKWARD = "\u001b[D";
	public static final String ANSI_SAVE_CURSORPOS = "\u001b[s";
	public static final String ANSI_RESORE_CURSORPOS = "\u001b[u";
	public static final String ANSI_HOME = "\u001b[H";
	public static final String ANSI_REVERSEON = "\u001b[7m";
	
	public static final String ANSI_BOLD = "\u001b[1m";
	public static final String ANSI_AT55 = "\u001b[10;10H";
	public static final String ANSI_NORMAL = "\u001b[0m";
	
	public static final String ANSI_FOREGROUND_BLACK = "\u001b[30";
	public static final String ANSI_FOREGROUND_RED = "\u001b[31";
	public static final String ANSI_FOREGROUND_GREEN = "\u001b[32";
	public static final String ANSI_FOREGROUND_YELLOW = "\u001b[33";
	public static final String ANSI_FOREGROUND_BLUE = "\u001b[34";
	public static final String ANSI_FOREGROUND_MAGENTA = "\u001b[35";
	public static final String ANSI_FOREGROUND_CYAN = "\u001b[36";
	public static final String ANSI_FOREGROUND_WHITE = "\u001b[37";
	
	public static final String ANSI_BACKGROUND_BLACK = "\u001b[40";
	public static final String ANSI_BACKGROUND_RED = "\u001b[41";
	public static final String ANSI_BACKGROUND_GREEN = "\u001b[42";
	public static final String ANSI_BACKGROUND_YELLOW = "\u001b[43";
	public static final String ANSI_BACKGROUND_BLUE = "\u001b[44";
	public static final String ANSI_BACKGROUND_MAGENTA = "\u001b[45";
	public static final String ANSI_BACKGROUND_CYAN = "\u001b[46";
	public static final String ANSI_BACKGROUND_WHITE = "\u001b[47";
	
	/**
	 * Builds the Ansi-Code to set the cursor to any desired position.
	 * @param line
	 * The number of the line where you want to place the cursor.
	 * @param column
	 * The number of the line where you want to place the cursor.
	 * @return
	 * The Ansi-Code to set the cursor to any desired position.
	 */
	public static String ANSI_SET_CURSORPOS(int line, int column){
		StringBuilder sb = new StringBuilder();
		sb.append("\u001b[");
		sb.append(line);
		sb.append(';');
		sb.append(column);
		sb.append('H');
		return sb.toString();
	}
}
