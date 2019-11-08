package avram.pop.model.expression;

import avram.pop.utils.MyException;
import avram.pop.utils.MyIDictionary;
import avram.pop.model.value.IValue;

public interface Expression {
    IValue eval(MyIDictionary<String, IValue> tbl) throws MyException;
}
