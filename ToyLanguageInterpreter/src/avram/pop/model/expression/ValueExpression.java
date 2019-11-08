package avram.pop.model.expression;

import avram.pop.utils.MyException;
import avram.pop.utils.DictionaryInterface;
import avram.pop.model.value.Value;

public class ValueExpression implements Expression {
    private Value value;

    @Override
    public String toString(){
        return "ValueExp{" +
                "e=" + value +
                '}';
    }

    public ValueExpression(Value value){
        this.value = value;
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> symbolTable) throws MyException{
        return value;
    }
}
