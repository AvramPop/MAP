package avram.pop.model.statement;

import avram.pop.model.value.IValue;
import avram.pop.utils.MyException;
import avram.pop.model.control.ProgramState;
import avram.pop.model.expression.Expression;
import avram.pop.utils.MyIList;

public class PrintStmt implements IStmt {
    private Expression expression;

    public PrintStmt(Expression expression){
        this.expression = expression;
    }

    public String toString(){
        return ("print(" + expression.toString() + ")");
    }

    public ProgramState execute(ProgramState state) throws MyException{
        MyIList<IValue> out = state.getOut();
        out.add(expression.eval(state.getSymTable()));
        return state;
    }
}
