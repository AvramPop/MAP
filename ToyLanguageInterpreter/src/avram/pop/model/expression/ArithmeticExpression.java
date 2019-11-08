package avram.pop.model.expression;

import avram.pop.utils.MyException;
import avram.pop.utils.DictionaryInterface;
import avram.pop.model.type.IntType;
import avram.pop.model.value.IntValue;
import avram.pop.model.value.Value;

public class ArithmeticExpression implements Expression {
    private Expression e1;
    private Expression e2;
    private int op; //1-plus, 2-minus, 3-star, 4-divide

    public ArithmeticExpression(char c, Expression valueExp, Expression valueExp1){
        e1 = valueExp;
        e2 = valueExp1;
        if(c == '+'){
            op = 1;
        } else if(c == '-'){
            op = 2;
        } else if(c == '*'){
            op = 3;
        } else {
            op = 4;
        }
    }

    @Override
    public String toString(){
        return "ArithExp{" +
                "e1=" + e1 +
                ", e2=" + e2 +
                ", op=" + op +
                '}';
    }

    public Value eval(DictionaryInterface<String, Value> tbl) throws MyException{
        Value v1, v2;
        v1 = e1.eval(tbl);
        if(v1.getType().equals(new IntType())){
            v2 = e2.eval(tbl);
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if(op == 1) return new IntValue(n1 + n2);
                if(op == 2) return new IntValue(n1 - n2);
                if(op == 3) return new IntValue(n1 * n2);
                if(op == 4){
                    if(n2 == 0) throw new MyException("division by zero");
                    else return new IntValue(n1 / n2);
                }
            } else{
                throw new MyException("second operand is not an integer");
            }
        } else{
            throw new MyException("first operand is not an integer");
        }
        return null;
    }
}
