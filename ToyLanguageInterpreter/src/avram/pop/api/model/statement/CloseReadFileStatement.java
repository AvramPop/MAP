package avram.pop.api.model.statement;

import avram.pop.api.model.control.ProgramState;
import avram.pop.api.model.expression.Expression;
import avram.pop.api.model.type.StringType;
import avram.pop.api.model.type.Type;
import avram.pop.api.model.value.Value;
import avram.pop.api.model.value.StringValue;
import avram.pop.api.utils.MyException;
import avram.pop.api.utils.DictionaryInterface;

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
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws MyException{
        if(expression.typecheck(typeEnvironment).equals(new StringType())){
            return typeEnvironment;
        } else throw new MyException("trying to close file given not as string!");
    }

    @Override
    public String toString(){
        return "CloseRFileStmt{" +
                "exp=" + expression +
                '}';
    }
}
