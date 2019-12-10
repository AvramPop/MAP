package avram.pop.model.expression;

import avram.pop.model.type.BoolType;
import avram.pop.model.type.Type;
import avram.pop.model.value.BoolValue;
import avram.pop.model.value.Value;
import avram.pop.utils.DictionaryInterface;
import avram.pop.utils.HeapInterface;
import avram.pop.utils.MyException;

public class LogicExpression implements Expression {
    private Expression firstOperand;
    private Expression secondOperand;
    private int operationCode; // 1 for and 2 for or

    public LogicExpression(Expression firstOperand, Expression secondOperand, String operationCode){
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        if(operationCode.equals("and")){
            this.operationCode = 1;
        } else if(operationCode.equals("or")){
            this.operationCode = 2;
        }
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> symbolTable, HeapInterface<Integer, Value> heap) throws MyException{
        Value v1, v2;
        v1 = firstOperand.evaluate(symbolTable, heap);
        if(v1.getType().equals(new BoolType())){
            v2 = secondOperand.evaluate(symbolTable, heap);
            if(v2.getType().equals(new BoolType())){
                BoolValue b1 = (BoolValue) v1;
                BoolValue b2 = (BoolValue) v2;
                boolean bool1 = b1.getValue();
                boolean bool2 = b2.getValue();
                return new BoolValue(operationCode == 1 ? bool1 && bool2 : bool1 || bool2);
            } else throw new MyException("Operand2 is not a boolean");
        } else throw new MyException("Operand1 is not a boolean");
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnvironment) throws MyException{
        Type type1, type2;
        type1 = firstOperand.typecheck(typeEnvironment);
        type2 = secondOperand.typecheck(typeEnvironment);
        if(type1.equals(new BoolType())){
            if(type2.equals(new BoolType())){
                return new BoolType();
            } else throw new MyException("second operand is not a bool");
        } else throw new MyException("first operand is not a bool");
    }

    @Override
    public String toString(){
        return "LogicExp{" +
                "e1=" + firstOperand +
                ", e2=" + secondOperand +
                ", op=" + operationCode +
                '}';
    }
}
