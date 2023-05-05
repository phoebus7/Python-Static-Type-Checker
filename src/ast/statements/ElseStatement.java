package src.ast.statements;

import src.ast.expressions.Expression;
import src.ast.main.Range;
import src.ast.main.Type;
import java.util.ArrayList;

public class ElseStatement extends Statement {
   
    
    public Range range;


    public ElseStatement(int line, int column, Range range) {
        super(line, column);
        this.range = range;
    }

    public ElseStatement getStatement() {
        return this;
    }

    public Type getType() {
        return Type.INT;
    }

}