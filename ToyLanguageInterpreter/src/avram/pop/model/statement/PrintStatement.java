package avram.pop.model.statement;

import avram.pop.model.value.Value;
import avram.pop.utils.MyException;
import avram.pop.model.control.ProgramState;
import avram.pop.model.expression.Expression;
import avram.pop.utils.ListInterface;

public class PrintStatement implements Statement {
    private Expression expression;

    public PrintStatement(Expression expression){
        this.expression = expression;
    }

    public String toString(){
        return ("print(" + expression.toString() + ")");
    }

    public ProgramState execute(ProgramState state) throws MyException{
        ListInterface<Value> outputBuffer = state.getOutputBuffer();
        outputBuffer.add(expression.evaluate(state.getSymbolTable(), state.getHeap()));
        return state;
    }
}
