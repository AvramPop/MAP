package avram.pop.model;

public class Motorcycle extends Vehicle {
    public Motorcycle(String color){
        super(color);
    }

    @Override
    public String getColor(){
        return super.getColor();
    }

    @Override
    public String toString(){
        return "Motorcycle{" +
                "color='" + color + '\'' +
                '}';
    }
}
