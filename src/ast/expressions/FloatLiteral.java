package src.ast.expressions;

import src.ast.main.Type;

public class FloatLiteral extends Expression{
    public float value;


    public FloatLiteral(float value, int line, int column){
        super(line, column);
        this.value = value;
        type = Type.FLOAT;
    }

    public Type getType() {
        return type;
   }
}