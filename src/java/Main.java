import java.io.*;

public class Main {
	public static void main(String argv[]) {
		try{
			Lexer lexer = new Lexer(new FileReader(argv[0]));
			parser p = new parser(lexer);
			p.fname = argv[0];
			Object result = p.parse();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
