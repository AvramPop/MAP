package avram.pop.utils;

public interface MyIStack<T> {
    T pop() throws MyException;
    void push(T v);

    boolean isEmpty();
}
