package avram.pop.model.expression;

import avram.pop.model.value.IntValue;
import avram.pop.utils.MyException;
import avram.pop.utils.MyIDictionary;
import avram.pop.model.value.Value;

public class ValueExp implements Exp {
    private Value e;

    @Override
    public String toString(){
        return "ValueExp{" +
                "e=" + e +
                '}';
    }

    public ValueExp(Value value){
        e = value;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl) throws MyException{
        return e;
    }
}
