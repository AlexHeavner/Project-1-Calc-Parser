
public class Token{
	private String type;
	private String token;
	private int line_number;
	
	public Token(String token,int line_number)
	{
		this.token = token;
		this.type = "undefined";
		this.line_number = line_number;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getToken()
	{
		return token;
	}
	
	public String getType()
	{
		return type;
	}
	public int getLine()
	{
		return line_number;
	}
}
