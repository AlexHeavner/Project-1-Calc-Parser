import java.util.LinkedList;


public class Test 
{
	public static void main(String[] args)
	{
		String valid_program = "myprog.calc";
		String invalid_program = "badprog.calc";

		CalcScanner scanner = new CalcScanner(valid_program);
		
		LinkedList<Token> token_list = scanner.scan();
		
		for(Token element: token_list)
			System.out.println(element.getToken() + " "+element.getType() + " " +element.getLine());
		
		System.out.println("Scanning Complete, beginning to Parse.");

		CalcParser parser = new CalcParser(token_list);

		if(parser.parse())
			System.out.println("The program is valid.");
		else 
			System.out.println("The program is invalid");

	}
}
