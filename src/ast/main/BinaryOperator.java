package src.ast.main;

public enum BinaryOperator {
    PLUS,
    MINUS,
    MULTIPLICATION,
    POWER,
    INTDIVISION,
    FLOATDIVISION,
    MODULO,
    LEFT_SHIFT,
    RIGHT_SHIFT,
    BIT_XOR,
    BIT_AND,
    BIT_OR,
    AND,
    OR;

    @Override
    public String toString() {
        switch(this) {
            case PLUS: return "+";
            case MINUS: return "-";
            case MULTIPLICATION: return "*";
            case POWER: return "**";
            case INTDIVISION: return "//";
            case FLOATDIVISION: return "/";
            case MODULO: return "%";
            case RIGHT_SHIFT: return ">>";
            case LEFT_SHIFT: return "<<";
            case BIT_XOR: return "^";
            case BIT_AND: return "&";
            case BIT_OR: return "|";
            case AND: return "and";
            default: return "or";
        }
    } 
}