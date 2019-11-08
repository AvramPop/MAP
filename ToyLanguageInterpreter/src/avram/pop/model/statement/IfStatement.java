package avram.pop.model.statement;

import avram.pop.model.type.BoolType;
import avram.pop.model.value.BoolValue;
import avram.pop.model.value.Value;
import avram.pop.utils.MyException;
import avram.pop.model.control.ProgramState;
import avram.pop.model.expression.Expression;

public class IfStatement implements Statement {
    private Expression expression;
    private Statement thenS;
    private Statement elseS;

    public IfStatement(Expression e, Statement t, Statement el){
        expression = e;
        thenS = t;
        elseS = el;
    }

    public String toString(){
        return "(IF(" + expression.toString() + ") THEN(" + thenS.toString()
                + ")ELSE(" + elseS.toString() + "))";
    }

    public ProgramState execute(ProgramState state) throws MyException{
        Value cond = expression.eval(state.getSymTable());
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
