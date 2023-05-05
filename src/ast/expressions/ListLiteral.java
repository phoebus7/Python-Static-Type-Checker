package src.ast.expressions;

import java.util.ArrayList;
import src.ast.main.Type;


public class ListLiteral extends Expression {
    
    public ArrayList<Type> types;

    public ListLiteral(int line, int column, ArrayList<Expression> exprs) {
        super(line, column);
        this.type = Type.LIST;
        this.types = new ArrayList<>();
       
        for (Expression expr : exprs)
            types.add(expr.getType());

    }

    public Type getType() {
        return type;
   }

}