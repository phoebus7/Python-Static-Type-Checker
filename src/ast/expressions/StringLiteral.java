package src.ast.expressions;

import src.ast.main.Type;

public class StringLiteral extends Expression{
    public String value;


    public StringLiteral(String value, int line, int column){
        super(line, column);
        this.value = value;
        type = Type.STRING;
    }

    public Type getType() {
        return type;
   }
}