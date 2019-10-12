package avram.pop.model;

public class Bicycle extends Vehicle {
    public Bicycle(String color){
        super(color);
    }

    @Override
    public String toString(){
        return "Bicycle{" +
                "color='" + color + '\'' +
                '}';
    }

    @Override
    public String getColor(){
        return super.getColor();
    }
}
