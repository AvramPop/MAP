package avram.pop.model.expression;

import avram.pop.model.type.ReferenceType;
import avram.pop.model.value.ReferenceValue;
import avram.pop.model.value.Value;
import avram.pop.utils.DictionaryInterface;
import avram.pop.utils.HeapInterface;
import avram.pop.utils.MyException;

public class HeapReadingExpression implements Expression{
    private Expression expression;

    public HeapReadingExpression(Expression expression){
        this.expression = expression;
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> symbolTable, HeapInterface<Integer, Value> heap) throws MyException{
        Value expressionEvaluation = expression.evaluate(symbolTable, heap);
        if(expressionEvaluation.getType() instanceof ReferenceType){
            ReferenceValue referenceValue = (ReferenceValue) expressionEvaluation;
            try{
                return heap.get(referenceValue.getAddress());
            } catch(MyException e){
                throw new MyException("location not found in heap");
            }
        } else {
            throw new MyException("expression evaluation not reference");
        }
    }
}
