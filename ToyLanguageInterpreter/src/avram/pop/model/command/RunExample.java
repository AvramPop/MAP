package avram.pop.model.command;

import avram.pop.controller.Controller;
import avram.pop.utils.MyException;

public class RunExample extends Command {
    private Controller ctr;

    public RunExample(String key, String desc, Controller ctr){
        super(key, desc);
        this.ctr = ctr;
    }

    @Override
    public void execute(){
        try{
            ctr.allStep();
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
    }
}
