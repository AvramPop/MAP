package avram.pop.model.statement;

import avram.pop.utils.MyException;
import avram.pop.model.control.ProgramState;

public interface Statement {
    ProgramState execute(ProgramState state) throws MyException;
}
