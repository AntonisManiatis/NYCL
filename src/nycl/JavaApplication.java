package nycl;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author AnthonyManiatis
 */
public class JavaApplication
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        NYCLParser parser = new NYCLParser(new File("resources\\main.nycl"));
        parser.parse();
    }
}
