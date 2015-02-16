import java.util.LinkedList;


public class CalculatorLanguage {
	
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
			
			String[] temp = calc_prog.split("\\W");
			String prog = temp[temp.length - 2];
			
			CalcWriter writer = new CalcWriter(prog);
			writer.write(statement_list);
		}
		//Print errors if program is not valid.
		else
		{
			for(String element: parser.getErrorList())
				System.out.println(element);
		}
	}
}
