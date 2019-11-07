package avram.pop.model.statement;

import avram.pop.model.type.IntType;
import avram.pop.model.value.BoolValue;
import avram.pop.model.value.IntValue;
import avram.pop.model.value.Value;
import avram.pop.utils.MyException;
import avram.pop.model.control.PrgState;
import avram.pop.model.type.Type;
import avram.pop.utils.MyIDictionary;

public class VarDeclStmt implements IStmt {
    private String name;
    private Type typ;

    @Override
    public String toString(){
        return "VarDeclStmt{" +
                "name='" + name + '\'' +
                ", typ=" + typ +
                '}';
    }

    public VarDeclStmt(String name, Type typ){
        this.name = name;
        this.typ = typ;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if(!symTbl.isDefined(name)){
            if(typ.equals(new IntType())){
                symTbl.update(name, new IntValue(0));
            } else {
                symTbl.update(name, new BoolValue(false));
            }
        } else {
            throw new MyException("variable is already defined");
        }
        return null;
    }
}
