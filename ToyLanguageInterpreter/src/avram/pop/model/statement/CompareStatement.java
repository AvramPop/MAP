package avram.pop.model.statement;

import avram.pop.utils.MyException;
import avram.pop.utils.StackInterface;
import avram.pop.model.control.ProgramState;

public class CompareStatement implements Statement {
    private Statement first;
    private Statement snd;

    public CompareStatement(Statement first, Statement snd){
        this.first = first;
        this.snd = snd;
    }

    public String toString(){
        return "(" + first.toString() + "; " + snd.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws MyException{
        StackInterface<Statement> stk = state.getStk();
        stk.push(snd);
        stk.push(first);
        return state;
    }
}
