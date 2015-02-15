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
			
			writer.write("import java.util.Scanner; \n public class " + file_name + "{ \n " +
					"public static void main(String[] args){ \n" +
					"Scanner scan = new Scanner(System.in);\n");
			Token prev_element = null;
			String helper = "";
			for(LinkedList<Token> element: statement_list)
			{
				if(element.get(0).getType() == Type.READ)
				{
					writer.write("double " + element.get(1).getToken() + ";\n" +
							element.get(1).getToken() + "= scan.nextDouble();\n");
				}
				
				else if(element.get(0).getType() == Type.WRITE)
				{
					writer.write("System.out.println(");
					
					for(int count = 1 ; count < element.size(); count++)
						writer.write(element.get(count).getToken());
					
					writer.write(");");
				}
				else if(element.get(0).getType() == Type.ID)
				{
					writer.write("double "+ element.get(0).getToken() + ";");
					writer.write(element.get(0).getToken() + "=");
					
					for(int count = 2 ; count < element.size(); count++)
						writer.write(element.get(count).getToken());
					
					writer.write(";");
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
