import java.util.LinkedList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class CalcScanner
{
	private File source_file;
	private LinkedList <Token> tokens;
	private BufferedReader br;
	private static HashMap<String, Type> Token_Type = new HashMap<String, Type>();
	
	public CalcScanner(String source_file_path)
	{
		buildTokenMap();
		this.source_file = new File(source_file_path);
	}
	
	public CalcScanner(File source_file)
	{
		buildTokenMap();
		this.source_file = source_file;
	}
	
	public CalcScanner()
	{
		buildTokenMap();
		this.source_file = null;
	}

	public LinkedList<Token> scan()
	{	
		String input;
		String[] input_tokens;
		int line_number = 1;
		
		this.tokens = new LinkedList<Token>();
		try
		{
			this.br = new BufferedReader(new FileReader(this.source_file));
			
			while((input = this.br.readLine())!= null)
			{
				input_tokens=input.split(" ");
				
				for(String element: input_tokens)
				{
					Type type = Type.UNDEFINED; 
					if(Token_Type.containsKey(element))
						type = Token_Type.get(element);
					else if(isID(element))
					{
						type = Type.ID;
					}
					else if(isNum(element))
						type = Type.NUM;
					
					this.tokens.add(new Token(element, line_number, type));
				}

				line_number++;
			}
			this.br.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return this.tokens;
	}
	
	public void setFile(File source_file)
	{
		this.source_file = source_file;
	}
	
	public LinkedList<Token> getTokens()
	{
		return this.tokens;
	}
	
	private static void buildTokenMap()
	{
		Token_Type.put(":=", Type.STMT);
		Token_Type.put("read", Type.STMT);
		Token_Type.put("write", Type.STMT);
		Token_Type.put("+", Type.ADD_OP);
		Token_Type.put("-", Type.SUB_OP);
		Token_Type.put("*", Type.MULT_OP);
		Token_Type.put("/", Type.DIV_OP);
		Token_Type.put("(", Type.LEFTP);
		Token_Type.put(")", Type.RIGHTP);
	}
	
	private boolean isID(String element)
	{
		if(Character.isLetter(element.charAt(0)))
		{
			for(int i=1; i<element.length(); i++)
			{
				if(!Character.isLetter(element.charAt(i)) && !Character.isDigit(element.charAt(i)))
					return false;
			}
		}
		else return false;
		
		return true; 
	}
	
	private boolean isNum(String element)
	{
		int index = 0; 
		while(index < element.length() && element.charAt(index) != '.')
		{
			if(!Character.isDigit(element.charAt(index)))
				return false; 
			
			index++;
		}
		
		while(index < element.length())
		{
			if(!Character.isDigit(element.charAt(index)))
				return false; 
			
			index++;
		}
		
		return true; 
	}
}