package at.tetris4j.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.pattern.Util;

public class Utils {
	public static String[] readLines(String file) {
		InputStream in = Util.class.getClass().getResourceAsStream(file);
		BufferedReader input = new BufferedReader(new InputStreamReader(in));
		List<String> lines = new ArrayList<String>();
		try {
			String line = null;
			while ((line = input.readLine()) != null) {
				lines.add(line);
			}
			input.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return lines.toArray(new String[lines.size()]);
	}
}
