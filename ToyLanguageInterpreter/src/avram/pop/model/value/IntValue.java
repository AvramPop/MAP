package avram.pop.model.value;

import avram.pop.model.type.IntType;
import avram.pop.model.type.Type;

import java.util.Objects;

public class IntValue implements Value {
    private int value;

    public IntValue(int v){
        value = v;
    }

    public int getValue(){
        return value;
    }

    public String toString(){
        return "(int) value is: " + value;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        IntValue intValue = (IntValue) o;
        return value == intValue.value;
    }

    @Override
    public int hashCode(){
        return Objects.hash(value);
    }

    public Type getType(){
        return new IntType();
    }

    @Override
    public Value copy(){
        return new IntValue(value);
    }
}
