package avram.pop.utils;

import avram.pop.model.type.Type;
import avram.pop.model.value.Value;

import java.util.Map;

public class CloneFactory {
    public static DictionaryInterface<String, Type> cloneTypeEnvironment(DictionaryInterface<String, Type> typeEnvironment){
        DictionaryInterface<String, Type> copy = new MyDictionary<>();
        for(Map.Entry<String, Type> entry : typeEnvironment.getContent().entrySet()){
            copy.update(entry.getKey(), entry.getValue().copy());
        }
        return copy;
    }

    public static DictionaryInterface<String, Value> cloneSymbolTable(DictionaryInterface<String, Value> symbolTable){
        DictionaryInterface<String, Value> copy = new MyDictionary<>();
        for(Map.Entry<String, Value> entry : symbolTable.getContent().entrySet()){
            copy.update(entry.getKey(), entry.getValue().copy());
        }
        return copy;
    }
}
