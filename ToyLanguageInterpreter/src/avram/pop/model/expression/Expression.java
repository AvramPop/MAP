package avram.pop.model.expression;

import avram.pop.model.type.Type;
import avram.pop.model.value.Value;
import avram.pop.utils.DictionaryInterface;
import avram.pop.utils.HeapInterface;
import avram.pop.utils.MyException;

public interface Expression {
    Value evaluate(DictionaryInterface<String, Value> symbolTable, HeapInterface<Integer, Value> heap) throws MyException;
    Type typecheck(DictionaryInterface<String, Type> typeEnvironment) throws MyException;
}
