import java.util.LinkedList;
import java.util.Iterator;


public class Test 
{
	public static void main(String[] args)
	{
		String valid_program = "myprog.calc";
		String invalid_program = "badprog.calc";

		CalcScanner scanner = new CalcScanner(invalid_program);
		
		LinkedList<LinkedList<Token>> statement_list = scanner.scan();

		Iterator<LinkedList<Token>> statement_it = statement_list.iterator();

		while(statement_it.hasNext())
		{
			//each statement
			Iterator<Token> tokens_it = statement_it.next().iterator();

			//each token
			while(tokens_it.hasNext())
			{
				Token token = tokens_it.next();
				System.out.println(token.getToken() + " "+token.getType() + " " +token.getLine());
			}
		}

		System.out.println("Scanning Complete, beginning to Parse.");

		//reset iterator
		Iterator<LinkedList<Token>> statement_it2 = statement_list.iterator();
		
		while(statement_it2.hasNext())
		{
			//each statement
			LinkedList<Token> statement = statement_it2.next();
			int line_num = statement.get(0).getLine();
			CalcParser parser = new CalcParser(statement);

			if(parser.parse())
				System.out.println("The statement is valid.");
			else 
				System.out.println("Syntax error on line "+ line_num);
			
		}

		/*
		CalcParser parser = new CalcParser(statement_list);

		if(parser.parse())
			System.out.println("The program is valid.");
		else 
			System.out.println("The program is invalid");
		*/
	}
}
