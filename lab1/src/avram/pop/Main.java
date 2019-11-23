package avram.pop;

import avram.pop.controller.VehicleController;
import avram.pop.model.Bicycle;
import avram.pop.model.Car;
import avram.pop.model.Motorcycle;
import avram.pop.model.Vehicle;
import avram.pop.repository.VehicleRepository;
import avram.pop.view.Console;

/*
* 1. Intr-o parcare exista masini, motociclete

si biciclete. Sa se afiseze toate vehiculele

de culoare rosie.*/
public class Main {

    public static void main(String[] args) {
        Vehicle v0 = new Bicycle("red");
        Vehicle v1 = new Bicycle("blue");
        Vehicle v2 = new Car("red");
        Vehicle v3 = new Car("red");
        Vehicle v4 = new Motorcycle("black");
        Vehicle v5 = new Motorcycle("red");
        VehicleRepository vehicleRepository = new VehicleRepository();
        vehicleRepository.addVehicle(v0);
        vehicleRepository.addVehicle(v1);
        vehicleRepository.addVehicle(v2);
        vehicleRepository.addVehicle(v3);
        vehicleRepository.addVehicle(v4);
        vehicleRepository.addVehicle(v5);
        VehicleController vehicleController = new VehicleController(vehicleRepository);
        Console console = new Console(vehicleController);
        console.run();
    }
}
