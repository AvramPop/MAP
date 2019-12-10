package avram.pop.api.repository;

import avram.pop.api.model.control.ProgramState;
import avram.pop.api.utils.MyException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ListRepository implements Repository {
    private List<ProgramState> states;
    private Path logFilePath;

    public ListRepository(String logFilePath) throws MyException{
        this.logFilePath = FileSystems.getDefault().getPath(logFilePath);
        clearLogFile(logFilePath);
        this.states = new ArrayList<>();
    }

    private void clearLogFile(String logFilePath) throws MyException{
        PrintWriter logFile;
        try{
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
        } catch(IOException e){
            throw new MyException("IO Exception");
        }
        logFile.print("");
        logFile.close();
    }

    public void addState(ProgramState state){
        states.add(state);
    }

    @Override
    public void logProgramState(ProgramState programState) throws MyException{
        PrintWriter logFile;
        try{
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath.toFile(), true)));
        } catch(IOException e){
            throw new MyException("files error");
        }
        String log = programState.toLogString();

        logFile.print(log);
        logFile.close();
    }

    @Override
    public List<ProgramState> getProgramList(){
        return states;
    }

    @Override
    public void setProgramList(List<ProgramState> programStates){
        states = programStates;
    }

    @Override
    public void logMessage(String message) throws MyException{
        PrintWriter logFile;
        try{
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath.toFile(), true)));
        } catch(IOException e){
            throw new MyException("files error");
        }

        logFile.print(message);
        logFile.close();
    }
}
