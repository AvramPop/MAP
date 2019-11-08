package avram.pop.model.expression;

import avram.pop.utils.MyException;
import avram.pop.utils.DictionaryInterface;
import avram.pop.model.value.Value;

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

    public Value evaluate(DictionaryInterface<String, Value> symbolTable) throws MyException{
        if(symbolTable.isDefined(variableName)) return symbolTable.lookup(variableName);
        else throw new MyException("variable " + variableName + " is not defined");
    }
}
