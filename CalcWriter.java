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
		LinkedList<String> init_vars = new LinkedList<String>();
		try
		{
			writer = new FileWriter(new File(file_name + ".java"));
			
			writer.write("import java.util.Scanner; \n public class " + file_name + "{ \n " +
					"public static void main(String[] args){ \n" +
					"Scanner scan = new Scanner(System.in);\n");
			for(LinkedList<Token> element: statement_list)
			{
				if(element.get(0).getType() == Type.READ)
				{
					if(init_vars.contains(element.get(1).getToken()))
						writer.write(element.get(1).getToken() + "= scan.nextDouble();\n");
					else
					{
						init_vars.add(element.get(1).getToken());
						writer.write("double " + element.get(1).getToken() + ";\n" +
								element.get(1).getToken() + "= scan.nextDouble();\n");
					}
					
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
					if(init_vars.contains(element.get(0).getToken()))
						writer.write(element.get(0).getToken() + "=");
					else
					{
						writer.write("double "+ element.get(0).getToken() + ";");
						writer.write(element.get(0).getToken() + "=");
						init_vars.add(element.get(0).getToken());
					}
					
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
