package avram.pop.api.model.command;

import avram.pop.api.controller.Controller;
import avram.pop.api.utils.MyException;

public class RunExample extends Command {
    private Controller controller;

    public RunExample(String key, String description, Controller controller){
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() throws InterruptedException{
        try{
            controller.allStep();
        } catch(MyException e){
            e.printStackTrace();
        }
    }
}
