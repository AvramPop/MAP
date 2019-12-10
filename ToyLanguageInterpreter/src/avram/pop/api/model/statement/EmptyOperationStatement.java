package avram.pop.api.model.statement;

import avram.pop.api.model.type.Type;
import avram.pop.api.utils.DictionaryInterface;
import avram.pop.api.utils.MyException;
import avram.pop.api.model.control.ProgramState;

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
