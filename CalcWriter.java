import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;


public class CalcWriter {
	
	private String file_name;
	private FileWriter writer;
	
	public CalcWriter(String file_name)
	{
		this.file_name = file_name;
	}
	
	public void write(LinkedList <LinkedList <Token>> statement_list)
	{
		try
		{
			writer = new FileWriter(new File(file_name + ".java"));
			
			writer.write("import java.util.Scanner; \n puplic class " + file_name + "{ \n " +
					"public static void main(String[] args){ \n" +
					"Scanner scan = new Scanner(System.in);\n");
			
			for(LinkedList<Token> list_element: statement_list)
			{
				for(Token element: list_element)
				{

				}
			}
			
			writer.write("}}");
			
			writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
