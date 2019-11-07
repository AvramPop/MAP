package avram.pop.model.statement;

import avram.pop.utils.MyException;
import avram.pop.model.control.PrgState;

public class NopStmt implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException{
        return state;
    }
}
