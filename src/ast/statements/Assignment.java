package src.ast.statements;

import src.ast.expressions.Expression;
import src.ast.expressions.Label;
import src.ast.main.Type;
import java.util.HashMap;


public class Assignment extends Statement{
    private Label lhs;
    private Expression rhs;
    public static HashMap<HashMap<Type, Type>, Type> inference;


    public Assignment(int line, int column, Label lhs, Expression rhs) {
        super(line, column);
        this.lhs = lhs;
        this.rhs = rhs;
        if (inference == null)
            Infere();
    }

    public Label getLabel() {
        return lhs;
    }

    public Expression getExpression() {
        return rhs;
    }

    public Type getType() {
        HashMap<Type, Type> pair = new HashMap<>();
        pair.put(lhs.getType(), rhs.getType());
        if (rhs.getType() == Type.NOTDEFINED) {
            error = "Name \"" + ((Label)rhs).getName() + "\" is not defined";
            return Type.NOTDEFINED;
        }
        if (inference.containsKey(pair))
            return inference.get(pair);
        error = "Incompatible types in assignment (expression has type \"" + rhs.getType() + "\", variable has type \"" +
                lhs.getType() + "\")";  
        return Type.SYNTAXERROR;
    }

    public static void Infere() {
        inference = new HashMap<>() {
            {put(new HashMap<>() {
                {put(Type.INT, Type.INT);}
            }, Type.INT);}
            {put(new HashMap<>() {
                {put(Type.INT, Type.BOOL);}
            }, Type.INT);}
            {put(new HashMap<>() {
                {put(Type.FLOAT, Type.INT);}
            }, Type.FLOAT);}
            {put(new HashMap<>() {
                {put(Type.FLOAT, Type.FLOAT);}
            }, Type.FLOAT);}
            {put(new HashMap<>() {
                {put(Type.FLOAT, Type.BOOL);}
            }, Type.FLOAT);}
            {put(new HashMap<>() {
                {put(Type.STRING, Type.STRING);}
            }, Type.STRING);}
            {put(new HashMap<>() {
                {put(Type.BOOL, Type.BOOL);}
            }, Type.BOOL);}
            {put(new HashMap<>() {
                {put(Type.LIST, Type.LIST);}
            }, Type.LIST);}
            {put(new HashMap<>() {
                {put(Type.TUPLE, Type.TUPLE);}
            }, Type.TUPLE);}
            {put(new HashMap<>() {
                {put(Type.SET, Type.SET);}
            }, Type.SET);}
            {put(new HashMap<>() {
                {put(Type.DICT, Type.DICT);}
            }, Type.DICT);}
        };
    }

}
