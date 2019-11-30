package avram.pop.model.statement;

import avram.pop.model.control.ProgramState;
import avram.pop.model.expression.Expression;
import avram.pop.model.type.StringType;
import avram.pop.model.value.Value;
import avram.pop.model.value.StringValue;
import avram.pop.utils.MyException;
import avram.pop.utils.DictionaryInterface;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFileStatement implements Statement {
    private Expression expression;

    public CloseReadFileStatement(Expression expression){
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();
        if(expression.evaluate(symbolTable, state.getHeap()).getType().equals(new StringType())){
            Value value = expression.evaluate(symbolTable, state.getHeap());
            StringValue expAsStringValue = (StringValue) value;
            if(fileTable.isDefined(expAsStringValue)){
                BufferedReader bufferedReader = fileTable.lookup(expAsStringValue);
                try{
                    bufferedReader.close();
                    fileTable.removeEntry(expAsStringValue);
                } catch(IOException e){
                    throw new MyException("exception while closing file");
                }
            } else {
                throw new MyException("file not opened");
            }
        } else {
            throw new MyException("expression not string");
        }
        return null;
    }

    @Override
    public String toString(){
        return "CloseRFileStmt{" +
                "exp=" + expression +
                '}';
    }
}
