package avram.pop.model.expression;

import avram.pop.model.type.Type;
import avram.pop.model.value.Value;
import avram.pop.utils.DictionaryInterface;
import avram.pop.utils.HeapInterface;
import avram.pop.utils.MyException;

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
