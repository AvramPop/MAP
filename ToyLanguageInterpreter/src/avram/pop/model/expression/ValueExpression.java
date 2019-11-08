package avram.pop.model.expression;

import avram.pop.utils.MyException;
import avram.pop.utils.DictionaryInterface;
import avram.pop.model.value.Value;

public class ValueExpression implements Expression {
    private Value e;

    @Override
    public String toString(){
        return "ValueExp{" +
                "e=" + e +
                '}';
    }

    public ValueExpression(Value value){
        e = value;
    }

    @Override
    public Value eval(DictionaryInterface<String, Value> tbl) throws MyException{
        return e;
    }
}
