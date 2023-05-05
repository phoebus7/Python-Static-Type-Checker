package src.ast.statements;

import src.ast.expressions.Expression;
import src.ast.main.Type;

public class Return extends Statement{
    public Type returntype; 
    public Expression returnValue;
    public Return(Type type, Expression value, int line, int column, String fileLocation) {
        super(line, column);
        this.returntype = type;
        this.returnValue = value;
    }

    public Type getType() {
        return Type.INT;
    }

}
