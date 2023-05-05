import java.util.Stack;
//import java.lang.*;

%%
%class Lexer 
%standalone
%unicode
%line
%column
%xstate INDENT
%xstate DEDENT

 



%{
     public static int indentLevel = 0;
     public static int currIndentLevel = 0;
     public static int tabWidth = 4;
     public static Stack<Integer> indents = new Stack<Integer>();
    
    
     
%}

%init{
    indents.push(0);
%init}






whitespace = \r\n|\n\r
newline = \n
comment = \#[^\n\r]*
int = [+-]?[0-9]+ | [0][Bb][01]+ | [0][Xx][a-fA-F0-9]+ | [0][Oo][0-7]+
float = [+-]?[0-9]*\.?[0-9]+([Ee][+-]?[0-9]+)?
bool = "True" | "False"
str = \'.*\' | \".*\" | \"\"\"([^\"])* ~ \"\"\" | \'\'\'([^\'])* ~ \'\'\'
id = [a-zA-Z_][a-zA-Z_0-9]*


%%

{newline} {
     currIndentLevel = 0; 
     yybegin(INDENT); 
     System.out.println("LEXER: new line token: " + indentLevel);
}

<INDENT> {
     " "+ {
         currIndentLevel = yytext().length();
        // System.out.println("LEXER: in space INDENT");
         indents.push(currIndentLevel);
     }
     "\t"+ {currIndentLevel = tabWidth * yytext().length(); }
     "\n" {currIndentLevel = 0;}
     . {
          yypushback(1);
          if (currIndentLevel > indentLevel) {
               indentLevel = currIndentLevel;
               System.out.println("LEXER: INDENT token: " + currIndentLevel);
          } else if (currIndentLevel < indents.peek()) {

               while (indents.peek() != currIndentLevel) {
                    
                    System.out.println("LEXER: DEDENT token: " + indents.pop());

                    //System.out.println("LEXER: DEDENT token: " + indentLevel);
               }
               
            //   System.out.println("LEXER: DEDENT token: " + indents.peek());
          } else {
               
               yybegin(YYINITIAL);
          }
     }
}





// <INDENT> {
//       "\t" {
//           int currIndent+= tabWidth;
//           if (hasCOLON) {
//                int currIndent = yytext().length();
//               // System.out.println(currIndent);
//                if (currIndent > indentLevel) {
//                     indentLevel = currIndent;
//                     indents.push(indentLevel);
//                     System.out.println("LEXER: INDENT token: " + indentLevel);
//                } else if (currIndent < indentLevel) {
//                     while(indents.peek() != currIndent) {
//                          indents.pop();
//                          System.out.println();
//                     }
//                }
//                hasCOLON = false;
//           }
//      }
//      " "+ {
//           if (hasCOLON) {
//                int currIndent = yytext().length();
//               // System.out.println(currIndent);
//                if (currIndent > indentLevel) {
//                     indentLevel = currIndent;
//                     indents.push(indentLevel);
//                     System.out.println("LEXER: INDENT token: " + indentLevel);
//                } else if (currIndent < indentLevel) {
//                     while(indents.peek() != currIndent) {
//                          indents.pop();
//                          System.out.println();
//                     }
//                }
//                hasCOLON = false;
//           }
//      }
     
    
//      . {
//           yybegin(YYINITIAL);
//           yypushback(1);
     
          
//      }
// }




// {newline}" "+  {
   
//           int prevIndent = indentLevel;
//           processIndent();
//           if (indentLevel > prevIndent) {
//                System.out.println("LEXER: INDENT token: " + indentLevel);
               
//           }
//           else if (indentLevel < prevIndent) {
//                System.out.println("LEXER: DEDENT token: " + indentLevel);
               
//           }
     
// }

// {newline} {
//      int prevIndent = indentLevel;
//      hasNL = true;
//      processIndent();
//      System.out.println("LEXER: new line token: " + indentLevel);
//      if (indentLevel > prevIndent) System.out.println("LEXER: INDENT token: " + indentLevel);
//      else if (indentLevel < prevIndent) System.out.println("LEXER: DEDENT token: " + indentLevel);
//      else System.out.println("LEXER: new line token: " + indentLevel);
// }


// <INDENT> {
//      " "+ {
//           if (hasCOLON) {
//                int prevIndent = indentLevel;
//                processIndent();
//                yybegin(DEDENT);
//                hasCOLON = false;
//                if (indentLevel > prevIndent) 
//                     System.out.println("LEXER: INDENT token: " + indentLevel);4
//           }
//      }
//      "\n" {
//           yybegin(DEDENT);
//      }
     
// }

// <DEDENT> {
//  .    {
//           int prevIndent = indentLevel;
//           processIndent();
//           if (indentLevel < prevIndent) 
//                System.out.println("LEXER: DEDENT token: " + indentLevel);
//           yybegin(YYINITIAL);
//      }
// }

// {newline} {
//      yybegin(INDENT);
//      //yybegin(DEDENT);
//      System.out.println("LEXER: new line token: " + indentLevel);
// }



{whitespace}|" " {}




// <INDENT>" "+ {
//     int currIndentLevel = yytext().length();
//     if (currIndentLevel > indentLevel) {
//         indentLevel = currIndentLevel;
//         indents.push(indentLevel);
//         System.out.println("LEXER: found INDENT token: " + indentLevel);
//         yybegin(YYINITIAL);
//     } else if (currIndentLevel < indentLevel) {
//         indents.pop();
//         indentLevel = currIndentLevel;
//         System.out.println("LEXER: found DEDENT token: " + indentLevel);
//     } else {
//         yybegin(YYINITIAL);
//     }

// }




// <DEDENT> {
//      [\n] {yybegin(YYINITIAL);}
//      [ \t]+ {
//           System.out.println("LEXER: find tabs in DEDENT");
//           int numSpaces = 0, numTabs = 0;
//           for (int i = 0; i < yytext().length(); ++ i) {
//                if (yytext().charAt(i) == ' ') ++numSpaces;
//                else if (yytext().charAt(i) == '\t') ++numTabs;
//           }
//           indentLevel = numSpaces + tabWidth * numTabs;
//           System.out.println("LEXER: find dedent");
          
//      }
    
// }



"or" {System.out.println("LEXER: disjunction: " + yytext());}
"and" {System.out.println("LEXER: conjunction: " + yytext());}
"not" {System.out.println("LEXER: negation: " + yytext());}

"is" {System.out.println("LEXER: identity: " + yytext());}
"is not" {System.out.println("LEXER: identity: " + yytext());}

"in" {System.out.println("LEXER: membership: " + yytext());}
"not in" {System.out.println("LEXER: membership: " + yytext());}

"if" {System.out.println("LEXER: if: " + yytext());}

"elif" {System.out.println("LEXER: elif: " + yytext());}
"else" {System.out.println("LEXER: else: " + yytext());}
"while" {System.out.println("LEXER: while: " + yytext());}
"return" {System.out.println("LEXER: return statement: " + yytext());}





{int} {System.out.println("LEXER: int token: " + yytext()); }
{float} {System.out.println("LEXER: float token: " + yytext()); }
{bool} {System.out.println("LEXER: bool token: " + yytext()); }
{str} {System.out.println("LEXER: str token: " + yytext()); }
{id} {System.out.println("LEXER: id token: " + yytext()); }



"&" {System.out.println("LEXER: bitwise and: " + yytext());}
"|" {System.out.println("LEXER: bitwise or: " + yytext());}
"^" {System.out.println("LEXER: bitwise xor: " + yytext());}
"~" {System.out.println("LEXER: bitwise inversion: " + yytext());}


">>" {System.out.println("LEXER: bitwise right shift: " + yytext());}
"<<" {System.out.println("LEXER: bitwise left shift: " + yytext());}

"[" {System.out.println("LEXER: left square bracket: " + yytext());}
"]" {System.out.println("LEXER: right square bracket: " + yytext());}
"(" {System.out.println("LEXER: left parenthesis: " + yytext());}
")" {System.out.println("LEXER: right parenthesis: " + yytext());}
"{" {System.out.println("LEXER: left curly bracket: " + yytext());}
"}" {System.out.println("LEXER: right curly bracket: " + yytext());}

"," {System.out.println("LEXER: comma: " + yytext());}
"." {System.out.println("LEXER: dot: " + yytext());}
":" {System.out.println("LEXER: colon: " + yytext());}
";" {System.out.println("LEXER: semicolon: " + yytext());}





"=" {System.out.println("LEXER: assignment token: " + yytext()); }
":=" {System.out.println("LEXER: walrus assignment token: " + yytext());}

"+" {System.out.println("LEXER: binary addition token: " + yytext()); }
"+=" {System.out.println("LEXER: short addition token: " + yytext()); }
"-" {System.out.println("LEXER: binary substraction token: " + yytext()); }
"-=" {System.out.println("LEXER: short substraction token: " + yytext()); }
"*" {System.out.println("LEXER: binary multiplication token: " + yytext()); }
"*=" {System.out.println("LEXER: short multiplication token: " + yytext()); }
"/" {System.out.println("LEXER: binary float division token: " + yytext()); }
"/=" {System.out.println("LEXER: short floatdivision token: " + yytext()); }
"//" {System.out.println("LEXER: binary int division token: " + yytext()); }
"//=" {System.out.println("LEXER: short intdivision token: " + yytext()); }
"%" {System.out.println("LEXER: binary modulo token: " + yytext()); }
"%=" {System.out.println("LEXER: short modulo token: " + yytext()); }
"**" {System.out.println("LEXER: binary power token: " + yytext()); }
"**=" {System.out.println("LEXER: short power token: " + yytext()); }


"==" {System.out.println("LEXER: equality token: " + yytext());        }
"!=" {System.out.println("LEXER: inequality token: " + yytext());      }
">" {System.out.println("LEXER: greater than token: " + yytext());     }
"<" {System.out.println("LEXER: less than token: " + yytext());        }
">=" {System.out.println("LEXER: greater or equal token: " + yytext());}
"<=" {System.out.println("LEXER: less or equal token: " + yytext());   }



{comment} {}





. {System.out.println("LEXER: error: " + yytext());}
