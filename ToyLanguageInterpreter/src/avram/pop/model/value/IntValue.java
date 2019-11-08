package avram.pop.model.value;

import avram.pop.model.type.IntType;
import avram.pop.model.type.IType;

import java.util.Objects;

public class IntValue implements IValue {
    private int val;

    public IntValue(int v){
        val = v;
    }

    public int getVal(){
        return val;
    }

    public String toString(){
        return "(int) value is: " + val;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        IntValue intValue = (IntValue) o;
        return val == intValue.val;
    }

    @Override
    public int hashCode(){
        return Objects.hash(val);
    }

    public IType getType(){
        return new IntType();
    }
}
