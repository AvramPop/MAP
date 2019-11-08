package avram.pop.model.statement;

import avram.pop.model.value.IValue;
import avram.pop.utils.MyException;
import avram.pop.model.control.ProgramState;
import avram.pop.model.type.IType;
import avram.pop.utils.MyIDictionary;

public class VarDeclStmt implements IStmt {
    private String name;
    private IType typ;

    @Override
    public String toString(){
        return "VarDeclStmt{" +
                "name='" + name + '\'' +
                ", typ=" + typ +
                '}';
    }

    public VarDeclStmt(String name, IType typ){
        this.name = name;
        this.typ = typ;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        MyIDictionary<String, IValue> symTbl = state.getSymTable();
        if(!symTbl.isDefined(name)){
            symTbl.update(name, typ.defaultValue());
        } else {
            throw new MyException("variable is already defined");
        }
        return null;
    }
}
