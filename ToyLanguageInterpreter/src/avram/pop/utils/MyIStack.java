package avram.pop.utils;

public interface MyIStack<T> {
    T pop();
    void push(T v);

    boolean isEmpty();
}
