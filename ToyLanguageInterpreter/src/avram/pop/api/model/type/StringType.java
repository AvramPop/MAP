package avram.pop.api.model.type;

import avram.pop.api.model.value.StringValue;
import avram.pop.api.model.value.Value;

public class StringType implements Type {
    public boolean equals(Object another){
        return another instanceof StringType;
    }

    public String toString(){
        return "string";
    }

    @Override
    public Value defaultValue(){
        return new StringValue("");
    }

    @Override
    public Type copy(){
        return new StringType();
    }
}
