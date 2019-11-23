package avram.pop.model.command;

import avram.pop.controller.Controller;
import avram.pop.utils.MyException;

public class RunExample extends Command {
    private Controller controller;

    public RunExample(String key, String description, Controller controller){
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute(){
        try{
            controller.allStep();
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
    }
}
