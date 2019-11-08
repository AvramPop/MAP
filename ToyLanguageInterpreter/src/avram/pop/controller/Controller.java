package avram.pop.controller;

import avram.pop.repository.Repository;
import avram.pop.utils.MyException;
import avram.pop.model.statement.Statement;
import avram.pop.utils.StackInterface;
import avram.pop.model.control.ProgramState;

public class Controller {
    private Repository repo;

    public Controller(Repository repo){
        this.repo = repo;
    }

    public ProgramState oneStep(ProgramState state) throws MyException{
        StackInterface<Statement> stk = state.getStk();
        if(stk.isEmpty()) throw new MyException("program stack is empty");
        Statement crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    public void allStep() throws MyException{
        ProgramState prg = repo.getCrtPrg();
        repo.logPrgStateExec();
        while(!prg.getStk().isEmpty()){
            oneStep(prg);
            repo.logPrgStateExec();
        }
    }
}
