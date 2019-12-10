package avram.pop.api.utils;

public interface StackInterface<T> {
    T pop() throws MyException;
    void push(T v);

    boolean isEmpty();

    String toLogString();
}
