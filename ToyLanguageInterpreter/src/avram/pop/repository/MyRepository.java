package avram.pop.repository;

import avram.pop.model.control.ProgramState;
import avram.pop.utils.MyException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MyRepository implements MyIRepository {
    private List<ProgramState> states;
    private Path logFilePath;

    public MyRepository(String logFilePath) throws MyException{
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
    public void logPrgStateExec() throws MyException{
        PrintWriter logFile;
        try{
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath.toFile(), true)));
        } catch(IOException e){
            throw new MyException("files error");
        }
        String log = getCrtPrg().toLogString();

        logFile.print(log);
        logFile.close();
    }

    @Override
    public ProgramState getCrtPrg(){
        return states.get(0);
    }
}
