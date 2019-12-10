package avram.pop.api.utils;

import avram.pop.api.model.value.Value;

import java.util.Map;

public interface HeapInterface<K, V>{
    String toLogString();
    void add(Value value);
    Value get(Integer location) throws MyException;
    boolean isDefined(Integer location);
    int getLastAllocatedLocation();
    void update(Integer id, Value val);

    void setContent(Map<Integer, Value> heap);
    Map<Integer, Value> getContent();
}
