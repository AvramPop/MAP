package avram.pop.repository;

import avram.pop.model.Vehicle;

public class VehicleRepository implements VehicleBuffer {
    private Vehicle[] buffer;
    private int size;

    @Override
    public int getSize(){
        return size;
    }

    public VehicleRepository(){
        this.buffer = new Vehicle[100];
        size = 0;
    }

    @Override
    public void addVehicle(Vehicle newVehicle){
        buffer[size++] = newVehicle;
    }

    @Override
    public void removeVehicle(Vehicle vehicleToRemove) throws VehicleNotFoundException{
        int i;
        boolean found = false;
        for(i = 0; i < size && !found; i++){
            if(buffer[i].equals(vehicleToRemove)){
                found = true;
            }
        }
        if(found){
            for(int j = i; j < size - 1; j++){
                buffer[j] = buffer[j + 1];
            }
            size--;
        } else {
            throw new VehicleNotFoundException();
        }
    }

    @Override
    public Vehicle[] getBuffer(){
        return buffer;
    }

    public boolean containsVehicle(Vehicle vehicle){
        for(int i = 0; i < size; i++){
            if(vehicle.equals(buffer[i])){
                return true;
            }
        }
        return false;
    }
}
