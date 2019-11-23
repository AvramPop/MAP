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

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        if(symbolTable.isDefined(variableName)){
            Type variableType = symbolTable.lookup(variableName).getType();
            if(variableType instanceof ReferenceType){
                Value expressionValue = expression.evaluate(symbolTable, state.getHeap());
                ReferenceValue referenceValue = (ReferenceValue) expressionValue;
                if(referenceValue.getType().equals(variableType)){
                    HeapInterface<Integer, Value> heap = state.getHeap();
                    heap.add(expressionValue);
                    symbolTable.update(variableName, new ReferenceValue(heap.getLastAllocatedLocation(), variableType));
                } else {
                    throw new MyException("variable and expression not of same type");
                }
            } else {
                throw new MyException("variable is not reference type");
            }
        } else {
            throw new MyException("variable not defined");
        }
        return state;
    }
}
