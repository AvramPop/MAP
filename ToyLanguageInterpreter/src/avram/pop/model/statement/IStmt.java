package avram.pop.model.statement;

import avram.pop.utils.MyException;
import avram.pop.model.control.ProgramState;

public interface IStmt {
    ProgramState execute(ProgramState state) throws MyException;
}
