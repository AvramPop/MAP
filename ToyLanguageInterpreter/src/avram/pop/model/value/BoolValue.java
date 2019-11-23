package avram.pop.model.value;

import avram.pop.model.type.BoolType;
import avram.pop.model.type.Type;

import java.util.Objects;

public class BoolValue implements Value {
    private boolean value;

    public BoolValue(boolean v){
        value = v;
    }

    public boolean getValue(){
        return value;
    }

    public String toString(){
        return "(bool) value is: " + value;
    }

    public Type getType(){
        return new BoolType();
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        BoolValue boolValue = (BoolValue) o;
        return value == boolValue.value;
    }

    @Override
    public int hashCode(){
        return Objects.hash(value);
    }
}
