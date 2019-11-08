package avram.pop.model.expression;

import avram.pop.utils.MyException;
import avram.pop.utils.DictionaryInterface;
import avram.pop.model.value.Value;

public class VariableExpression implements Expression {
    private String id;

    @Override
    public String toString(){
        return "VarExp{" +
                "id='" + id + '\'' +
                '}';
    }

    public VariableExpression(String v){
        id = v;
    }

    public Value eval(DictionaryInterface<String, Value> tbl) throws MyException{
        if(tbl.isDefined(id)) return tbl.lookup(id);
        else throw new MyException("variable " + id + " is not defined");
    }
}
