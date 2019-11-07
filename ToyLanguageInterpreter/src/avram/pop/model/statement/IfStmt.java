package avram.pop.model.statement;

import avram.pop.model.type.BoolType;
import avram.pop.model.value.BoolValue;
import avram.pop.model.value.Value;
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
        Value cond = exp.eval(state.getSymTable());
        if(cond.getType().equals(new BoolType())){
            BoolValue booleanCondition = (BoolValue) cond;
            if(booleanCondition.getVal()){
                state.getStk().push(thenS);
            } else {
                state.getStk().push(elseS);
            }
        } else {
            throw new MyException("conditional expr is not a boolean");
        }
        return state;
    }
}
