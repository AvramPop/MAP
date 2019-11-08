package avram.pop.model.statement;

import avram.pop.model.control.ProgramState;
import avram.pop.model.expression.Expression;
import avram.pop.model.type.StringType;
import avram.pop.model.value.IValue;
import avram.pop.model.value.StringValue;
import avram.pop.utils.MyException;
import avram.pop.utils.MyIDictionary;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStmt implements IStmt {
    private Expression exp;

    public CloseRFileStmt(Expression exp){
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        MyIDictionary<String, IValue> symTbl = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        if(exp.eval(symTbl).getType().equals(new StringType())){
            IValue val = exp.eval(symTbl);
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
