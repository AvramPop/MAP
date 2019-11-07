package avram.pop.model.expression;

import avram.pop.utils.MyException;
import avram.pop.utils.MyIDictionary;
import avram.pop.model.value.Value;

public class VarExp implements Exp {
    private String id;

    @Override
    public String toString(){
        return "VarExp{" +
                "id='" + id + '\'' +
                '}';
    }

    public VarExp(String v){
        id = v;
    }

    public Value eval(MyIDictionary<String, Value> tbl) throws MyException{
        if(tbl.isDefined(id)) return tbl.lookup(id);
        else throw new MyException("variable " + id + " is not defined");
    }
}
