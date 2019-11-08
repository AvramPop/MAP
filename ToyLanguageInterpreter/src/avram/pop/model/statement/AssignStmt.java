package avram.pop.model.statement;

import avram.pop.utils.MyException;
import avram.pop.utils.MyIDictionary;
import avram.pop.model.control.ProgramState;
import avram.pop.model.type.IType;
import avram.pop.model.value.IValue;
import avram.pop.model.expression.Expression;

public class AssignStmt implements IStmt{
    private String id;
    private Expression expression;

    public AssignStmt(String v, Expression expression){
        id = v;
        this.expression = expression;
    }

    public String toString(){
        return id + "=" + expression.toString();
    }

    public ProgramState execute(ProgramState state) throws MyException{
        MyIDictionary<String, IValue> symTbl = state.getSymTable();

        if(symTbl.isDefined(id)){
            IValue val = expression.eval(symTbl);
            IType typId = (symTbl.lookup(id)).getType();
            if(val.getType().equals(typId)){
                symTbl.update(id, val);
            } else {
                throw new MyException("declared type of variable" + id + " and type of the assigned expression do not match ");
            }
        } else {
            throw new MyException("the used variable" + id + " was not declared before");
        }
        return state;
    }
}