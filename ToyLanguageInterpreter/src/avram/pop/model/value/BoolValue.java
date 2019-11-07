package avram.pop.model.value;

import avram.pop.model.type.BoolType;
import avram.pop.model.type.Type;

public class BoolValue implements Value {
    private boolean val;

    public BoolValue(boolean v){
        val = v;
    }

    public boolean getVal(){
        return val;
    }

    public String toString(){
        return "bool value is: " + val;
    }

    public Type getType(){
        return new BoolType();
    }
}
