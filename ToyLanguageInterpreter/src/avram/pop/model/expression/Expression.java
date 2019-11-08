package avram.pop.model.expression;

import avram.pop.utils.MyException;
import avram.pop.utils.DictionaryInterface;
import avram.pop.model.value.Value;

public interface Expression {
    Value eval(DictionaryInterface<String, Value> tbl) throws MyException;
}
