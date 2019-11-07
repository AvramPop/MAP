package avram.pop.repository;

import avram.pop.model.control.PrgState;

public interface MyIRepository {
    PrgState getCrtPrg();
    void addState(PrgState state);
}
