public class Token
{
	private Type type;
	private String token;
	private int line_number;
	
	public Token(String token,int line_number, Type t)
	{
		this.token = token;
		this.type = t;
		this.line_number = line_number;
	}
	
	public void setType(Type type)
	{
		this.type = type;
	}
	
	public String getToken()
	{
		return token;
	}
	
	public Type getType()
	{
		return type;
	}
	public int getLine()
	{
		return line_number;
	}
}
