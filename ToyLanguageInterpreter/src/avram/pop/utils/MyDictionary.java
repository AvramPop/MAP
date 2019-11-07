package avram.pop.utils;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {
    private Map<K, V> map;

    public MyDictionary(){
        map = new HashMap<>();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Dictionary: {");
        map.forEach((k, v) -> sb.append(k.toString())
                .append(": ")
                .append(v.toString())
                .append(", "));
        sb.append("}");
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
}
