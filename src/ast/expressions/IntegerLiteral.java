package src.ast.expressions;

import src.ast.main.Type;

public class IntegerLiteral extends Expression{
    public int value;


    public IntegerLiteral(int value, int line, int column){
        super(line, column);
        this.value = value;
        type = Type.INT;
    }

   public int getValue() {
        return value;
   }

   public Type getType() {
        return type;
   }
}
