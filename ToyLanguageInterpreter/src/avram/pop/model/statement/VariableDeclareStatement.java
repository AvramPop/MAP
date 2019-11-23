package avram.pop.model.statement;

import avram.pop.model.value.Value;
import avram.pop.utils.MyException;
import avram.pop.model.control.ProgramState;
import avram.pop.model.type.Type;
import avram.pop.utils.DictionaryInterface;

public class VariableDeclareStatement implements Statement {
    private String variableName;
    private Type variableType;

    @Override
    public String toString(){
        return "VarDeclStmt{" +
                "name='" + variableName + '\'' +
                ", typ=" + variableType +
                '}';
    }

    public VariableDeclareStatement(String variableName, Type variableType){
        this.variableName = variableName;
        this.variableType = variableType;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        if(!symbolTable.isDefined(variableName)){
            symbolTable.update(variableName, variableType.defaultValue());
        } else {
            throw new MyException("variable is already defined");
        }
        return null;
    }
}
