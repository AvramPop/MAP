package avram.pop.api.model.expression;

import avram.pop.api.model.type.Type;
import avram.pop.api.model.value.Value;
import avram.pop.api.utils.DictionaryInterface;
import avram.pop.api.utils.HeapInterface;
import avram.pop.api.utils.MyException;

public interface Expression {
    Value evaluate(DictionaryInterface<String, Value> symbolTable, HeapInterface<Integer, Value> heap) throws MyException;
    Type typecheck(DictionaryInterface<String, Type> typeEnvironment) throws MyException;
}
