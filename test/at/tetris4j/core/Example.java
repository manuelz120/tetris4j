package at.tetris4j.core;

import java.io.Serializable;

import org.junit.Test;
import static org.mockito.Mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Florian Genser
 *
 */
public class Example {

	// Instantiates a logger for this class.
	private static final Logger logger = LoggerFactory.getLogger(Example.class);
	
	@Test
	public void test() {
		// mock and when are static imported Mockito methods
		// use of Mockito to get a Stub (Test Double) of an Interface
		Serializable myMockedObject = mock(Serializable.class);
		// defines the return value for the toString()
		when(myMockedObject.toString()).thenReturn("Custom Value");
		
		System.out.println(myMockedObject);
		
		// use of placeholder in log message. with this unnecessary string concat can be prevented.
		logger.warn("Logging funktioniert. Platzhalter *{}*", "eingefügt");
		
	}
}
