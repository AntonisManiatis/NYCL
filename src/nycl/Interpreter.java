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
            String[] variables = line.split("\\s+");
            String variable = variables[0];
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
            	int value1 = Integer.parseInt(variables[2]);
            	String operator = variables[3];
            	int value2 = Integer.parseInt(variables[4]);
            	
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
