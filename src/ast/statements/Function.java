package src.ast.statements;

import src.ast.main.Type;
import java.util.ArrayList;

public class Function extends Statement{
    Type returnType;
    ArrayList<Type> params;

    public Function(int line, int column, Type returnType, ArrayList<Type> params) {
        super(line, column);
        this.returnType = returnType;
        this.params = new ArrayList<>(params);
    }

    public Type getType() {
        return returnType;
    }
}