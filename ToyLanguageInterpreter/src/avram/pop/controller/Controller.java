package avram.pop.controller;

import avram.pop.model.control.ProgramState;
import avram.pop.model.value.ReferenceValue;
import avram.pop.model.value.Value;
import avram.pop.repository.Repository;
import avram.pop.utils.MyException;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private Repository repository;
    private ExecutorService executor;
    public Controller(Repository repository){
        this.repository = repository;
    }

    public void allStep() throws MyException, InterruptedException{
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<ProgramState> programList = removeCompletedProgram(repository.getProgramList());
        while(programList.size() > 0){
            garbageCollector(programList);
            oneStepForAllPrograms(programList);
            //remove the completed programs
            programList = removeCompletedProgram(repository.getProgramList());
        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        //setPrgList of repository in order to change the repository
        // update the repository state
        repository.setProgramList(programList);
    }

    private Map<Integer, Value> garbageCollector(List<ProgramState> programList){
        ProgramState arbitraryState = programList.get(0);
        return arbitraryState
                .getHeap()
                .getContent()
                .entrySet().stream()
                .filter(e -> (getAddresses(
                        programList.stream()
                                .map(programState -> programState.getSymbolTable().getContent().values())
                                .reduce(new ArrayList<>(), (s1, s2) -> {List<Value> temp = new ArrayList<>(s1); temp.addAll(s2); return temp;}),
                        arbitraryState.getHeap().getContent().values())
                        .contains(e.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddresses(Collection<Value> symbolTableValues, Collection<Value> heapValues){
        return Stream.concat(
                symbolTableValues.stream()
                        .filter(v-> v instanceof ReferenceValue)
                        .map(v-> {
                            ReferenceValue referenceValue = (ReferenceValue) v;
                            return referenceValue.getAddress();
                        }),
                heapValues.stream()
                        .filter(v-> v instanceof ReferenceValue)
                        .map(v-> {
                            ReferenceValue referenceValue = (ReferenceValue) v;
                            return referenceValue.getAddress();
                        })).collect(Collectors.toList());
    }

    private void oneStepForAllPrograms(List<ProgramState> programStateList) throws InterruptedException{
       // repository.logMessage("before one step:\n");
//        programStateList.forEach(programState -> {
//            try{
//                repository.logProgramState(programState);
//            } catch(MyException e){
//                e.printStackTrace();
//            }
//        });

        List<Callable<ProgramState>> callList =
                programStateList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(() -> p.oneStep()))
                .collect(Collectors.toList());

        List<ProgramState> newProgramList = executor.invokeAll(callList)
                .stream()
                .map(future -> {
                    try{
                        return future.get();
                    } catch(InterruptedException | ExecutionException e){
                         return null;
                    }
                })
                .filter(p -> p != null)
                .collect(Collectors.toList());

        programStateList.addAll(newProgramList);

        //repository.logMessage("after one step:\n");
        programStateList.forEach(program -> {
            try{
                repository.logProgramState(program);
            } catch(MyException e){
                e.printStackTrace();
            }
        });
        repository.setProgramList(programStateList);
    }

    private List<ProgramState> removeCompletedProgram(List<ProgramState> programList){
        return programList.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }
}
