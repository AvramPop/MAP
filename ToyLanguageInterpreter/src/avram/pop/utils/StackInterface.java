package avram.pop.utils;

public interface StackInterface<T> {
    T pop() throws MyException;
    void push(T v);

    boolean isEmpty();

    String toLogString();
}
