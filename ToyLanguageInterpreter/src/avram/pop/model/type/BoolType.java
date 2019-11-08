package avram.pop.model.type;

import avram.pop.model.value.BoolValue;
import avram.pop.model.value.IValue;

public class BoolType implements IType {
    public boolean equals(Object another){
        return another instanceof BoolType;
    }

    public String toString(){
        return "bool";
    }

    @Override
    public IValue defaultValue(){
        return new BoolValue(false);
    }
}
