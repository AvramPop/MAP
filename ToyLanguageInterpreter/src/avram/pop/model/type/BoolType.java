package avram.pop.model.type;

import avram.pop.model.value.BoolValue;
import avram.pop.model.value.Value;

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
