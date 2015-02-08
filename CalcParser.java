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
	
	//this method is not done
	private boolean match(Type expected)
	{
		if(this.type == expected)
			return true;
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
		if(Factor() && FactorTail())
			return true; 
		else 
		{
			error();
			return false; 
		}
	}
	
	private boolean factor()
	{
		if(this.type == LEFTP)
			return (match(LEFTP) && match(E) && match(RIGHTP));
		else if(this.type == ID)
			return match(ID);
		else if(this.type == NUM)
			return match(NUM)
		else
		{
			error();
			return false;
		}
				
	}
	
	private boolean addOp()
	{
		return 
	}
}
