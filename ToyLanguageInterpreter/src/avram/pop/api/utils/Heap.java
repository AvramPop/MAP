package avram.pop.api.utils;

import avram.pop.api.model.value.Value;

import java.util.HashMap;
import java.util.Map;

public class Heap<K, V> implements HeapInterface<K, V>{
    private Map<Integer, Value> heap;
    private int nextFreeLocation;
    private int lastAllocated;
    public Heap(){
        nextFreeLocation = 1;
        lastAllocated = 1;
        heap = new HashMap<>();
    }

    @Override
    public String toLogString(){
        StringBuilder logString = new StringBuilder();
        for(Map.Entry<Integer, Value> entry : heap.entrySet()){
            logString.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return logString.toString();
    }

    @Override
    public void add(Value value){
        heap.put(nextFreeLocation, value);
        lastAllocated = nextFreeLocation;
        nextFreeLocation++;
    }

    @Override
    public Value get(Integer location) throws MyException{
        if(location == 0){
            throw new MyException("null address");
        } else {
            return heap.get(location);
        }
    }

    @Override
    public boolean isDefined(Integer location){
        return heap.containsKey(location);
    }

    @Override
    public int getLastAllocatedLocation(){
        return lastAllocated;
    }

    @Override
    public void update(Integer id, Value val){
        heap.put(id, val);
    }

    @Override
    public void setContent(Map<Integer, Value> heap){
        this.heap = heap;
    }

    @Override
    public Map<Integer, Value> getContent(){
        return heap;
    }
}
