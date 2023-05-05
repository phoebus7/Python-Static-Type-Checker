package src.ast.expressions;

import src.ast.main.Type;

public class Label extends Expression {
    private String name;

    public Label(int line, int column, String name, Type type){
        super(line, column);
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }
}
