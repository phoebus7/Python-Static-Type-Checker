package src.ast.expressions;

import java.util.HashMap;
import src.ast.main.BinaryOperator;
import src.ast.main.Type;

public class BinaryExpression extends Expression{
    public Expression lhs;
    public BinaryOperator op;
    public Expression rhs;
    public String error;
    public static HashMap<BinaryOperator, HashMap<HashMap<Type, Type>, Type>> inference;
    private static Runnable[] methods = {BinaryExpression::Plus, BinaryExpression::Minus, 
                                         BinaryExpression::Times, BinaryExpression::Pow,
                                         BinaryExpression::IntDiv, BinaryExpression::FloatDiv,
                                         BinaryExpression::Mod, BinaryExpression::RightShift,
                                         BinaryExpression::LeftShift, BinaryExpression::BitXor,
                                         BinaryExpression::BitAnd, BinaryExpression::BitOr,
                                         BinaryExpression::And, BinaryExpression::Or};
    
    private static void Infere() {
        inference = new HashMap<>();
        for (Runnable method : methods)
            method.run();
    }

    public BinaryExpression(int line, int column, Expression lhs, Expression rhs, BinaryOperator operator){
        super(line, column);
        this.lhs = lhs;
        this.rhs = rhs;
        this.op = operator;
        if (inference == null)
            Infere();
        type = getType();
        
    }

    public Type getType() {
       HashMap<Type, Type> pair = new HashMap<>() {{put(lhs.getType(), rhs.getType());}};
        if (inference.get(op).containsKey(pair)) 
        {
            return inference.get(op).get(pair);
        } else {  
            error = "Unsopperted operand types for " + op + " (\"" + lhs.getType() + "\", \"" + rhs.getType() + "\")";
            return Type.SYNTAXERROR;
        }
    }


    public static void Plus() {
        HashMap<HashMap<Type, Type>, Type> plus = new HashMap<>();
        plus.put(new HashMap<Type, Type>() {{put(Type.INT, Type.INT);}}, Type.INT);
        plus.put(new HashMap<Type, Type>() {{put(Type.INT, Type.FLOAT);}}, Type.FLOAT);
        plus.put(new HashMap<Type, Type>() {{put(Type.INT, Type.BOOL);}}, Type.INT);
        plus.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.INT);}}, Type.FLOAT);
        plus.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.FLOAT);}}, Type.FLOAT);
        plus.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.BOOL);}}, Type.FLOAT);
        plus.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.STRING);}}, Type.STRING);
        plus.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.INT);}}, Type.INT);
        plus.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.FLOAT);}}, Type.FLOAT);
        plus.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.BOOL);}}, Type.INT);
        plus.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.LIST);}}, Type.LIST);
        plus.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.TUPLE);}}, Type.TUPLE);
        inference.put(BinaryOperator.PLUS, plus);
    }

    public static void Minus() {
        HashMap<HashMap<Type, Type>, Type> minus = new HashMap<>();
        minus.put(new HashMap<Type, Type>() {{put(Type.INT, Type.INT);}}, Type.INT);
        minus.put(new HashMap<Type, Type>() {{put(Type.INT, Type.FLOAT);}}, Type.FLOAT);
        minus.put(new HashMap<Type, Type>() {{put(Type.INT, Type.BOOL);}}, Type.INT);
        minus.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.INT);}}, Type.FLOAT);
        minus.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.FLOAT);}}, Type.FLOAT);
        minus.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.BOOL);}}, Type.FLOAT);
        minus.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.INT);}}, Type.INT);
        minus.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.FLOAT);}}, Type.FLOAT);
        minus.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.BOOL);}}, Type.INT);
        inference.put(BinaryOperator.MINUS, minus);
    }
   
    public static void Times() {
        HashMap<HashMap<Type, Type>, Type> times = new HashMap<>();
        times.put(new HashMap<Type, Type>() {{put(Type.INT, Type.INT);}}, Type.INT);
        times.put(new HashMap<Type, Type>() {{put(Type.INT, Type.FLOAT);}}, Type.FLOAT);
        times.put(new HashMap<Type, Type>() {{put(Type.INT, Type.STRING);}}, Type.STRING);
        times.put(new HashMap<Type, Type>() {{put(Type.INT, Type.BOOL);}}, Type.INT);
        times.put(new HashMap<Type, Type>() {{put(Type.INT, Type.LIST);}}, Type.LIST);
        times.put(new HashMap<Type, Type>() {{put(Type.INT, Type.TUPLE);}}, Type.TUPLE);
        times.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.INT);}}, Type.FLOAT);
        times.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.FLOAT);}}, Type.FLOAT);
        times.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.BOOL);}}, Type.FLOAT);
        times.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.INT);}}, Type.STRING);
        times.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.BOOL);}}, Type.STRING);
        times.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.INT);}}, Type.INT);
        times.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.FLOAT);}}, Type.FLOAT);
        times.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.STRING);}}, Type.STRING);
        times.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.BOOL);}}, Type.INT);
        times.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.LIST);}}, Type.LIST);
        times.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.TUPLE);}}, Type.TUPLE);
        times.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.INT);}}, Type.LIST);
        times.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.BOOL);}}, Type.LIST);
        times.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.INT);}}, Type.TUPLE);
        times.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.BOOL);}}, Type.TUPLE);
        inference.put(BinaryOperator.MULTIPLICATION, times);
    }

    public static void Pow() {
        HashMap<HashMap<Type, Type>, Type> pow = new HashMap<>();
        pow.put(new HashMap<Type, Type>() {{put(Type.INT, Type.INT);}}, Type.INT);
        pow.put(new HashMap<Type, Type>() {{put(Type.INT, Type.FLOAT);}}, Type.FLOAT);
        pow.put(new HashMap<Type, Type>() {{put(Type.INT, Type.BOOL);}}, Type.INT);
        pow.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.INT);}}, Type.FLOAT);
        pow.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.FLOAT);}}, Type.FLOAT);
        pow.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.BOOL);}}, Type.FLOAT);
        pow.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.INT);}}, Type.INT);
        pow.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.FLOAT);}}, Type.FLOAT);
        pow.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.BOOL);}}, Type.INT);
        inference.put(BinaryOperator.POWER, pow);
    }

    public static void IntDiv() {
        HashMap<HashMap<Type, Type>, Type> intdiv = new HashMap<>();
        intdiv.put(new HashMap<Type, Type>() {{put(Type.INT, Type.INT);}}, Type.INT);
        intdiv.put(new HashMap<Type, Type>() {{put(Type.INT, Type.FLOAT);}}, Type.FLOAT);
        intdiv.put(new HashMap<Type, Type>() {{put(Type.INT, Type.BOOL);}}, Type.INT);
        intdiv.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.INT);}}, Type.FLOAT);
        intdiv.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.FLOAT);}}, Type.FLOAT);
        intdiv.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.BOOL);}}, Type.FLOAT);
        intdiv.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.INT);}}, Type.INT);
        intdiv.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.FLOAT);}}, Type.FLOAT);
        intdiv.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.BOOL);}}, Type.INT);
        inference.put(BinaryOperator.INTDIVISION, intdiv);
    }

    public static void FloatDiv() {
        HashMap<HashMap<Type, Type>, Type> floatdiv = new HashMap<>();
        floatdiv.put(new HashMap<Type, Type>() {{put(Type.INT, Type.INT);}}, Type.FLOAT);
        floatdiv.put(new HashMap<Type, Type>() {{put(Type.INT, Type.FLOAT);}}, Type.FLOAT);
        floatdiv.put(new HashMap<Type, Type>() {{put(Type.INT, Type.BOOL);}}, Type.FLOAT);
        floatdiv.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.INT);}}, Type.FLOAT);
        floatdiv.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.FLOAT);}}, Type.FLOAT);
        floatdiv.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.BOOL);}}, Type.FLOAT);
        floatdiv.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.INT);}}, Type.FLOAT);
        floatdiv.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.FLOAT);}}, Type.FLOAT);
        floatdiv.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.BOOL);}}, Type.FLOAT);
        inference.put(BinaryOperator.FLOATDIVISION, floatdiv);
    }

    public static void Mod() {
        HashMap<HashMap<Type, Type>, Type> mod = new HashMap<>();
        mod.put(new HashMap<Type, Type>() {{put(Type.INT, Type.INT);}}, Type.INT);
        mod.put(new HashMap<Type, Type>() {{put(Type.INT, Type.FLOAT);}}, Type.FLOAT);
        mod.put(new HashMap<Type, Type>() {{put(Type.INT, Type.BOOL);}}, Type.INT);
        mod.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.INT);}}, Type.FLOAT);
        mod.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.FLOAT);}}, Type.FLOAT);
        mod.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.BOOL);}}, Type.FLOAT);
        mod.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.INT);}}, Type.INT);
        mod.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.FLOAT);}}, Type.FLOAT);
        mod.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.BOOL);}}, Type.INT);
        inference.put(BinaryOperator.MODULO, mod);
    }

    public static void RightShift() {
        HashMap<HashMap<Type, Type>, Type> rs = new HashMap<>();
        rs.put(new HashMap<Type, Type>() {{put(Type.INT, Type.INT);}}, Type.INT);
        rs.put(new HashMap<Type, Type>() {{put(Type.INT, Type.BOOL);}}, Type.INT);
        rs.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.INT);}}, Type.INT);
        rs.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.BOOL);}}, Type.INT);
        inference.put(BinaryOperator.RIGHT_SHIFT, rs);
    }

    public static void LeftShift() {
        HashMap<HashMap<Type, Type>, Type> ls = new HashMap<>();
        ls.put(new HashMap<Type, Type>() {{put(Type.INT, Type.INT);}}, Type.INT);
        ls.put(new HashMap<Type, Type>() {{put(Type.INT, Type.BOOL);}}, Type.INT);
        ls.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.INT);}}, Type.INT);
        ls.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.BOOL);}}, Type.INT);
        inference.put(BinaryOperator.LEFT_SHIFT, ls);
    }

    public static void BitXor() {
        HashMap<HashMap<Type, Type>, Type> xor = new HashMap<>();
        xor.put(new HashMap<Type, Type>() {{put(Type.INT, Type.INT);}}, Type.INT);
        xor.put(new HashMap<Type, Type>() {{put(Type.INT, Type.BOOL);}}, Type.INT);
        xor.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.INT);}}, Type.INT);
        xor.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.BOOL);}}, Type.INT);
        inference.put(BinaryOperator.BIT_XOR, xor);
    }

     public static void BitAnd() {
        HashMap<HashMap<Type, Type>, Type> and = new HashMap<>();
        and.put(new HashMap<Type, Type>() {{put(Type.INT, Type.INT);}}, Type.INT);
        and.put(new HashMap<Type, Type>() {{put(Type.INT, Type.BOOL);}}, Type.INT);
        and.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.INT);}}, Type.INT);
        and.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.BOOL);}}, Type.INT);
        inference.put(BinaryOperator.BIT_AND, and);
    }

    public static void BitOr() {
        HashMap<HashMap<Type, Type>, Type> or = new HashMap<>();
        or.put(new HashMap<Type, Type>() {{put(Type.INT, Type.INT);}}, Type.INT);
        or.put(new HashMap<Type, Type>() {{put(Type.INT, Type.BOOL);}}, Type.INT);
        or.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.INT);}}, Type.INT);
        or.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.BOOL);}}, Type.INT);
        inference.put(BinaryOperator.BIT_OR, or);
    }

    public static void And() {
        HashMap<HashMap<Type, Type>, Type> and = new HashMap<>();
        and.put(new HashMap<Type, Type>() {{put(Type.INT, Type.INT);}}, Type.INT);
		and.put(new HashMap<Type, Type>() {{put(Type.INT, Type.FLOAT);}}, Type.FLOAT);
		and.put(new HashMap<Type, Type>() {{put(Type.INT, Type.STRING);}}, Type.STRING);
		and.put(new HashMap<Type, Type>() {{put(Type.INT, Type.BOOL);}}, Type.BOOL);
		and.put(new HashMap<Type, Type>() {{put(Type.INT, Type.LIST);}}, Type.LIST);
		and.put(new HashMap<Type, Type>() {{put(Type.INT, Type.TUPLE);}}, Type.TUPLE);
		and.put(new HashMap<Type, Type>() {{put(Type.INT, Type.SET);}}, Type.SET);
		and.put(new HashMap<Type, Type>() {{put(Type.INT, Type.DICT);}}, Type.DICT);
		and.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.INT);}}, Type.INT);
		and.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.FLOAT);}}, Type.FLOAT);
		and.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.STRING);}}, Type.STRING);
		and.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.BOOL);}}, Type.BOOL);
		and.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.LIST);}}, Type.LIST);
		and.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.TUPLE);}}, Type.TUPLE);
		and.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.SET);}}, Type.SET);
		and.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.DICT);}}, Type.DICT);
		and.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.INT);}}, Type.INT);
		and.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.FLOAT);}}, Type.FLOAT);
		and.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.STRING);}}, Type.STRING);
		and.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.BOOL);}}, Type.BOOL);
		and.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.LIST);}}, Type.LIST);
		and.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.TUPLE);}}, Type.TUPLE);
		and.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.SET);}}, Type.SET);
		and.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.DICT);}}, Type.DICT);
		and.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.INT);}}, Type.INT);
		and.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.FLOAT);}}, Type.FLOAT);
		and.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.STRING);}}, Type.STRING);
		and.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.BOOL);}}, Type.BOOL);
		and.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.LIST);}}, Type.LIST);
		and.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.TUPLE);}}, Type.TUPLE);
		and.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.SET);}}, Type.SET);
		and.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.DICT);}}, Type.DICT);
		and.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.INT);}}, Type.INT);
		and.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.FLOAT);}}, Type.FLOAT);
		and.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.STRING);}}, Type.STRING);
		and.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.BOOL);}}, Type.BOOL);
		and.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.LIST);}}, Type.LIST);
		and.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.TUPLE);}}, Type.TUPLE);
		and.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.SET);}}, Type.SET);
		and.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.DICT);}}, Type.DICT);
		and.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.INT);}}, Type.INT);
		and.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.FLOAT);}}, Type.FLOAT);
		and.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.STRING);}}, Type.STRING);
		and.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.BOOL);}}, Type.BOOL);
		and.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.LIST);}}, Type.LIST);
		and.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.TUPLE);}}, Type.TUPLE);
		and.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.SET);}}, Type.SET);
		and.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.DICT);}}, Type.DICT);
		and.put(new HashMap<Type, Type>() {{put(Type.SET, Type.INT);}}, Type.INT);
		and.put(new HashMap<Type, Type>() {{put(Type.SET, Type.FLOAT);}}, Type.FLOAT);
		and.put(new HashMap<Type, Type>() {{put(Type.SET, Type.STRING);}}, Type.STRING);
		and.put(new HashMap<Type, Type>() {{put(Type.SET, Type.BOOL);}}, Type.BOOL);
		and.put(new HashMap<Type, Type>() {{put(Type.SET, Type.LIST);}}, Type.LIST);
		and.put(new HashMap<Type, Type>() {{put(Type.SET, Type.TUPLE);}}, Type.TUPLE);
		and.put(new HashMap<Type, Type>() {{put(Type.SET, Type.SET);}}, Type.SET);
		and.put(new HashMap<Type, Type>() {{put(Type.SET, Type.DICT);}}, Type.DICT);
		and.put(new HashMap<Type, Type>() {{put(Type.DICT, Type.INT);}}, Type.INT);
		and.put(new HashMap<Type, Type>() {{put(Type.DICT, Type.FLOAT);}}, Type.FLOAT);
		and.put(new HashMap<Type, Type>() {{put(Type.DICT, Type.STRING);}}, Type.STRING);
		and.put(new HashMap<Type, Type>() {{put(Type.DICT, Type.BOOL);}}, Type.BOOL);
		and.put(new HashMap<Type, Type>() {{put(Type.DICT, Type.LIST);}}, Type.LIST);
		and.put(new HashMap<Type, Type>() {{put(Type.DICT, Type.TUPLE);}}, Type.TUPLE);
		and.put(new HashMap<Type, Type>() {{put(Type.DICT, Type.SET);}}, Type.SET);
		and.put(new HashMap<Type, Type>() {{put(Type.DICT, Type.DICT);}}, Type.DICT);
        inference.put(BinaryOperator.AND, and);
    }

    public static void Or() {
        HashMap<HashMap<Type, Type>, Type> or = new HashMap<>();
        or.put(new HashMap<Type, Type>() {{put(Type.INT, Type.INT);}}, Type.INT);
		or.put(new HashMap<Type, Type>() {{put(Type.INT, Type.FLOAT);}}, Type.INT);
		or.put(new HashMap<Type, Type>() {{put(Type.INT, Type.STRING);}}, Type.INT);
		or.put(new HashMap<Type, Type>() {{put(Type.INT, Type.BOOL);}}, Type.INT);
		or.put(new HashMap<Type, Type>() {{put(Type.INT, Type.LIST);}}, Type.INT);
		or.put(new HashMap<Type, Type>() {{put(Type.INT, Type.TUPLE);}}, Type.INT);
		or.put(new HashMap<Type, Type>() {{put(Type.INT, Type.SET);}}, Type.INT);
		or.put(new HashMap<Type, Type>() {{put(Type.INT, Type.DICT);}}, Type.INT);
		or.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.INT);}}, Type.FLOAT);
		or.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.FLOAT);}}, Type.FLOAT);
		or.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.STRING);}}, Type.FLOAT);
		or.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.BOOL);}}, Type.FLOAT);
		or.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.LIST);}}, Type.FLOAT);
		or.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.TUPLE);}}, Type.FLOAT);
		or.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.SET);}}, Type.FLOAT);
		or.put(new HashMap<Type, Type>() {{put(Type.FLOAT, Type.DICT);}}, Type.FLOAT);
		or.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.INT);}}, Type.STRING);
		or.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.FLOAT);}}, Type.STRING);
		or.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.STRING);}}, Type.STRING);
		or.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.BOOL);}}, Type.STRING);
		or.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.LIST);}}, Type.STRING);
		or.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.TUPLE);}}, Type.STRING);
		or.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.SET);}}, Type.STRING);
		or.put(new HashMap<Type, Type>() {{put(Type.STRING, Type.DICT);}}, Type.STRING);
		or.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.INT);}}, Type.BOOL);
		or.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.FLOAT);}}, Type.BOOL);
		or.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.STRING);}}, Type.BOOL);
		or.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.BOOL);}}, Type.BOOL);
		or.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.LIST);}}, Type.BOOL);
		or.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.TUPLE);}}, Type.BOOL);
		or.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.SET);}}, Type.BOOL);
		or.put(new HashMap<Type, Type>() {{put(Type.BOOL, Type.DICT);}}, Type.BOOL);
		or.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.INT);}}, Type.LIST);
		or.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.FLOAT);}}, Type.LIST);
		or.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.STRING);}}, Type.LIST);
		or.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.BOOL);}}, Type.LIST);
		or.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.LIST);}}, Type.LIST);
		or.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.TUPLE);}}, Type.LIST);
		or.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.SET);}}, Type.LIST);
		or.put(new HashMap<Type, Type>() {{put(Type.LIST, Type.DICT);}}, Type.LIST);
		or.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.INT);}}, Type.TUPLE);
		or.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.FLOAT);}}, Type.TUPLE);
		or.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.STRING);}}, Type.TUPLE);
		or.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.BOOL);}}, Type.TUPLE);
		or.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.LIST);}}, Type.TUPLE);
		or.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.TUPLE);}}, Type.TUPLE);
		or.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.SET);}}, Type.TUPLE);
		or.put(new HashMap<Type, Type>() {{put(Type.TUPLE, Type.DICT);}}, Type.TUPLE);
		or.put(new HashMap<Type, Type>() {{put(Type.SET, Type.INT);}}, Type.SET);
		or.put(new HashMap<Type, Type>() {{put(Type.SET, Type.FLOAT);}}, Type.SET);
		or.put(new HashMap<Type, Type>() {{put(Type.SET, Type.STRING);}}, Type.SET);
		or.put(new HashMap<Type, Type>() {{put(Type.SET, Type.BOOL);}}, Type.SET);
		or.put(new HashMap<Type, Type>() {{put(Type.SET, Type.LIST);}}, Type.SET);
		or.put(new HashMap<Type, Type>() {{put(Type.SET, Type.TUPLE);}}, Type.SET);
		or.put(new HashMap<Type, Type>() {{put(Type.SET, Type.SET);}}, Type.SET);
		or.put(new HashMap<Type, Type>() {{put(Type.SET, Type.DICT);}}, Type.SET);
		or.put(new HashMap<Type, Type>() {{put(Type.DICT, Type.INT);}}, Type.DICT);
		or.put(new HashMap<Type, Type>() {{put(Type.DICT, Type.FLOAT);}}, Type.DICT);
		or.put(new HashMap<Type, Type>() {{put(Type.DICT, Type.STRING);}}, Type.DICT);
		or.put(new HashMap<Type, Type>() {{put(Type.DICT, Type.BOOL);}}, Type.DICT);
		or.put(new HashMap<Type, Type>() {{put(Type.DICT, Type.LIST);}}, Type.DICT);
		or.put(new HashMap<Type, Type>() {{put(Type.DICT, Type.TUPLE);}}, Type.DICT);
		or.put(new HashMap<Type, Type>() {{put(Type.DICT, Type.SET);}}, Type.DICT);
		or.put(new HashMap<Type, Type>() {{put(Type.DICT, Type.DICT);}}, Type.DICT);
        inference.put(BinaryOperator.OR, or);
    }
}