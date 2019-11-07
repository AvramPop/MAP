package avram.pop.view;

import avram.pop.controller.Controller;
import avram.pop.utils.MyException;

import java.util.Scanner;

public class View {
    private Controller controller;

    public View(Controller controller){
        this.controller = controller;
    }

    public void run(){
        while(true){
            System.out.println("1 - input");
            System.out.println("2 - run chosen");
            System.out.println("0 - exit");
            System.out.print(">");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            if(option == 0){
                System.out.println("bye!");
                break;
            } else if(option == 1){
                option = scanner.nextInt();
                System.out.println("loading program");
            } else if(option == 2){
                try{
                    controller.allStep();
                } catch(MyException e){
                    System.err.println(e.getMessage());
                }
            } else {
                System.err.println("bad input. try again!");
            }
        }
    }
}
