package src.ast.statements;

import java.util.List;
import src.ast.expressions.Expression;
import src.ast.main.Type;

public class ForLoop extends Statement {
    public Expression iterator;
    public Type iterable; 
    public Expression range; //range(start, end, inc value)
    

    public ForLoop(int line, int column, Expression it, Type field, Expression range) {
        super(line, column);
        this.iterator = it;
        this.iterable = field;
        this.range = range;
    }

    public Type getType() {
        return Type.INT;
    }
}
