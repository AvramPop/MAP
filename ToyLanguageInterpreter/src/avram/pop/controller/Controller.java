package avram.pop.controller;

import avram.pop.repository.MyIRepository;
import avram.pop.repository.MyRepository;
import avram.pop.utils.MyException;
import avram.pop.model.statement.IStmt;
import avram.pop.utils.MyIStack;
import avram.pop.model.control.PrgState;

public class Controller {
    MyIRepository repo;

    public Controller(MyIRepository repo){
        this.repo = repo;
    }

    public PrgState oneStep(PrgState state) throws MyException{
        MyIStack<IStmt> stk = state.getStk();
        if(stk.isEmpty()) throw new MyException("program stack is empty");
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    public void allStep() throws MyException{
        PrgState prg = repo.getCrtPrg();
        System.out.println(prg.toString());
        while(!prg.getStk().isEmpty()){
            oneStep(prg);
            System.out.println(prg.toString());
        }
    }
}
