package avram.pop.model.expression;

import avram.pop.model.type.IntType;
import avram.pop.model.value.IntValue;
import avram.pop.model.value.Value;
import avram.pop.utils.DictionaryInterface;
import avram.pop.utils.HeapInterface;
import avram.pop.utils.MyException;

public class ArithmeticExpression implements Expression {
    private Expression firstOperand;
    private Expression secondOperand;
    private int operationCode; //1-plus, 2-minus, 3-star, 4-divide

    public ArithmeticExpression(char operationSymbol, Expression firstOperand, Expression secondOperand){
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        if(operationSymbol == '+'){
            operationCode = 1;
        } else if(operationSymbol == '-'){
            operationCode = 2;
        } else if(operationSymbol == '*'){
            operationCode = 3;
        } else {
            operationCode = 4;
        }
    }

    @Override
    public String toString(){
        return "ArithExp{" +
                "e1=" + firstOperand +
                ", e2=" + secondOperand +
                ", op=" + operationCode +
                '}';
    }

    public Value evaluate(DictionaryInterface<String, Value> symbolTable, HeapInterface<Integer, Value> heap) throws MyException{
        Value v1, v2;
        v1 = firstOperand.evaluate(symbolTable, heap);
        if(v1.getType().equals(new IntType())){
            v2 = secondOperand.evaluate(symbolTable, heap);
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if(operationCode == 1) return new IntValue(n1 + n2);
                if(operationCode == 2) return new IntValue(n1 - n2);
                if(operationCode == 3) return new IntValue(n1 * n2);
                if(operationCode == 4){
                    if(n2 == 0) throw new MyException("division by zero");
                    else return new IntValue(n1 / n2);
                }
            } else{
                throw new MyException("second operand is not an integer");
            }
        } else{
            throw new MyException("first operand is not an integer");
        }
        return null;
    }
}
