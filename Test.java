import java.util.LinkedList;


public class Test {
	public static void main(String[] args)
	{
		CalcScanner scanner = new CalcScanner("myprog.calc");
		
		LinkedList<Token> prog = scanner.scan();
		
		for(Token element: prog)
			System.out.println(element.getToken() + " "+element.getType() + " " +element.getLine());
	}
}
