package avram.pop.model.statement;

import avram.pop.model.control.ProgramState;
import avram.pop.model.expression.Expression;
import avram.pop.model.value.ReferenceValue;
import avram.pop.model.value.Value;
import avram.pop.utils.DictionaryInterface;
import avram.pop.utils.HeapInterface;
import avram.pop.utils.MyException;

public class WriteHeapStatement implements Statement{
    private String variableName;
    private Expression expression;

    public WriteHeapStatement(String variableName, Expression expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public String toString(){
        return "WriteHeapStatement{" +
                "variableName='" + variableName + '\'' +
                ", expression=" + expression +
                '}';
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface<Integer, Value> heap = state.getHeap();
        if(symbolTable.isDefined(variableName)){
            Value variableValue = symbolTable.lookup(variableName);
            if(variableValue instanceof ReferenceValue){
                ReferenceValue reference = (ReferenceValue) variableValue;
                if(heap.isDefined(reference.getAddress())){
                    Value expressionEvaluation = expression.evaluate(symbolTable, heap);
                    if(expressionEvaluation.getType().equals(((ReferenceValue)reference).getLocationType())){
                        heap.update(reference.getAddress(), expressionEvaluation);
                    } else {
                        throw new MyException("expression and reference not of same type");
                    }
                } else {
                    throw new MyException("reference not in heap");
                }
            } else {
                throw new MyException("type is not reference type");
            }
        } else {
            throw new MyException("variable not defined");
        }
        return null;
    }
}
