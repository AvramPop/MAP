package avram.pop.model.statement;

import avram.pop.model.type.Type;
import avram.pop.utils.DictionaryInterface;
import avram.pop.utils.MyException;
import avram.pop.model.control.ProgramState;

public class EmptyOperationStatement implements Statement {
    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws MyException{
        return typeEnvironment;
    }

}
