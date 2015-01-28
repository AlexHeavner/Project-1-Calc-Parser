private File source_file;
	private LinkedList <Token> tokens;
	private BufferedReader br;
	
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
					this.tokens.add(new Token(element, line_number));

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
	
	public LinkedList<Token> getTokens()
	{
		return this.tokens;
	}
}
