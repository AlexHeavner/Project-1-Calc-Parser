import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


public class CalcScanner {
	private File source_file;
	private LinkedList <String>tokens;
	private BufferedReader br;
	
	public LinkedList<String> scan()
	{	
		String input;
		String file_contents = "";

		try
		{
			this.br = new BufferedReader(new FileReader(this.source_file));
			
			//adds extra space at end of input so it will split correctly
			while((input = this.br.readLine())!= null)
				file_contents = file_contents + input + " ";
			
			this.br.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		this.tokens = new LinkedList<String>();
		
		String[] token_array = file_contents.split(" +");
		
		for(String element : token_array)
			this.tokens.add(element);
		
		return this.tokens;
	}
	
	public CalcScanner(String source_file_path)
	{
		this.source_file = new File(source_file_path);
	}
	
	public CalcScanner(File source_file)
	{
		this.source_file = source_file;
	}
	
	public CalcScanner()
	{
		this.source_file = null;
	}
	
	public void setFile(File source_file)
	{
		this.source_file = source_file;
	}
	
	public LinkedList<String> getTokens()
	{
		return this.tokens;
	}
}
