package avram.pop.model;

public class Car extends Vehicle{
    public Car(String color){
        super(color);
    }

    @Override
    public String toString(){
        return "Car{" +
                "color='" + color + '\'' +
                '}';
    }

    @Override
    public String getColor(){
        return super.getColor();
    }
}
