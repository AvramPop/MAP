package avram.pop.model.statement;

import avram.pop.utils.MyException;
import avram.pop.model.control.PrgState;
import avram.pop.model.expression.Exp;

public class IfStmt implements IStmt {
    private Exp exp;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el){
        exp = e;
        thenS = t;
        elseS = el;
    }

    public String toString(){
        return "(IF(" + exp.toString() + ") THEN(" + thenS.toString()
                + ")ELSE(" + elseS.toString() + "))";
    }

    public PrgState execute(PrgState state) throws MyException{

        return state;
    }
}
