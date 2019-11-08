package avram.pop.model.expression;

import avram.pop.model.type.BoolType;
import avram.pop.model.value.BoolValue;
import avram.pop.utils.MyException;
import avram.pop.utils.MyIDictionary;
import avram.pop.model.value.IValue;

public class LogicExp implements Expression {
    private Expression e1;
    private Expression e2;
    private int op; // 1 for and 2 for or

    public LogicExp(Expression e1, Expression e2, String op){
        this.e1 = e1;
        this.e2 = e2;
        if(op.equals("and")){
            this.op = 1;
        } else if(op.equals("or")){
            this.op = 2;
        }
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl) throws MyException{
        IValue v1, v2;
        v1 = e1.eval(tbl);
        if(v1.getType().equals(new BoolType())){
            v2 = e2.eval(tbl);
            if(v2.getType().equals(new BoolType())){
                BoolValue b1 = (BoolValue) v1;
                BoolValue b2 = (BoolValue) v2;
                boolean bool1 = b1.getVal();
                boolean bool2 = b2.getVal();
                return new BoolValue(op == 1 ? bool1 && bool2 : bool1 || bool2);
            } else throw new MyException("Operand2 is not a boolean");
        } else throw new MyException("Operand1 is not a boolean");
    }

    @Override
    public String toString(){
        return "LogicExp{" +
                "e1=" + e1 +
                ", e2=" + e2 +
                ", op=" + op +
                '}';
    }
}
