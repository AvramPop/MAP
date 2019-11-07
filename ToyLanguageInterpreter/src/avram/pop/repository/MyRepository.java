package avram.pop.repository;

import avram.pop.model.control.PrgState;

import java.util.ArrayList;
import java.util.List;

public class MyRepository implements MyIRepository {
    List<PrgState> states;

    public MyRepository(){
        this.states = new ArrayList<>();
    }

    public void addState(PrgState state){
        states.add(state);
    }

    @Override
    public PrgState getCrtPrg(){
        return states.get(0);
    }
}
