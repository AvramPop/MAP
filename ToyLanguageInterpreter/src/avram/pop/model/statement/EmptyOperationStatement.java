package avram.pop.model.statement;

import avram.pop.utils.MyException;
import avram.pop.model.control.ProgramState;

public class EmptyOperationStatement implements Statement {
    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        return state;
    }

}
