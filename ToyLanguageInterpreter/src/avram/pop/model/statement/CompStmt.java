package avram.pop.model.statement;

import avram.pop.utils.MyException;
import avram.pop.utils.MyIStack;
import avram.pop.model.control.ProgramState;

public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt snd;

    public CompStmt(IStmt first, IStmt snd){
        this.first = first;
        this.snd = snd;
    }

    public String toString(){
        return "(" + first.toString() + "; " + snd.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws MyException{
        MyIStack<IStmt> stk = state.getStk();
        stk.push(snd);
        stk.push(first);
        return state;
    }
}
