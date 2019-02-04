package nycl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

class NYCLParserTest
{
	@Test
	void testFileExtensionEqualsNycl()
	{
		String fileName = "main.nycl";
		
        String[] split = fileName.split("\\.");
        String extension = split[split.length - 1];
        assertEquals("nycl", extension);
	}
	
	@Test
	void testParseFileWithTxtExtension()
	{
		NYCLParser parser = new NYCLParser(new File("resources\\main.txt"));
		assertThrows(InvalidFileExtensionException.class, () -> parser.parse());
	}
}
