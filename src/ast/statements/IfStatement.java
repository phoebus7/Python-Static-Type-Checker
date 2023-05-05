package src.ast.statements;

import src.ast.expressions.Expression;
import src.ast.main.Range;
import src.ast.main.Type;
import java.util.ArrayList;


public class IfStatement extends Statement {
    private Expression condition;
    private Range range;
    
    

     public IfStatement(int line, int column, Expression condition, Range range) {
        super(line, column);
        this.condition = condition;
        this.range = range;
    }


    public IfStatement getStatement() {
        return this;
    }

    public Type getType() {
        return Type.INT;
    }
}
