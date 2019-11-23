package avram.pop.controller;

import avram.pop.model.control.ProgramState;
import avram.pop.model.statement.Statement;
import avram.pop.model.value.ReferenceValue;
import avram.pop.model.value.Value;
import avram.pop.repository.Repository;
import avram.pop.utils.MyException;
import avram.pop.utils.StackInterface;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    private Repository repository;

    public Controller(Repository repository){
        this.repository = repository;
    }

    private ProgramState oneStep(ProgramState state) throws MyException{
        StackInterface<Statement> stack = state.getExecutionStack();
        if(stack.isEmpty()) throw new MyException("program stack is empty");
        Statement currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    public void allStep() throws MyException{
        ProgramState programState = repository.getCurrentProgram();
        repository.logProgramState();
        while(!programState.getExecutionStack().isEmpty()){
            oneStep(programState);
            repository.logProgramState();
            programState.getHeap().setContent(unsafeGarbageCollector(
                    getAddressesFomSymbolTable(programState.getSymbolTable().getContent().values()),
                    programState.getHeap().getContent()));
        }
    }

    private Map<Integer, Value> unsafeGarbageCollector(List<Integer> symbolTableAddresses, Map<Integer, Value> heap){
        return heap.entrySet().stream()
                .filter(e -> (symbolTableAddresses.contains(e.getKey()) ||
                        heap.entrySet().stream().anyMatch(p -> {
                        if(p.getValue().getType() instanceof ReferenceValue){
                            ReferenceValue value = (ReferenceValue) p.getValue();
                            if(value.getAddress() == e.getKey()){
                                return true;
                            }
                        }
                        return false;
                })))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddressesFomSymbolTable(Collection<Value> symbolTableValues){
        return symbolTableValues.stream()
                .filter(v-> v instanceof ReferenceValue)
                .map(v-> {
                    ReferenceValue referenceValue = (ReferenceValue) v;
                    return referenceValue.getAddress();
                })
                .collect(Collectors.toList());
    }
}
