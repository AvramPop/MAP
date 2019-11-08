package avram.pop.repository;

import avram.pop.model.control.ProgramState;
import avram.pop.utils.MyException;

public interface MyIRepository {
    ProgramState getCrtPrg();
    void addState(ProgramState state);
    void logPrgStateExec() throws MyException;
}
