package avram.pop.model.expression;

import avram.pop.utils.MyException;
import avram.pop.utils.MyIDictionary;
import avram.pop.model.value.Value;

public class VarExp implements Exp {
    private String id;

    public Value eval(MyIDictionary<String, Value> tbl) throws MyException{
        return tbl.lookup(id);
    }
}
