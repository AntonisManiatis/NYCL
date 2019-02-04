package nycl;

import java.io.IOException;

/**
 *
 * @author AnthonyManiatis
 */
public class JavaApplication
{
    public static void main(String[] args) throws IOException
    {
        NYCLParser parser = new NYCLParser("resources\\main.nycl");
        parser.parse();
    }
}
