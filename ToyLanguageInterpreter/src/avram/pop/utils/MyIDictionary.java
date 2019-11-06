package avram.pop.utils;

public interface MyIDictionary<K, V> {
    boolean isDefined(K id);

    V lookup(K id);

    void update(K id, V val);
}
