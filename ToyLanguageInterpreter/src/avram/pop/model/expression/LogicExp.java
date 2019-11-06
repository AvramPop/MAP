package avram.pop.model.expression;

import avram.pop.model.type.BoolType;
import avram.pop.model.type.IntType;
import avram.pop.model.value.BoolValue;
import avram.pop.utils.MyException;
import avram.pop.utils.MyIDictionary;
import avram.pop.model.value.Value;

public class LogicExp implements Exp {
    private Exp e1;
    private Exp e2;
    private int op; // 1 for and 2 for or

    public LogicExp(Exp e1, Exp e2, int op){
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl) throws MyException{
        Value v1, v2;
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
}
