package avram.pop.model.statement;

import avram.pop.model.type.BoolType;
import avram.pop.model.value.BoolValue;
import avram.pop.model.value.IValue;
import avram.pop.utils.MyException;
import avram.pop.model.control.ProgramState;
import avram.pop.model.expression.Expression;

public class IfStmt implements IStmt {
    private Expression expression;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(Expression e, IStmt t, IStmt el){
        expression = e;
        thenS = t;
        elseS = el;
    }

    public String toString(){
        return "(IF(" + expression.toString() + ") THEN(" + thenS.toString()
                + ")ELSE(" + elseS.toString() + "))";
    }

    public ProgramState execute(ProgramState state) throws MyException{
        IValue cond = expression.eval(state.getSymTable());
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
