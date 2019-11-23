package avram.pop.model.type;

import avram.pop.model.value.StringValue;
import avram.pop.model.value.Value;

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
}
