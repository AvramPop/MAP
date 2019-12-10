package avram.pop.api.model.expression;

import avram.pop.api.model.type.ReferenceType;
import avram.pop.api.model.type.Type;
import avram.pop.api.model.value.ReferenceValue;
import avram.pop.api.model.value.Value;
import avram.pop.api.utils.DictionaryInterface;
import avram.pop.api.utils.HeapInterface;
import avram.pop.api.utils.MyException;

public class HeapReadingExpression implements Expression{
    private Expression expression;

    public HeapReadingExpression(Expression expression){
        this.expression = expression;
    }

    @Override
    public String toString(){
        return "HeapRead{" +
                "expr=" + expression +
                '}';
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> symbolTable, HeapInterface<Integer, Value> heap) throws MyException{
        Value expressionEvaluation = expression.evaluate(symbolTable, heap);
        if(expressionEvaluation instanceof ReferenceValue){
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

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnvironment) throws MyException{
        Type type = expression.typecheck(typeEnvironment);
        if (type instanceof ReferenceType) {
            ReferenceType referenceType = (ReferenceType) type;
            return referenceType.getInnerType();
        } else
            throw new MyException("the rH argument is not a Ref Type");
    }
}
