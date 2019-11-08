package avram.pop.model.type;

import avram.pop.model.value.StringValue;
import avram.pop.model.value.IValue;

public class StringType implements IType {
    public boolean equals(Object another){
        return another instanceof StringType;
    }

    public String toString(){
        return "string";
    }

    @Override
    public IValue defaultValue(){
        return new StringValue("");
    }
}
