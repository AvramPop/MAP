package avram.pop.controller;

import avram.pop.repository.MyIRepository;
import avram.pop.utils.MyException;
import avram.pop.model.statement.IStmt;
import avram.pop.utils.MyIStack;
import avram.pop.model.control.ProgramState;

public class Controller {
    private MyIRepository repo;

    public Controller(MyIRepository repo){
        this.repo = repo;
    }

    public ProgramState oneStep(ProgramState state) throws MyException{
        MyIStack<IStmt> stk = state.getStk();
        if(stk.isEmpty()) throw new MyException("program stack is empty");
        IStmt crtStmt = stk.pop();
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
