package avram.pop.model.statement;

import avram.pop.utils.MyException;
import avram.pop.utils.MyIDictionary;
import avram.pop.utils.MyIStack;
import avram.pop.model.control.PrgState;
import avram.pop.model.type.Type;
import avram.pop.model.value.Value;
import avram.pop.model.expression.Exp;

public class AssignStmt {
    private String id;
    private Exp exp;

    public String toString(){
        return id + "=" + exp.toString();
    }

    public PrgState execute(PrgState state) throws MyException{
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        if(symTbl.isDefined(id)){
            Value val = exp.eval(symTbl);
            Type typId = (symTbl.lookup(id)).getType();
            if(val.getType().equals(typId)){
                symTbl.update(id, val);
            } else {
                throw new MyException("declared type of variable" + id + " and type of the assigned expression do not match ");
            }
        } else{
            throw new MyException("the used variable" + id + " was not declared before");
        }
        return state;
    }
}