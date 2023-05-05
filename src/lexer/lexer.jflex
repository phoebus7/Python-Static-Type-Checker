import java_cup.runtime.*;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;

//import java.lang.*;

%%
%class Lexer 
%standalone
%function process_next_token
%unicode
%cup
%line
%column
%xstate INDENT


 



%{
     public static int indentLevel = 0;
     public static int currIndentLevel = 0;
     public static int tabWidth = 4;
     public static boolean hasColon = false;
     public static Stack<Integer> indents = new Stack<Integer>();
     ArrayList<Symbol> tokenBuffer = new ArrayList<>();
     public static HashMap<Integer, Integer> context = new HashMap<>();

     // private Symbol symbol(int token, Object value) {
	// 	return new Symbol(token, yyline, yycolumn, value);
	// }

     private Symbol symbol(int token, Object value) {
          Symbol sym = new Symbol(token, yyline, yycolumn, value);
          tokenBuffer.add(sym);
          return tokenBuffer.remove(0);
     }

    public java_cup.runtime.Symbol next_token() throws java.io.IOException {
          if (tokenBuffer.isEmpty())
               return process_next_token();
          return tokenBuffer.remove(0);
    }

	

%}

%init{
     indents.push(0);
%init}






whitespace = \r\n|\n\r
newline = \n
comment = \#[^\n\r]*
//str = "'" ([^'\\] | '\\\\' . | '\\'' .)* "'" | \" ([^\"\\] | \"\\\\\" . | \"\\\"\" .)* \";
int = [+-]?[0-9]+ | [0][Bb][01]+ | [0][Xx][a-fA-F0-9]+ | [0][Oo][0-7]+
float = [+-]?[0-9]*\.?[0-9]+([Ee][+-]?[0-9]+)?
bool = "True" | "False"
//str = \'.*\' | \".*\" | \"\"\"([^\"])* ~ \"\"\" | \'\'\'([^\'])* ~ \'\'\';
//str = ["]([^"\\\n]|\\(.|\n))*["] | [']([^'\\\n]|\\(.|\n))*['] ;
str = \'.*\' | \".*\"
id = [a-zA-Z_][a-zA-Z_0-9]*


%%


{newline} {
     currIndentLevel = 0; 
     yybegin(INDENT); 
     // System.out.println("LEXER: new line token: " + indents.peek());
     // return symbol(sym.NL, new Integer(0));
}

<INDENT> {
     " "+ {
         currIndentLevel = yytext().length();
     }
     "\t"+ {
         // System.out.println("LEXER: tab in Indent");
          currIndentLevel = tabWidth * yytext().length();
      }
     "\n" {
          currIndentLevel = 0; 
          System.out.println("LEXER: new line token: " + indents.peek());
          return symbol(sym.NL, new Integer(0)) ;
     }
     <<EOF>> {
          currIndentLevel = 0;
          while (indents.peek() != currIndentLevel) {
                                       
                    System.out.println("LEXER: DEDENT token: " + indents.pop());
                    return symbol(sym.DEDENT, new Integer(yyline));
                    
          }
          return symbol(sym.EOF, new Integer(0));
     }
     . {
          yypushback(1);
        
          if (hasColon && currIndentLevel > indents.peek()) {
               indents.push(currIndentLevel);
               System.out.println("LEXER: INDENT token: " + indents.peek());
               return symbol(sym.INDENT, new Integer(yyline));
          } else if (currIndentLevel < indents.peek()) {
               
               while (indents.peek() != currIndentLevel) {
                                       
                    System.out.println("LEXER: DEDENT token: " + indents.pop());
                    hasColon = false;
                    return symbol(sym.DEDENT, new Integer(yyline));
               }
          } else {
               indentLevel = currIndentLevel;
               yybegin(YYINITIAL);
               // System.out.println("LEXER: new line token: 0");
               // return symbol(sym.NL, new Integer(0));
          }
     }
}






"or" {System.out.println("LEXER: disjunction: " + yytext());
      return symbol(sym.OR, new String(yytext()));}
"and" {System.out.println("LEXER: conjunction: " + yytext());
       return symbol(sym.AND, new String(yytext()));}
"not" {System.out.println("LEXER: negation: " + yytext());
       return symbol(sym.NOT, new String(yytext()));}

"is" {System.out.println("LEXER: identity: " + yytext());
      return symbol(sym.IS, new String(yytext()));}
"is not" {System.out.println("LEXER: identity: " + yytext());
          return symbol(sym.ISNOT, new String(yytext()));}

"in" {System.out.println("LEXER: membership: " + yytext());
      return symbol(sym.IN, new String(yytext()));}
"not in" {System.out.println("LEXER: membership: " + yytext());
          return symbol(sym.NOTIN, new String(yytext()));}

"if" {
          System.out.println("LEXER: if token: " + yytext());
          context.put(yyline, indents.peek());
          return symbol(sym.IF_STMT, new String(yytext()));
     }

"elif" {
          System.out.println("LEXER: elif token: " + yytext());
          context.put(yyline, indents.peek());
          return symbol(sym.ELIF_STMT, new String(yytext()));}
"else" {
          System.out.println("LEXER: else token: " + yytext());
          context.put(yyline, indents.peek());
          return symbol(sym.ELSE_STMT, new String(yytext()));
        }
"while" {System.out.println("LEXER: while token: " + yytext());
          return symbol(sym.WHILE_STMT, new String(yytext()));
          }
"for" {System.out.println("LEXER: for token: " + yytext());
       return symbol(sym.FOR_STMT, new String(yytext()));}
"return" {System.out.println("LEXER: return statement: " + yytext());
        return symbol(sym.RETURN_STMT, new String(yytext()));}
"try" {
     System.out.println("LEXER: try statement: " + yytext());
        return symbol(sym.TRY_STMT, new String(yytext()));
}

"except" {
     System.out.println("LEXER: except statement: " + yytext());
        return symbol(sym.EXCEPT_STMT, new String(yytext()));
}

"as" {
     System.out.println("LEXER: " + yytext());
        return symbol(sym.AS, new String(yytext()));
}

"finally" {
     System.out.println("LEXER: " + yytext());
        return symbol(sym.FINALLY_STMT, new String(yytext()));
}


"async" {
     System.out.println("LEXER: for loop: " + yytext());
        return symbol(sym.ASYNC, new String(yytext()));
}

"@" {
     System.out.println("LEXER: @ " + yytext());
          return symbol(sym.AT, new String(yytext()));
}

"class" {
     System.out.println("LEXER: class " + yytext());
          return symbol(sym.CLASS_STMT, new String(yytext()));
}

"def" {
     System.out.println("LEXER: function: " + yytext());
     return symbol(sym.FUN_STMT, new String(yytext()));
}

"await" {
     System.out.println("LEXER: await: " + yytext());
     return symbol(sym.AWAIT, new String(yytext()));
}


{int} {System.out.println("LEXER: int token: " + yytext()); return symbol(sym.INTEGER, new Integer(yytext()));}
{float} {System.out.println("LEXER: float token: " + yytext()); return symbol(sym.FLOAT, new Float(yytext()));}
{bool} {System.out.println("LEXER: bool token: " + yytext()); return symbol(sym.BOOL, new Boolean(yytext()));}
//{str} {System.out.println("LEXER: str token: " + yytext()); return symbol(sym.STRING, new String(yytext()));}
{id} {
          System.out.println("LEXER: id token: " + yytext()); 
          context.put(yyline, indents.peek());
          return symbol(sym.ID, new String(yytext()));
     }
{str} {
          System.out.println("LEXER: str token: " + yytext()); 
          context.put(yyline, indents.peek());
          return symbol(sym.STRING, new String(yytext()));
     }


"&" {System.out.println("LEXER: bitwise and: " + yytext());
     return symbol(sym.BIT_AND, new String(yytext()));}
"|" {System.out.println("LEXER: bitwise or: " + yytext());
     return symbol(sym.BIT_OR, new String(yytext()));}
"^" {System.out.println("LEXER: bitwise xor: " + yytext());
     return symbol(sym.BIT_XOR, new String(yytext()));}
"~" {System.out.println("LEXER: bitwise inversion: " + yytext());
     return symbol(sym.BIT_NOT, new String(yytext()));}

"->" {
     System.out.println("LEXER: arrow right: " + yytext());
     return symbol(sym.ARROW_RIGHT, new String(yytext()));
}
">>" {System.out.println("LEXER: bitwise right shift: " + yytext());
      return symbol(sym.RIGHT_SHIFT, new String(yytext()));}
"<<" {System.out.println("LEXER: bitwise left shift: " + yytext());
      return symbol(sym.LEFT_SHIFT, new String(yytext()));}

"[" {System.out.println("LEXER: left square bracket: " + yytext());
     return symbol(sym.LSQB, new String(yytext()));}
"]" {System.out.println("LEXER: right square bracket: " + yytext());
     return symbol(sym.RSQB, new String(yytext()));}
"(" {System.out.println("LEXER: left parenthesis: " + yytext());
     return symbol(sym.LPAR, new String(yytext()));}
")" {System.out.println("LEXER: right parenthesis: " + yytext());
     return symbol(sym.RPAR, new String(yytext()));}
"{" {System.out.println("LEXER: left curly bracket: " + yytext());
     return symbol(sym.LBRACE, new String(yytext()));}
"}" {System.out.println("LEXER: right curly bracket: " + yytext());
     return symbol(sym.RBRACE, new String(yytext()));}

"," {System.out.println("LEXER: comma: " + yytext());
     return symbol(sym.COMMA, new String(yytext()));}
"." {System.out.println("LEXER: dot: " + yytext());
     return symbol(sym.DOT, new String(yytext()));}
":" {System.out.println("LEXER: colon: " + yytext());
     hasColon = true;
     return symbol(sym.COLON, new String(yytext()));}
";" {System.out.println("LEXER: semicolon: " + yytext());
     return symbol(sym.SEMICOLON, new String(yytext()));}



"==" {System.out.println("LEXER: equality token: " + yytext()); return symbol(sym.EQ, new String(yytext()));}
"!=" {System.out.println("LEXER: inequality token: " + yytext()); return symbol(sym.NE, new String(yytext()));}
">" {System.out.println("LEXER: greater than token: " + yytext()); return symbol(sym.GT, new String(yytext()));}
"<" {System.out.println("LEXER: less than token: " + yytext()); return symbol(sym.LT, new String(yytext()));}
">=" {System.out.println("LEXER: greater or equal token: " + yytext()); return symbol(sym.GE, new String(yytext()));}
"<=" {System.out.println("LEXER: less or equal token: " + yytext()); return symbol(sym.LE, new String(yytext()));}


"=" {System.out.println("LEXER: assignment token: " + yytext()); return symbol(sym.ASSIGNMENT, new String(yytext()));}
":=" {System.out.println("LEXER: walrus assignment token: " + yytext()); return symbol(sym.WALRUS_ASSIGNMENT, new String(yytext()));}

"+" {System.out.println("LEXER: binary addition token: " + yytext()); return symbol(sym.ADDITION, new String(yytext()));}
"+=" {System.out.println("LEXER: short addition token: " + yytext()); return symbol(sym.SHORTADDITION, new String(yytext()));}
"-" {System.out.println("LEXER: binary substraction token: " + yytext()); return symbol(sym.SUBSTRACTION, new String(yytext()));}
"-=" {System.out.println("LEXER: short substraction token: " + yytext()); return symbol(sym.SHORTSUBSTRACTION, new String(yytext()));}
"*" {System.out.println("LEXER: binary multiplication token: " + yytext()); return symbol(sym.MULTIPLICATION, new String(yytext()));}
"*=" {System.out.println("LEXER: short multiplication token: " + yytext()); return symbol(sym.SHORTMULTIPLICATION, new String(yytext()));}
"/" {System.out.println("LEXER: binary float division token: " + yytext()); return symbol(sym.FLOATDIVISION, new String(yytext()));}
"/=" {System.out.println("LEXER: short floatdivision token: " + yytext()); return symbol(sym.SHORTFLOATDIVISION, new String(yytext()));}
"//" {System.out.println("LEXER: binary int division token: " + yytext()); return symbol(sym.INTDIVISION, new String(yytext()));}
"//=" {System.out.println("LEXER: short intdivision token: " + yytext()); return symbol(sym.SHORTINTDIVISION, new String(yytext()));}
"%" {System.out.println("LEXER: binary modulo token: " + yytext()); return symbol(sym.MODULO, new String(yytext()));}
"%=" {System.out.println("LEXER: short modulo token: " + yytext()); return symbol(sym.SHORTMODULO, new String(yytext()));}
"**" {System.out.println("LEXER: binary power token: " + yytext()); return symbol(sym.DEGREE, new String(yytext()));}
"**=" {System.out.println("LEXER: short power token: " + yytext()); return symbol(sym.SHORTDEGREE, new String(yytext()));}






{comment} {}

{whitespace}|" "|\t {}

<<EOF>> {
     //currIndentLevel = 0;
     yybegin(INDENT);
}


. {System.out.println("LEXER: error: " + yytext());}

