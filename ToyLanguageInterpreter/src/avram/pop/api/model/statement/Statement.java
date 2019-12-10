package avram.pop.api.model.statement;

import avram.pop.api.model.control.ProgramState;
import avram.pop.api.model.type.Type;
import avram.pop.api.utils.DictionaryInterface;
import avram.pop.api.utils.MyException;

public interface Statement {
    ProgramState execute(ProgramState state) throws MyException;
    DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws MyException;
}
