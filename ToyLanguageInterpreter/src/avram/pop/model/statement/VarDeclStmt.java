package avram.pop.model.statement;

import avram.pop.utils.MyException;
import avram.pop.model.control.PrgState;
import avram.pop.model.type.Type;

public class VarDeclStmt implements IStmt {
    private String name;
    private Type typ;

    @Override
    public PrgState execute(PrgState state) throws MyException{
        return null;
    }
}
