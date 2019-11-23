package avram.pop.model.statement;

import avram.pop.model.control.ProgramState;
import avram.pop.model.expression.Expression;
import avram.pop.model.type.IntType;
import avram.pop.model.type.StringType;
import avram.pop.model.value.Value;
import avram.pop.model.value.IntValue;
import avram.pop.model.value.StringValue;
import avram.pop.utils.MyException;
import avram.pop.utils.DictionaryInterface;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements Statement {
    private Expression expression;
    private String variableName;

    public ReadFileStatement(Expression expression, String variableName){
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();
        if(symbolTable.isDefined(variableName)){
            Value variableValue = symbolTable.lookup(variableName);
            if(variableValue.getType().equals(new IntType())){
                Value expressionValue = expression.evaluate(symbolTable, state.getHeap());
                if(expressionValue.getType().equals(new StringType())){
                    StringValue expressionValueAsStringValue = (StringValue) expressionValue;
                    if(fileTable.isDefined(expressionValueAsStringValue)){
                        BufferedReader bufferedReader = fileTable.lookup(expressionValueAsStringValue);
                        try{
                            String lineReadFromBuffer = bufferedReader.readLine();
                            int value;
                            if(lineReadFromBuffer != null){
                                value = Integer.parseInt(lineReadFromBuffer);
                            } else {
                                value = 0;
                            }
                            symbolTable.update(variableName, new IntValue(value));
                        } catch(IOException e){
                            throw new MyException("IO error");
                        }
                    } else {
                        throw new MyException("file with name given not defined");
                    }
                } else {
                    throw new MyException("expression not string");
                }
            } else {
                throw new MyException("variable not int");
            }
        } else {
            throw new MyException("variable not defined");
        }
        return state;
    }

    @Override
    public String toString(){
        return "ReadFileStmt{" +
                "exp=" + expression +
                ", varName='" + variableName + '\'' +
                '}';
    }
}
