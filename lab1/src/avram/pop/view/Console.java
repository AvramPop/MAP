package avram.pop.view;

import avram.pop.controller.VehicleController;
import avram.pop.model.Vehicle;
import avram.pop.repository.VehicleNotFoundException;

import java.util.Scanner;

public class Console {
    private VehicleController controller;
    public Console(VehicleController controller){
        this.controller = controller;
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        int choice;
        while(true){
            System.out.println("0 - exit");
            System.out.println("1 type color - add vehicle");
            System.out.println("2 type color - remove vehicle");
            System.out.println("3 color - get vehicles with color");
            System.out.println("4 - get all vehicles");
            System.out.println(">");
            choice = scanner.nextInt();
            if(choice == 0){
                System.out.println("bye");
                break;
            } else if(choice == 1){
                int type = scanner.nextInt();
                String color = scanner.next();
                controller.addVehicleOfType(type, color);
            } else if(choice == 2){
                int type = scanner.nextInt();
                String color = scanner.next();
                try{
                    controller.removeVehicle(type, color);
                } catch(VehicleNotFoundException e){
                    System.err.println("cannot remove vehicle. not found");
                }
            } else if(choice == 3){
                String color = scanner.next();
                for(int i = 0; i < controller.getVehiclesByColor(color).getSize(); i++){
                    System.out.println(controller.getVehiclesByColor(color).getBuffer()[i].toString());
                }
            } else if(choice == 4){
                for(int i = 0; i < controller.getNumberOfVehicles(); i++){
                    System.out.println(controller.getAllVehicles()[i].toString());
                }
            } else {
                System.out.println("bad input. try again!");
            }
        }
    }
}
