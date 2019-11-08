package avram.pop.model.expression;

import avram.pop.utils.MyException;
import avram.pop.utils.MyIDictionary;
import avram.pop.model.value.IValue;

public class ValueExp implements Expression {
    private IValue e;

    @Override
    public String toString(){
        return "ValueExp{" +
                "e=" + e +
                '}';
    }

    public ValueExp(IValue iValue){
        e = iValue;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl) throws MyException{
        return e;
    }
}
