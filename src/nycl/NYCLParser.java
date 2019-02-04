package nycl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author AnthonyManiatis
 */
public class NYCLParser
{
    private static final String FILE_EXTENSION = "nycl";
    
    private final File file;
    private final Interpreter interpreter = new Interpreter();
    
    public NYCLParser(String fileName)
    {
		this(new File(fileName));
	}
    
    public NYCLParser(File file)
    {
        this.file = file;
    }
    
    public void parse() throws FileNotFoundException, IOException
    {
        String[] parts = file.getName().split("\\.");
        String extension = parts[parts.length - 1];
        if (!extension.equals(FILE_EXTENSION))
        {
        	throw new InvalidFileExtensionException("Can only parse files with .nycl extension!");
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
        	String currentLine = null;
			while ((currentLine = br.readLine()) != null)
			{
				interpreter.interpret(currentLine);
			}
        }
    }
}
