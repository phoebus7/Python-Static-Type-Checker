package src.ast.expressions;

import src.ast.main.Type;

public class BooleanLiteral extends Expression{
    public boolean value;
    

    public BooleanLiteral(boolean value, int line, int column){
        super(line, column);
        this.value = value;
        type = Type.BOOL;
    }

    public Type getType() {
        return type;
   }
}