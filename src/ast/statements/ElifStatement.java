package src.ast.statements;

import src.ast.expressions.Expression;
import src.ast.main.Range;
import src.ast.main.Type;
import java.util.ArrayList;

public class ElifStatement extends Statement {
    private Expression condition;
    private Range range;
    

    
     
     public ElifStatement(int line, int column, Expression condition, Range range) {
        super(line, column);
        this.condition = condition;
        this.range = range;
    }

    public ElifStatement getStatement() {
        return this;
    }

    public Type getType() {
        return Type.INT;
    }
}