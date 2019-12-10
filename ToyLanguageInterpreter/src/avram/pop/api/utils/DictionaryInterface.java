package avram.pop.api.utils;

import java.util.Map;

public interface DictionaryInterface<K, V> {
    boolean isDefined(K id);

    V lookup(K id) throws MyException;

    void update(K id, V val);

    String toLogString();

    void removeEntry(K id);

    Map<K, V> getContent();
}
