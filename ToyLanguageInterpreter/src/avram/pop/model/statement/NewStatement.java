package avram.pop.model.statement;

import avram.pop.model.control.ProgramState;
import avram.pop.model.expression.Expression;
import avram.pop.model.type.ReferenceType;
import avram.pop.model.type.Type;
import avram.pop.model.value.ReferenceValue;
import avram.pop.model.value.Value;
import avram.pop.utils.DictionaryInterface;
import avram.pop.utils.HeapInterface;
import avram.pop.utils.MyException;

public class NewStatement implements Statement{
    private String variableName;
    private Expression expression;

    public NewStatement(String variableName, Expression expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    public String toString(){
        return "new " + variableName + " value " + expression.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        if(symbolTable.isDefined(variableName)){
            Value variableValue = symbolTable.lookup(variableName);
            if(variableValue.getType() instanceof ReferenceType){
                Value expressionValue = expression.evaluate(symbolTable, state.getHeap());
                if(expressionValue.getType().equals(((ReferenceValue) variableValue).getLocationType())){
                    HeapInterface<Integer, Value> heap = state.getHeap();
                    heap.add(expressionValue);
                    symbolTable.update(variableName, new ReferenceValue(heap.getLastAllocatedLocation(), ((ReferenceValue) variableValue).getLocationType()));
                } else {
                    throw new MyException("variable and expression not of same type");
                }
            } else {
                throw new MyException("variable is not reference type");
            }
        } else {
            throw new MyException("variable not defined");
        }
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws MyException{
        Type variableType = typeEnvironment.lookup(variableName);
        Type expressionType = expression.typecheck(typeEnvironment);
        if (variableType.equals(new ReferenceType(expressionType)))
            return typeEnvironment;
        else
            throw new MyException("NEW statement: right hand side and left hand side have different types ");
    }
}
