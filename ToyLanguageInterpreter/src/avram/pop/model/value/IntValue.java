package avram.pop.model.value;

import avram.pop.model.type.IntType;
import avram.pop.model.type.Type;

public class IntValue implements Value {
    private int val;

    public IntValue(int v){
        val = v;
    }

    public int getVal(){
        return val;
    }

    public String toString(){
        return "int value is: " + val;
    }

    public Type getType(){
        return new IntType();
    }
}
