package avram.pop.model.value;

import avram.pop.model.type.BoolType;
import avram.pop.model.type.Type;

import java.util.Objects;

public class BoolValue implements Value {
    private boolean val;

    public BoolValue(boolean v){
        val = v;
    }

    public boolean getVal(){
        return val;
    }

    public String toString(){
        return "(bool) value is: " + val;
    }

    public Type getType(){
        return new BoolType();
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        BoolValue boolValue = (BoolValue) o;
        return val == boolValue.val;
    }

    @Override
    public int hashCode(){
        return Objects.hash(val);
    }
}
