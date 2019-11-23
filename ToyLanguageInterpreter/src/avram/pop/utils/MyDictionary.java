package avram.pop.utils;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements DictionaryInterface<K, V> {
    private Map<K, V> map;

    public MyDictionary(){
        map = new HashMap<>();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        map.forEach((k, v) -> sb.append(k.toString())
                .append(" ")
                .append(v.toString())
                .append('\n'));
        return sb.toString();
    }

    @Override
    public boolean isDefined(K id){
        return map.containsKey(id);
    }

    @Override
    public V lookup(K id) throws MyException{
        if(isDefined(id)){
            return map.get(id);
        } else {
            throw new MyException(id + " not a key in dictionary");
        }
    }

    @Override
    public void update(K id, V val){
        map.put(id, val);
    }

    @Override
    public String toLogString(){
        StringBuilder logString = new StringBuilder();
        for(Map.Entry<K, V> entry : map.entrySet()){
            logString.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return logString.toString();
    }

    @Override
    public void removeEntry(K id){
        map.remove(id);
    }

    @Override
    public Map<K, V> getContent(){
        return map;
    }
}
