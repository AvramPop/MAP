package avram.pop.model.statement;

import avram.pop.model.value.Value;
import avram.pop.utils.MyException;
import avram.pop.model.control.ProgramState;
import avram.pop.model.type.Type;
import avram.pop.utils.DictionaryInterface;

public class VariableDeclareStatement implements Statement {
    private String name;
    private Type typ;

    @Override
    public String toString(){
        return "VarDeclStmt{" +
                "name='" + name + '\'' +
                ", typ=" + typ +
                '}';
    }

    public VariableDeclareStatement(String name, Type typ){
        this.name = name;
        this.typ = typ;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        DictionaryInterface<String, Value> symTbl = state.getSymTable();
        if(!symTbl.isDefined(name)){
            symTbl.update(name, typ.defaultValue());
        } else {
            throw new MyException("variable is already defined");
        }
        return null;
    }
}
