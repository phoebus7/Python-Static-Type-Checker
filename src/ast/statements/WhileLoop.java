package src.ast.statements;

import java.util.List;
import src.ast.expressions.Expression;
import src.ast.main.Range;
import src.ast.main.Type;

public class WhileLoop extends Statement {
    public Expression condition;
    public Range range;
    public WhileLoop(int line, int column, Expression condition, Range range) {
        super(line, column);
        this.condition = condition;
        this.range = range;
    }

    public Type getType() {
        return Type.INT;
    }
}