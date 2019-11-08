package avram.pop.model.type;

import avram.pop.model.value.IntValue;
import avram.pop.model.value.IValue;

public class IntType implements IType {
    public boolean equals(Object another){
        return another instanceof IntType;
    }

    public String toString(){
        return "int";
    }

    @Override
    public IValue defaultValue(){
        return new IntValue(0);
    }
}
