package avram.pop.utils;

import avram.pop.model.value.Value;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {
    private Map<K, V> map;

    public MyDictionary(){
        map = new HashMap<K, V>();
    }

    @Override
    public boolean isDefined(K id){
        return map.containsKey(id);
    }

    @Override
    public V lookup(K id){
        return map.get(id);
    }

    @Override
    public void update(K id, V val){
        map.put(id, val);
    }
}
