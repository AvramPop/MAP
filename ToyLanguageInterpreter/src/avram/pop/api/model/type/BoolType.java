package avram.pop.api.model.type;

import avram.pop.api.model.value.BoolValue;
import avram.pop.api.model.value.Value;

public class BoolType implements Type {
    public boolean equals(Object another){
        return another instanceof BoolType;
    }

    public String toString(){
        return "bool";
    }

    @Override
    public Value defaultValue(){
        return new BoolValue(false);
    }

    @Override
    public Type copy(){
        return new BoolType();
    }
}
