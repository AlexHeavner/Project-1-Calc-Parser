import java.util.LinkedList;
import java.util.Iterator;


public class CalcParser 
{
	private LinkedList<Token> tokens;
	private Iterator<Token> iterator;
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
	
	public boolean parse()
	{
		if(expression() && current_token == null)
		{
			return true;
		}
		else return false;
	}

	//this method is not done
	private boolean match(Type expected)
	{
		if(current_token.getType() == expected)
		{
			getNextToken();
			return true;
		}
		else
		{
			error(expected, current_token.getType());
			return false;
		}
	}

	private void getNextToken()
	{
		if(iterator.hasNext())
			current_token = iterator.next();
		else
			current_token = null;
	}

	private static void error(Type expected, Type found)
	{
		System.out.println("Error: expected "+ expected +". Found: "+found);
	}
	
	private boolean expression()
	{
		if(term() && termTail())
			return true;
		else
		{
			return false;
		}
	}

	private boolean term()
	{
		if(factor() && factorTail())
			return true; 
		else 
		{
			//error();
			return false; 
		}
	}
	
	private boolean termTail()
	{
		if(current_token.getType() == Type.ADD_OP || current_token.getType() == Type.SUB_OP)
			return((match(Type.ADD_OP)||match(Type.SUB_OP)) && term() && termTail());
		else
			return true;
	}
	
	private boolean factor()
	{
		if(current_token.getType() == Type.LEFTP)
			return (match(Type.LEFTP) && expression() && match(Type.RIGHTP));
		else if(current_token.getType() == Type.ID)
			return match(Type.ID);
		else if(current_token.getType() == Type.NUM)
			return match(Type.NUM);
		else
		{
			return false;
		}	
	}

	private boolean factorTail()
	{
		if(current_token.getType() == Type.MULT_OP)
			return match(Type.MULT_OP) && factor() && factorTail();
		else if(current_token.getType() == Type.DIV_OP)
			return match(Type.DIV_OP) && factor() && factorTail();
		else return true;
	}
}
