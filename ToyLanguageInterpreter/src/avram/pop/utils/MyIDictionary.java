package avram.pop.utils;

public interface MyIDictionary<K, V> {
    boolean isDefined(K id);

    V lookup(K id) throws MyException;

    void update(K id, V val);

    String toLogString();

    void removeEntry(K id);
}
