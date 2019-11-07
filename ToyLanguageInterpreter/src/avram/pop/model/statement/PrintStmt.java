package avram.pop.model.statement;

import avram.pop.model.value.Value;
import avram.pop.utils.MyException;
import avram.pop.model.control.PrgState;
import avram.pop.model.expression.Exp;
import avram.pop.utils.MyIList;

public class PrintStmt implements IStmt {
    private Exp exp;

    public PrintStmt(Exp exp){
        this.exp = exp;
    }

    public String toString(){
        return ("print(" + exp.toString() + ")");
    }

    public PrgState execute(PrgState state) throws MyException{
        MyIList<Value> out = state.getOut();
        out.add(exp.eval(state.getSymTable()));
        return state;
    }
}
