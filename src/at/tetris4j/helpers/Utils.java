package at.tetris4j.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
	public static String[] readLines(BufferedReader bufferedReader) {
		List<String> lines = new ArrayList<String>();
		try {
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}
			bufferedReader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return lines.toArray(new String[lines.size()]);
	}
}
