package avram.pop.api.model.statement;

import avram.pop.api.utils.MyException;
import avram.pop.api.utils.DictionaryInterface;
import avram.pop.api.model.control.ProgramState;
import avram.pop.api.model.type.Type;
import avram.pop.api.model.value.Value;
import avram.pop.api.model.expression.Expression;

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
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws MyException{
        Type variableType = typeEnvironment.lookup(variableName);
        Type expressionType = expression.typecheck(typeEnvironment);
        if (variableType.equals(expressionType))
            return typeEnvironment;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types ");
    }
}