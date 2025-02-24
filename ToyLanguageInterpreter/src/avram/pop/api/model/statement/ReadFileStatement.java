package avram.pop.api.model.statement;

import avram.pop.api.model.control.ProgramState;
import avram.pop.api.model.expression.Expression;
import avram.pop.api.model.type.IntType;
import avram.pop.api.model.type.StringType;
import avram.pop.api.model.type.Type;
import avram.pop.api.model.value.Value;
import avram.pop.api.model.value.IntValue;
import avram.pop.api.model.value.StringValue;
import avram.pop.api.utils.MyException;
import avram.pop.api.utils.DictionaryInterface;

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
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws MyException{
        if(expression.typecheck(typeEnvironment).equals(new StringType())){
            if(typeEnvironment.lookup(variableName).equals(new IntType())){
                return typeEnvironment;
            } else throw new MyException("trying to assign value from file to non-integer variable");
        } else throw new MyException("trying to read from file given not as string!");
    }

    @Override
    public String toString(){
        return "ReadFileStmt{" +
                "exp=" + expression +
                ", varName='" + variableName + '\'' +
                '}';
    }
}
