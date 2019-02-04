package nycl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import nycl.operation.Addition;
import nycl.operation.Division;
import nycl.operation.Multiplication;
import nycl.operation.Subtraction;

class OperationTest
{
	@Test
	void testAddition()
	{
		var operation = new Addition();
		assertEquals(8, operation.execute(5, 3));
	}
	
	@Test
	void testSubtraction()
	{
		var operation = new Subtraction();
		assertEquals(25, operation.execute(100, 75));
	}
	
	@Test
	void testMultiplication()
	{
		var operation = new Multiplication();
		assertEquals(1024, operation.execute(512, 2));
	}
	
	@Test
	void testDivision()
	{
		var operation = new Division();
		assertEquals(256, operation.execute(512, 2));
	}
	
	@Test
	void testDivideByZero()
	{
		var operation = new Division();
		assertThrows(ArithmeticException.class, () -> operation.execute(500, 0));
	}
}
