package avram.pop.api.model.expression;

import avram.pop.api.model.type.Type;
import avram.pop.api.model.value.Value;
import avram.pop.api.utils.DictionaryInterface;
import avram.pop.api.utils.HeapInterface;
import avram.pop.api.utils.MyException;

public class VariableExpression implements Expression {
    private String variableName;

    @Override
    public String toString(){
        return "VarExp{" +
                "id='" + variableName + '\'' +
                '}';
    }

    public VariableExpression(String variableName){
        this.variableName = variableName;
    }

    public Value evaluate(DictionaryInterface<String, Value> symbolTable, HeapInterface<Integer, Value> heap) throws MyException{
        if(symbolTable.isDefined(variableName)) return symbolTable.lookup(variableName);
        else throw new MyException("variable " + variableName + " is not defined");
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnvironment) throws MyException{
        return typeEnvironment.lookup(variableName);
    }
}
