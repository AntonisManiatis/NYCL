package nycl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InterpreterTest
{
	Interpreter interpreter = new Interpreter();
	
	@Test
	void testGetVariableNameOutOfALine()
	{
		// Given this line
		String line = "aczxcz = 3 + 5";
        String[] variables = line.split("\\s*=");
        
        assertEquals("aczxcz", variables[0]);
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
	void testShowUndeclaredVariable()
	{
		// Given this line
		String line = "show a";
		assertThrows(UndeclaredVariableException.class, () -> interpreter.interpret(line));
	}
}
