package avram.pop.model.value;

import avram.pop.model.type.StringType;
import avram.pop.model.type.Type;

import java.util.Objects;

public class StringValue implements Value {
    private String value;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        StringValue that = (StringValue) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode(){
        return Objects.hash(value);
    }

    public StringValue(String v){
        value = v;
    }

    public String getValue(){
        return value;
    }

    public String toString(){
        return "(string) value is: " + value;
    }

    public Type getType(){
        return new StringType();
    }

    @Override
    public Value copy(){
        return new StringValue(value);
    }
}
