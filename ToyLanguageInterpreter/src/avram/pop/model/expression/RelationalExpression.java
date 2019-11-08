package avram.pop.model.expression;

import avram.pop.model.type.IntType;
import avram.pop.model.value.BoolValue;
import avram.pop.model.value.Value;
import avram.pop.model.value.IntValue;
import avram.pop.utils.MyException;
import avram.pop.utils.DictionaryInterface;

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
    public Value evaluate(DictionaryInterface<String, Value> symbolTable) throws MyException{
        Value v1, v2;
        v1 = firstOperand.evaluate(symbolTable);
        if(v1.getType().equals(new IntType())){
            v2 = secondOperand.evaluate(symbolTable);
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
    public String toString(){
        return "RelationalExpression{" +
                "e1=" + firstOperand +
                ", e2=" + secondOperand +
                ", op=" + operationCode +
                '}';
    }
}
