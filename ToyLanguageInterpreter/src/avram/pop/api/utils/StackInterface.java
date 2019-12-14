package avram.pop.api.utils;

import java.util.List;

public interface StackInterface<T> {
    T pop() throws MyException;
    void push(T v);

    boolean isEmpty();

    String toLogString();

    List<T> toList();
}
