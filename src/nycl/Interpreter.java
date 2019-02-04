package nycl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import nycl.operation.Addition;
import nycl.operation.Division;
import nycl.operation.Multiplication;
import nycl.operation.Operation;
import nycl.operation.Subtraction;

/**
 * Goes through every given line and interprets it.
 * @author AnthonyManiatis
 */
public class Interpreter
{
    private static final String COMMENT_CHARACTER = "#";
    private static final String VAR_REGEX = "^[a-zA-Z0-9_]*$";
    
    private final Map<String, Operation> operations = new HashMap<>();
    private final Map<String, Integer> variables = new HashMap<>();
    
    public Interpreter()
    {
    	operations.put("+", new Addition());
    	operations.put("-", new Subtraction());
    	operations.put("*", new Multiplication());
    	operations.put("/", new Division());
	}
    
	public void interpret(String line)
	{
		if (line == null || line.isEmpty())
		{
			return;
		}
		
		// Comments are indicated with the # character and can only be single line comments.
        if (line.startsWith(COMMENT_CHARACTER))
        {
        	return;
        }

        if (line.startsWith("show"))
        {
        	try
        	{
        		String variable = line.split("\\s+")[1];          	
            	if (variables.containsKey(variable))
            	{
                	System.out.println(variables.get(variable));
            	}
            	else
            	{            		
            		throw new UndeclaredVariableException("Variable " + variable + " has not been declared!");
            	}
        	}
        	catch (ArrayIndexOutOfBoundsException e)
        	{
        		// Either it's stuck to show or show is left empty
        		throw new SyntaxException("Wrong use of syntax for the show function.");
        	}
        }
        else if (line.startsWith("write"))
        {
        	try
        	{
        		String textToPrint = line.split(Pattern.quote("\""))[1];         	
            	System.out.println(textToPrint);
        	}
        	catch (Exception e)
        	{
        		throw new SyntaxException("Make your text is inside quotes");
        	}
        }
        else
        {
            String[] expression = line.split("\\s*=\\s*");
            // The left hand side of the expression which contains the variable
            String variable = expression[0];
            if (Character.isDigit(variable.charAt(0)))
            {
            	throw new SyntaxException("Variables cannot start with digits.");
            }
            
            // Variable names (identifiers) can consist of any number of latin characters,
            // upper or lower case, digits, and the underscore (_). 
            // The first letter of an identifier cannot be a digit.
            if (variable.matches(VAR_REGEX))
            {
            	// The format of any expected line here is expected and it is
            	// num1, any sign, num2
            	// TODO: Ideally here we need a regex to check the right hand side
            	// of the variable and see if the format is correct.
            	String[] rightHandSide = expression[1].split("\\s+");
	            int value1 = Integer.parseInt(rightHandSide[0]);
	            String operator = rightHandSide[1];
	            int value2 = Integer.parseInt(rightHandSide[2]);
	            	            	
	            Operation operation = operations.get(operator);
	            if (operation == null)
	            {
	            	throw new SyntaxException("Operation " + operator + " is unknown!");
	            }
	            
	            // Calculate and store the result for this variable.
	            this.variables.put(variable, operation.execute(value1, value2));
            }
            else
            {
                throw new SyntaxException("Variables can only contain latin characters, digits and underscores.");
            }
        }
	}
}
