package avram.pop.model.value;

import avram.pop.model.type.StringType;
import avram.pop.model.type.Type;

import java.util.Objects;

public class StringValue implements Value {
    private String val;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        StringValue that = (StringValue) o;
        return val.equals(that.val);
    }

    @Override
    public int hashCode(){
        return Objects.hash(val);
    }

    public StringValue(String v){
        val = v;
    }

    public String getVal(){
        return val;
    }

    public String toString(){
        return "(string) value is: " + val;
    }

    public Type getType(){
        return new StringType();
    }
}
