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
    private Expression exp;

    public CloseReadFileStatement(Expression exp){
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        DictionaryInterface<String, Value> symTbl = state.getSymTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();
        if(exp.eval(symTbl).getType().equals(new StringType())){
            Value val = exp.eval(symTbl);
            StringValue expAsStringValue = (StringValue) val;
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
        return state;
    }

    @Override
    public String toString(){
        return "CloseRFileStmt{" +
                "exp=" + exp +
                '}';
    }
}
