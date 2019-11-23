package avram.pop.model.statement;

import avram.pop.model.control.ProgramState;
import avram.pop.model.expression.Expression;
import avram.pop.model.type.StringType;
import avram.pop.model.value.Value;
import avram.pop.model.value.StringValue;
import avram.pop.utils.MyException;
import avram.pop.utils.DictionaryInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFileStatement implements Statement {
    private Expression expression;

    public OpenReadFileStatement(Expression expression){
        this.expression = expression;
    }

    @Override
    public String toString(){
        return "OpenRFileStmt{" +
                "exp=" + expression +
                '}';
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();
        if(expression.evaluate(symbolTable, state.getHeap()).getType().equals(new StringType())){
            Value value = expression.evaluate(symbolTable, state.getHeap());
            StringValue expressionAsStringValue = (StringValue) value;
            if(!fileTable.isDefined(expressionAsStringValue)){
                try{
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(expressionAsStringValue.getValue()));
                    fileTable.update(expressionAsStringValue, bufferedReader);
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
