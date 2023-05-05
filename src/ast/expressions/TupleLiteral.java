package src.ast.expressions;

import src.ast.main.Type;

public class TupleLiteral extends Expression {
    
    public TupleLiteral(int line, int column) {
        super(line, column);
        this.type = Type.TUPLE;
    }

    public Type getType() {
        return type;
   }

}