package avram.pop.model.statement;

import avram.pop.model.control.ProgramState;
import avram.pop.model.type.Type;
import avram.pop.utils.CloneFactory;
import avram.pop.utils.DictionaryInterface;
import avram.pop.utils.MyException;
import avram.pop.utils.MyStack;

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
