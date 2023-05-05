package src.ast.statements;

import java.util.List;
import src.ast.expressions.Expression;
import src.ast.main.Type;

public class Lambda extends Statement {
    public List<String> args; 
    public Expression expression;

    public Lambda(List<String> arguments, Expression expr, int line, int column, String fileLocation) {
        super(line, column);
        this.args = arguments;
        this.expression = expr;
    }

    public Type getType() {
        return Type.INT;
    }
}
