package avram.pop.repository;

import avram.pop.model.control.ProgramState;
import avram.pop.utils.MyException;

public interface Repository {
    ProgramState getCurrentProgram();
    void addState(ProgramState state);
    void logProgramState() throws MyException;
}
