package avram.pop.model.expression;

import avram.pop.model.type.IntType;
import avram.pop.model.value.BoolValue;
import avram.pop.model.value.Value;
import avram.pop.model.value.IntValue;
import avram.pop.utils.MyException;
import avram.pop.utils.DictionaryInterface;

public class RelationalExpression implements Expression {
    private Expression e1;
    private Expression e2;
    private int op; // 1 for < 2 for <= 3 for == 4 for != 5 for > 6 for >=

    public RelationalExpression(Expression e1, Expression e2, String op){
        this.e1 = e1;
        this.e2 = e2;
        if(op.equals("<")){
            this.op = 1;
        } else if(op.equals("<=")){
            this.op = 2;
        } else if(op.equals("==")){
            this.op = 3;
        } else if(op.equals("!=")){
            this.op = 4;
        } else if(op.equals(">")){
            this.op = 5;
        } else if(op.equals(">=")){
            this.op = 6;
        }
    }

    @Override
    public Value eval(DictionaryInterface<String, Value> tbl) throws MyException{
        Value v1, v2;
        v1 = e1.eval(tbl);
        if(v1.getType().equals(new IntType())){
            v2 = e2.eval(tbl);
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int int1 = i1.getVal();
                int int2 = i2.getVal();
                if(op == 1){
                    return new BoolValue(int1 < int2);
                } else if(op == 2){
                    return new BoolValue(int1 <= int2);
                } else if(op == 3){
                    return new BoolValue(int1 == int2);
                } else if(op == 4){
                    return new BoolValue(int1 != int2);
                } else if(op == 5){
                    return new BoolValue(int1 > int2);
                } else if(op == 6){
                    return new BoolValue(int1 >= int2);
                }
            } else throw new MyException("Operand2 is not int");
        } else throw new MyException("Operand1 is not int");
        return null;
    }

    @Override
    public String toString(){
        return "RelationalExpression{" +
                "e1=" + e1 +
                ", e2=" + e2 +
                ", op=" + op +
                '}';
    }
}
