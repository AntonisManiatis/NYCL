package nycl.operation;

/**
 * 
 * @author AnthonyManiatis
 */
public class Subtraction implements Operation
{
	@Override
	public int execute(int value1, int value2)
	{
		return value1 - value2;
	}
}
