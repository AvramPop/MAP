package avram.pop.model.statement;

import avram.pop.model.control.ProgramState;
import avram.pop.model.type.Type;
import avram.pop.utils.DictionaryInterface;
import avram.pop.utils.MyException;

public interface Statement {
    ProgramState execute(ProgramState state) throws MyException;
    DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws MyException;
}
