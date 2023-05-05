package src.ast.expressions;

import src.ast.main.Type;

public class DictLiteral extends Expression {
    
    public DictLiteral(int line, int column) {
        super(line, column);
        this.type = Type.DICT;
    }

    public Type getType() {
        return type;
   }

}