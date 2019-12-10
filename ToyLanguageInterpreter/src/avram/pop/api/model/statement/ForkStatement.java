package avram.pop.api.model.statement;

import avram.pop.api.model.control.ProgramState;
import avram.pop.api.model.type.Type;
import avram.pop.api.utils.CloneFactory;
import avram.pop.api.utils.DictionaryInterface;
import avram.pop.api.utils.MyException;
import avram.pop.api.utils.MyStack;

public class ForkStatement implements Statement{
    private Statement statement;

    public ForkStatement(Statement statement){
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state){
        return new ProgramState(new MyStack<>(), CloneFactory.cloneSymbolTable(state.getSymbolTable()), state.getOutputBuffer(), state.getFileTable(), statement, state.getHeap());
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws MyException{
        return statement.typecheck(CloneFactory.cloneTypeEnvironment(typeEnvironment));
    }
}
