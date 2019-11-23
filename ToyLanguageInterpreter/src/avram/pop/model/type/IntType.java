package avram.pop.model.type;

import avram.pop.model.value.IntValue;
import avram.pop.model.value.Value;

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
}
