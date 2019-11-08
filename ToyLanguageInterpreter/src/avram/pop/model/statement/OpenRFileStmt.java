package avram.pop.model.statement;

import avram.pop.model.control.ProgramState;
import avram.pop.model.expression.Expression;
import avram.pop.model.type.StringType;
import avram.pop.model.value.IValue;
import avram.pop.model.value.StringValue;
import avram.pop.utils.MyException;
import avram.pop.utils.MyIDictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFileStmt implements IStmt {
    private Expression exp;

    public OpenRFileStmt(Expression exp){
        this.exp = exp;
    }

    @Override
    public String toString(){
        return "OpenRFileStmt{" +
                "exp=" + exp +
                '}';
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        MyIDictionary<String, IValue> symTbl = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        if(exp.eval(symTbl).getType().equals(new StringType())){
            IValue val = exp.eval(symTbl);
            StringValue expAsStringValue = (StringValue) val;
            if(!fileTable.isDefined(expAsStringValue)){
                try{
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(expAsStringValue.getVal()));
                    fileTable.update(expAsStringValue, bufferedReader);
                } catch(FileNotFoundException e){
                    throw new MyException("error opening file");
                }
            } else {
                throw new MyException("file already opened");
            }
        } else {
            throw new MyException("expression not string");
        }
        return state;
    }
}
