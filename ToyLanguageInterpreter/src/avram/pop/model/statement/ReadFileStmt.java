package avram.pop.model.statement;

import avram.pop.model.control.ProgramState;
import avram.pop.model.expression.Expression;
import avram.pop.model.type.IntType;
import avram.pop.model.type.StringType;
import avram.pop.model.value.IValue;
import avram.pop.model.value.IntValue;
import avram.pop.model.value.StringValue;
import avram.pop.utils.MyException;
import avram.pop.utils.MyIDictionary;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements IStmt {
    private Expression exp;
    private String varName;

    public ReadFileStmt(Expression exp, String varName){
        this.exp = exp;
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        MyIDictionary<String, IValue> symbolTable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        if(symbolTable.isDefined(varName)){
            IValue variableValue = symbolTable.lookup(varName);
            if(variableValue.getType().equals(new IntType())){
                IValue expressionValue = exp.eval(symbolTable);
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
                            symbolTable.update(varName, new IntValue(value));
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
                "exp=" + exp +
                ", varName='" + varName + '\'' +
                '}';
    }
}
