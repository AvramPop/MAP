package avram.pop.controller;

import avram.pop.model.Bicycle;
import avram.pop.model.Car;
import avram.pop.model.Motorcycle;
import avram.pop.model.Vehicle;
import avram.pop.repository.VehicleBuffer;
import avram.pop.repository.VehicleNotFoundException;
import avram.pop.repository.VehicleRepository;

public class VehicleController {
    private VehicleBuffer repository;

    public VehicleController(VehicleBuffer repository){
        this.repository = repository;
    }

    public VehicleBuffer getVehiclesByColor(String color){
        VehicleBuffer filteredVehiclesRepository = new VehicleRepository();
        for(int i = 0; i < repository.getSize(); i++){
            if(repository.getBuffer()[i].getColor().equals(color)){
                filteredVehiclesRepository.addVehicle(repository.getBuffer()[i]);
            }
        }
        return filteredVehiclesRepository;
    }

    public Vehicle[] getAllVehicles(){
        return repository.getBuffer();
    }

    public void addVehicleOfType(int type, String color){
        Vehicle vehicle = null;
        if(type == 0){
            vehicle = new Bicycle(color);
        } else if(type == 1){
            vehicle = new Car(color);
        } else if(type == 2){
            vehicle = new Motorcycle(color);
        }
        repository.addVehicle(vehicle);
    }

    public void removeVehicle(int type, String color) throws VehicleNotFoundException{
        Vehicle vehicle = null;
        if(type == 0){
            vehicle = new Bicycle(color);
        } else if(type == 1){
            vehicle = new Car(color);
        } else if(type == 2){
            vehicle = new Motorcycle(color);
        }
        if(repository.containsVehicle(vehicle)){
            repository.removeVehicle(vehicle);
        } else {
            throw new VehicleNotFoundException();
        }
    }

    public int getNumberOfVehicles(){
        return repository.getSize();
    }
}
