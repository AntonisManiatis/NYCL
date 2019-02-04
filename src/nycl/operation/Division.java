package nycl.operation;

/**
 * 
 * @author AnthonyManiatis
 */
public class Division implements Operation
{
	@Override
	public int execute(int value1, int value2)
	{
		if (value2 == 0)
		{
			throw new ArithmeticException("Cannot divide by zero!");
		}
		
		return value1 / value2;
	}
}
