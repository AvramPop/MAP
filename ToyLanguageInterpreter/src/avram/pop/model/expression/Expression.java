package avram.pop.model.expression;

import avram.pop.utils.HeapInterface;
import avram.pop.utils.MyException;
import avram.pop.utils.DictionaryInterface;
import avram.pop.model.value.Value;

public interface Expression {
    Value evaluate(DictionaryInterface<String, Value> symbolTable, HeapInterface<Integer, Value> heap) throws MyException;
}
