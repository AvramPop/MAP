package avram.pop.api.model.type;

import avram.pop.api.model.value.ReferenceValue;
import avram.pop.api.model.value.Value;

public class ReferenceType implements Type{
    private Type innerType;
    public ReferenceType(Type innerType) {this.innerType = innerType;}
    public Type getInnerType() {return innerType;}
    public boolean equals(Object another){
        if (another instanceof ReferenceType)
            return innerType.equals(((ReferenceType) another).getInnerType());
        else
            return false;
    }
    public String toString() { return "Ref(" + innerType.toString() + ")";}
    public Value defaultValue() { return new ReferenceValue(0, innerType);}

    @Override
    public Type copy(){
        return new ReferenceType(innerType);
    }
}
