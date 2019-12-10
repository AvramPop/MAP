package avram.pop.model.expression;

import avram.pop.model.type.BoolType;
import avram.pop.model.type.IntType;
import avram.pop.model.type.Type;
import avram.pop.model.value.BoolValue;
import avram.pop.model.value.IntValue;
import avram.pop.model.value.Value;
import avram.pop.utils.DictionaryInterface;
import avram.pop.utils.HeapInterface;
import avram.pop.utils.MyException;

public class RelationalExpression implements Expression {
    private Expression firstOperand;
    private Expression secondOperand;
    private int operationCode; // 1 for < 2 for <= 3 for == 4 for != 5 for > 6 for >=

    public RelationalExpression(Expression firstOperand, Expression secondOperand, String operationSymbol){
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        if(operationSymbol.equals("<")){
            this.operationCode = 1;
        } else if(operationSymbol.equals("<=")){
            this.operationCode = 2;
        } else if(operationSymbol.equals("==")){
            this.operationCode = 3;
        } else if(operationSymbol.equals("!=")){
            this.operationCode = 4;
        } else if(operationSymbol.equals(">")){
            this.operationCode = 5;
        } else if(operationSymbol.equals(">=")){
            this.operationCode = 6;
        }
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> symbolTable, HeapInterface<Integer, Value> heap) throws MyException{
        Value v1, v2;
        v1 = firstOperand.evaluate(symbolTable, heap);
        if(v1.getType().equals(new IntType())){
            v2 = secondOperand.evaluate(symbolTable, heap);
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int int1 = i1.getValue();
                int int2 = i2.getValue();
                if(operationCode == 1){
                    return new BoolValue(int1 < int2);
                } else if(operationCode == 2){
                    return new BoolValue(int1 <= int2);
                } else if(operationCode == 3){
                    return new BoolValue(int1 == int2);
                } else if(operationCode == 4){
                    return new BoolValue(int1 != int2);
                } else if(operationCode == 5){
                    return new BoolValue(int1 > int2);
                } else if(operationCode == 6){
                    return new BoolValue(int1 >= int2);
                }
            } else throw new MyException("Operand2 is not int");
        } else throw new MyException("Operand1 is not int");
        return null;
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnvironment) throws MyException{
        Type type1, type2;
        type1 = firstOperand.typecheck(typeEnvironment);
        type2 = secondOperand.typecheck(typeEnvironment);
        if(type1.equals(new IntType())){
            if(type2.equals(new IntType())){
                return new BoolType();
            } else throw new MyException("second operand is not an integer");
        } else throw new MyException("first operand is not an integer");
    }

    @Override
    public String toString(){
        return "RelationalExpression{" +
                "e1=" + firstOperand +
                ", e2=" + secondOperand +
                ", op=" + operationCode +
                '}';
    }
}
