package src.ast.expressions;

import java.util.HashMap;
import src.ast.main.Type;
import src.ast.main.UnaryOperator;


public class UnaryExpression extends Expression {
    public Expression expression;
    public UnaryOperator op;
    public String error;
    public static HashMap<UnaryOperator, HashMap<Type, Type>> inference;
    public static Runnable[] methods = {UnaryExpression::Plus, UnaryExpression::Minus,
                                        UnaryExpression::BitNot, UnaryExpression::Not};


    private static void Infere() {
        inference = new HashMap<>();
        for (Runnable method : methods)
            method.run();
    }

    public UnaryExpression(int line, int column, Expression expression, UnaryOperator op){
        super(line, column);
        this.expression = expression;
        this.op = op;
        if (inference == null)
            Infere();
        type = getType();
    }

    public Type getType() {
        if (inference.get(op).containsKey(expression.getType())) {
            return inference.get(op).get(expression.getType());
        } else {
            error = "Unsupported operand type for unary " + op + " ('" + expression.getType() + "')"; 
            return Type.SYNTAXERROR;
        }
    }

    public static void Plus() {
        HashMap<Type, Type> plus = new HashMap<>();
        plus.put(Type.BOOL, Type.INT);
        inference.put(UnaryOperator.PLUS, plus);
    }

    public static void Minus() {
        HashMap<Type, Type> minus = new HashMap<>();
        minus.put(Type.BOOL, Type.INT);
        inference.put(UnaryOperator.MINUS, minus);
    }

    public static void BitNot() {
        HashMap<Type, Type> bitnot = new HashMap<>();
        bitnot.put(Type.INT, Type.INT);
        bitnot.put(Type.BOOL, Type.INT);
        inference.put(UnaryOperator.BIT_NOT, bitnot);
    }

    public static void Not() {
        HashMap<Type, Type> not = new HashMap<>();
        not.put(Type.INT, Type.BOOL);
        not.put(Type.FLOAT, Type.BOOL);
        not.put(Type.BOOL, Type.BOOL);
        not.put(Type.STRING, Type.BOOL);
        not.put(Type.LIST, Type.BOOL);
        not.put(Type.TUPLE, Type.BOOL);
        not.put(Type.DICT, Type.BOOL);
        not.put(Type.SET, Type.BOOL);
        inference.put(UnaryOperator.NOT, not);
    }
    
}
