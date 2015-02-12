import java.util.LinkedList;
import java.util.Iterator;


public class CalcParser 
{
	private LinkedList<Token> tokens;
	private Iterator iterator;
	private Token current_token; 
	
	public CalcParser(LinkedList<Token> tokens)
	{
		this.tokens = tokens;
		iterator = tokens.listIterator();

		if(iterator.hasNext())
			current_token = iterator.next();
		else
			current_token = null;
	}
	
	public void parse()
	{
		for(Token element: tokens)
		{
			
		}
	}

	private void getNextToken()
	{
		if(iterator.hasNext())
			current_token = iterator.next();
		else
			current_token = null;
	}
	
	//this method is not done
	private boolean match(Type expected)
	{
		if(this.type == expected)
		{
			getNextToken();
			return true;
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
			return match(NUM);
		else
		{
			error();
			return false;
		}
				
	}
}
