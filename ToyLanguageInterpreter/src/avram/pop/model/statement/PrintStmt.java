package avram.pop.model.statement;

import avram.pop.utils.MyException;
import avram.pop.model.control.PrgState;
import avram.pop.model.expression.Exp;

public class PrintStmt implements IStmt {
    private Exp exp;

    public String toString(){
        return ("print(" + exp.toString() + ")");
    }

    public PrgState execute(PrgState state) throws MyException{
        return state;
    }
}
