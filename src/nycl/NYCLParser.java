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
    private static final String[] RESERVED_KEYWORDS = {"show",  "write"};
    
    private static final String VAR_REGEX = "";
    
    private final File file;
    
    public NYCLParser(File file)
    {
        this.file = file;
    }
    
    public void parse() throws FileNotFoundException, IOException
    {
        if (file.exists())
        {
            System.out.println("file exists");
        }
        else
        {
            System.out.println("file doesn't exist");
        }
        
        String[] split = file.getName().split("\\.");
        String extension = split[split.length - 1];
        if (extension.equals(FILE_EXTENSION))
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            // Variable names (identifiers) can consist of any number of latin characters,
            // upper or lower case, digits, and the underscore (_). 
            // The first letter of an identifier cannot be a digit.
            String[] variables = line.split("\\s*=");
            String variable = variables[0];
            
            if (variable.matches(VAR_REGEX))
            {
                
            }
            else
            {
                
            }
        }
        else
        {
            System.out.println("wrong extention");
        }
    }
}
