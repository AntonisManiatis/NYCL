package nycl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InterpreterTest
{
	Interpreter interpreter;
	
	final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	final PrintStream originalOut = System.out;
	
	@BeforeEach
	void setup()
	{
		interpreter = new Interpreter();
		// A cheeky way to track what's written in the console :)
	    System.setOut(new PrintStream(outContent));
	}
	
	@AfterEach
	public void restoreStreams()
	{
	    System.setOut(originalOut);
	}
	
	/**
	 * In order to get the correct results from the {@link #outContent} 
	 * we need to append the line separator to each value for the tests to pass
	 * @param value
	 * @return
	 */
	String check(String value)
	{
		return value + System.lineSeparator();
	}
	
	@Test
	void testGetVariableNameOutOfALine()
	{
		// Given this line
		String line = "aczxcz = 3 + 5";
        String[] variables = line.split("\\s*=");
        
        assertEquals("aczxcz", variables[0]);
	}
	
	@Test
	void testShowValueVariable()
	{
		String[] lines = {"value = 3 + 5", "show value"};
		for (String line : lines)
		{
			interpreter.interpret(line);
		}
		
		assertEquals(check("8"), outContent.toString());
	}
	
	@Test
	void testShowUndeclaredVariable()
	{
		// Given this line
		String line = "show a";
		assertThrows(UndeclaredVariableException.class, () -> interpreter.interpret(line));
	}
	
	@Test
	void testWrite()
	{
		String line = "write \"Hey how are you?\"";
		interpreter.interpret(line);
		assertEquals(check("Hey how are you?"), outContent.toString());
	}
	
	@Test
	void testWriteWithoutQuotes()
	{
		String line = "write Hey how are you?";
		assertThrows(SyntaxException.class, () -> interpreter.interpret(line));
	}
	
	@Test
	void testContainsUnwantedCharacters()
	{
		// Given this line
		String line = "a; = 3 + 5";
		assertThrows(SyntaxException.class, () -> interpreter.interpret(line));
	}
	
	@Test
	void testContainsDigitsAtTheStartOfTheLine()
	{
		// Given this line
		String line = "1a = 3 + 5";
		assertThrows(SyntaxException.class, () -> interpreter.interpret(line));
	}
	
	@Test
	void testExpressionWithUnknownOperator()
	{
		String line = "a = 3 % 6";
		assertThrows(SyntaxException.class, () -> interpreter.interpret(line));
	}
}
