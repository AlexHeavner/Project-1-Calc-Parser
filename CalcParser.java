import java.util.LinkedList;
import java.util.Iterator;


public class CalcParser 
{
	private LinkedList<LinkedList<Token>> statement_list;
	private LinkedList<String> error_list;
	private Iterator<LinkedList<Token>> statement_iterator;
	private Iterator<Token> token_iterator;
	private LinkedList<Token> current_statement;
	private Token current_token; 
	
	public CalcParser(LinkedList<LinkedList<Token>> statements)
	{
		statement_list = statements;
		statement_iterator = statement_list.listIterator();
		error_list = new LinkedList<String>();

		//load first token on first line
		if(statement_iterator.hasNext())
		{
			current_statement = statement_iterator.next();
			token_iterator = current_statement.iterator();

			if(token_iterator.hasNext())
				current_token = token_iterator.next();
			else
				current_token = null;
		}

		System.out.println("First token: "+current_token.getToken());
	}
	
	public boolean parse_statements()
	{
		boolean valid_flag = true; 
		while(statement_iterator.hasNext())
		{
			if(parse());
				//System.out.println("valid statement");
			else
			{
				//System.out.println("invalid statement");
				
				error_list.add("Syntax Error on Line " + current_statement.get(0).getLine());
				
				valid_flag = false;
			}
				
			current_statement = statement_iterator.next();
			token_iterator = current_statement.iterator();

			//load first token of next statement
			if(token_iterator.hasNext())
				current_token = token_iterator.next();
			else
				current_token = null;
		}
		return valid_flag;
	}

	private boolean parse()
	{
		if(statement() && current_token == null)
		{
			return true;
		}
		else
		{
			return false;
		} 
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
		if(token_iterator.hasNext())
		{
			current_token = token_iterator.next();
			System.out.println("next token is: "+ current_token.getToken());

		}
		else
			current_token = null;
	}

	private static void error(Type expected, Type found)
	{
		System.out.println("Error: expected "+ expected +". Found: "+found);
	}

	private boolean statementList()
	{
		if(statement() || statementList())
			return true;
		else 
			return false;
	}

	private boolean statement()
	{
		if(current_token.getType() == Type.ID)
			return (match(Type.ID) && match(Type.STMT) && expression());
		else if(current_token.getType() == Type.READ)
			return (match(Type.READ) && match(Type.ID));
		else if(current_token.getType() == Type.WRITE)
			return (match(Type.WRITE) && expression());
		else 
			return false; 
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
		if(current_token == null)
			return true;
		else if(current_token.getType() == Type.ADD_OP )
			return(match(Type.ADD_OP) && term() && termTail());
		else if(current_token.getType() == Type.SUB_OP)
			return(match(Type.SUB_OP) && term() && termTail());
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
		if(current_token == null)
			return true;
		else if(current_token.getType() == Type.MULT_OP)
			return match(Type.MULT_OP) && factor() && factorTail();
		else if(current_token.getType() == Type.DIV_OP)
			return match(Type.DIV_OP) && factor() && factorTail();
		else 
			return true;
	}
	
	public LinkedList<String> getErrorList()
	{
		return error_list;
	}
}
