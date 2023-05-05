package src.ast.expressions;

import src.ast.main.Type;

public class SetLiteral extends Expression {
    
    public SetLiteral(int line, int column) {
        super(line, column);
        this.type = Type.SET;
    }

    public Type getType() {
        return type;
   }

}