package avram.pop.controller;

import avram.pop.repository.Repository;
import avram.pop.utils.MyException;
import avram.pop.model.statement.IStmt;
import avram.pop.utils.MyIStack;
import avram.pop.model.control.PrgState;

public class Controller {
    Repository repo;

    public Controller(Repository repo){
        this.repo = repo;
    }

    public PrgState oneStep(PrgState state) throws MyException{
        MyIStack<IStmt> stk = state.getStk();
        if(stk.isEmpty()) throw new MyException("prgstate stack is empty");
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    public void allStep() throws MyException{
        PrgState prg = repo.getCrtPrg(); // repo is the controller field of type MyRepoInterface
        //here you can display the prg state
        while(!prg.getStk().isEmpty()){
            oneStep(prg);
            //here you can display the prg state
        }
    }
}
