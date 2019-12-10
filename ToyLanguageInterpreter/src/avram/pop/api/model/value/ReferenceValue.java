package avram.pop.api.model.value;

import avram.pop.api.model.type.ReferenceType;
import avram.pop.api.model.type.Type;

import java.util.Objects;

public class ReferenceValue implements Value{
    private int address;
    private Type locationType;

    public ReferenceValue(int address, Type locationType){
        this.address = address;
        this.locationType = locationType;
    }

    public String toString(){
        return "(ref) address is: " + address + " location type: " + locationType.toString();
    }


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ReferenceValue that = (ReferenceValue) o;
        return address == that.address &&
                Objects.equals(locationType, that.locationType);
    }

    @Override
    public int hashCode(){
        return Objects.hash(address, locationType);
    }
    public Type getLocationType() {return locationType;}
    public int getAddress() {return address;}
    public Type getType() { return new ReferenceType(locationType);}

    @Override
    public Value copy(){
        return new ReferenceValue(address, locationType);
    }
}
