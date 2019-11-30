package avram.pop.model.statement;

import avram.pop.model.control.ProgramState;
import avram.pop.model.value.Value;
import avram.pop.utils.DictionaryInterface;
import avram.pop.utils.MyDictionary;
import avram.pop.utils.MyStack;

import java.util.Map;

public class ForkStatement implements Statement{
    private Statement statement;

    public ForkStatement(Statement statement){
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state){
        return new ProgramState(new MyStack<>(), clone(state.getSymbolTable()), state.getOutputBuffer(), state.getFileTable(), statement, state.getHeap());
    }

    private DictionaryInterface<String, Value> clone(DictionaryInterface<String, Value> dictionaryToClone){
        DictionaryInterface<String, Value> copy = new MyDictionary<>();
        for(Map.Entry<String, Value> entry : dictionaryToClone.getContent().entrySet()){
            copy.update(entry.getKey(), entry.getValue().copy());
        }
        return copy;
    }
}
