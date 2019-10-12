package avram.pop.repository;

import avram.pop.model.Vehicle;

public interface VehicleBuffer {
    void addVehicle(Vehicle newVehicle);
    void removeVehicle(Vehicle vehicleToRemove) throws VehicleNotFoundException;
    Vehicle[] getBuffer();
    int getSize();
    boolean containsVehicle(Vehicle vehicle);

}
