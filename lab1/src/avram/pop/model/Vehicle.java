package avram.pop.model;

import java.util.Objects;

public abstract class Vehicle{
    protected String color;

    public Vehicle(String color){
        this.color = color;
    }

    public String getColor(){
        return color;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(color, vehicle.color);
    }

    @Override
    public int hashCode(){
        return Objects.hash(color);
    }
}
