package avram.pop.controller;

import avram.pop.repository.Repository;
import avram.pop.utils.MyException;
import avram.pop.model.statement.Statement;
import avram.pop.utils.StackInterface;
import avram.pop.model.control.ProgramState;

public class Controller {
    private Repository repository;

    public Controller(Repository repository){
        this.repository = repository;
    }

    private ProgramState oneStep(ProgramState state) throws MyException{
        StackInterface<Statement> stack = state.getExecutionStack();
        if(stack.isEmpty()) throw new MyException("program stack is empty");
        Statement currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    public void allStep() throws MyException{
        ProgramState programState = repository.getCurrentProgram();
        repository.logProgramState();
        while(!programState.getExecutionStack().isEmpty()){
            oneStep(programState);
            repository.logProgramState();
        }
    }
}
