package avram.pop.api.model.expression;

import avram.pop.api.model.type.Type;
import avram.pop.api.model.value.Value;
import avram.pop.api.utils.DictionaryInterface;
import avram.pop.api.utils.HeapInterface;
import avram.pop.api.utils.MyException;

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
    public Value evaluate(DictionaryInterface<String, Value> symbolTable, HeapInterface<Integer, Value> heap) throws MyException{
        return value;
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnvironment) throws MyException{
        return value.getType();
    }
}
