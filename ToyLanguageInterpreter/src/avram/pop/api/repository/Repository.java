package avram.pop.api.repository;

import avram.pop.api.model.control.ProgramState;
import avram.pop.api.utils.MyException;

import java.util.List;

public interface Repository {
    void addState(ProgramState state);
    void logProgramState(ProgramState programState) throws MyException;
    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> programStates);

    void logMessage(String message) throws MyException;
}
