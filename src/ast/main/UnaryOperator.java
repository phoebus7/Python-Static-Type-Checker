package src.ast.main;

public enum UnaryOperator {
    PLUS,
    MINUS,
    BIT_NOT,
    NOT;

    @Override
    public String toString() {
        switch(this) {
            case PLUS: return "+";
            case MINUS: return "-";
            case BIT_NOT: return "~";
            default: return "not";
        }
    }
}
