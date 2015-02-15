import java.util.LinkedList;

public class CalculatorLanguage
{
	public CalculatorLanguage(String calc_prog)
	{
		//Scan calc program
		CalcScanner scanner = new CalcScanner(calc_prog);
		LinkedList<LinkedList<Token>> statement_list = scanner.scan();

		//Parse calc program
		CalcParser parser = new CalcParser(statement_list);

		//If the parser finds no errors, write the Program to Java "myprog"
		if(parser.parse_statements())
		{
			System.out.println("The program is valid.");

			CalcWriter writer = new CalcWriter("myprog");
			writer.write(statement_list);
		}
		//Otherwise print out the syntax errors
		else 
		{
			System.out.println("The program is invalid");
		} 
	}
}