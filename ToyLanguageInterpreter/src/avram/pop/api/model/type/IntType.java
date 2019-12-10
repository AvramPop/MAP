package avram.pop.api.model.type;

import avram.pop.api.model.value.IntValue;
import avram.pop.api.model.value.Value;

public class IntType implements Type {
    public boolean equals(Object another){
        return another instanceof IntType;
    }

    public String toString(){
        return "int";
    }

    @Override
    public Value defaultValue(){
        return new IntValue(0);
    }

    @Override
    public Type copy(){
        return new IntType();
    }
}
