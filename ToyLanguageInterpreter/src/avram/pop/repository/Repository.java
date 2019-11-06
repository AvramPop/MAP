package avram.pop.repository;

import avram.pop.model.control.PrgState;

import java.util.List;

public class Repository implements IRepository {
    List<PrgState> states;

    @Override
    public PrgState getCrtPrg(){
        return null;
    }
}
