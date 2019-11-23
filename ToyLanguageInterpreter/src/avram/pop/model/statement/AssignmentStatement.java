package avram.pop.model.statement;

import avram.pop.utils.MyException;
import avram.pop.utils.DictionaryInterface;
import avram.pop.model.control.ProgramState;
import avram.pop.model.type.Type;
import avram.pop.model.value.Value;
import avram.pop.model.expression.Expression;

public class AssignmentStatement implements Statement {
    private String variableName;
    private Expression expression;

    public AssignmentStatement(String variableName, Expression expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    public String toString(){
        return variableName + " = " + expression.toString();
    }

    public ProgramState execute(ProgramState state) throws MyException{
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();

        if(symbolTable.isDefined(variableName)){
            Value value = expression.evaluate(symbolTable, state.getHeap());
            Type variableType = (symbolTable.lookup(variableName)).getType();
            if(value.getType().equals(variableType)){
                symbolTable.update(variableName, value);
            } else {
                throw new MyException("declared type of variable" + variableName + " and type of the assigned expression do not match ");
            }
        } else {
            throw new MyException("the used variable" + variableName + " was not declared before");
        }
        return state;
    }
}