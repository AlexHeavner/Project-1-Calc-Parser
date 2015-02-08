import java.util.LinkedList;


public class CalcParser {
	private LinkedList<Token> tokens;
	
	public CalcParser(LinkedList<Token> tokens)
	{
		this.tokens = tokens;
	}
	
	public void parse()
	{
		for(Token element: tokens)
		{
			
		}
	}
	
	private boolean expression()
	{
		if(termTail() && term())
			return true;
		else
			return false; //error
	}
	
	private boolean termTail()
	{
		if(addOp())
			return((match(Type.ADD_OP)||match(Type.SUB_OP)) && term() && termTail());
		else
			return true;
	}
	
	private boolean term()
	{
		
	}
	
	private boolean addOp()
	{
		return 
	}
	
	private boolean match(Type expected)
	{
		
	}
	
}
