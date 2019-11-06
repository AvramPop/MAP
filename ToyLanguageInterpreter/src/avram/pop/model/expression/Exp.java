package avram.pop.model.expression;

import avram.pop.utils.MyException;
import avram.pop.utils.MyIDictionary;
import avram.pop.model.value.Value;

public interface Exp {
    Value eval(MyIDictionary<String, Value> tbl) throws MyException;
}
