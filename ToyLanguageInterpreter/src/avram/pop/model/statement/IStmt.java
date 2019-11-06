package avram.pop.model.statement;

import avram.pop.utils.MyException;
import avram.pop.model.control.PrgState;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
}
