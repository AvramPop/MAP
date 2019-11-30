package avram.pop.repository;

import avram.pop.model.control.ProgramState;
import avram.pop.utils.MyException;

import java.util.List;

public interface Repository {
    void addState(ProgramState state);
    void logProgramState(ProgramState programState) throws MyException;
    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> programStates);

    void logMessage(String message) throws MyException;
}
