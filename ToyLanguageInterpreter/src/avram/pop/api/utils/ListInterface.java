package avram.pop.api.utils;

import java.util.List;

public interface ListInterface<T> {
    void add(int index, T elem) throws MyException;
    void add(T elem);
    void remove(int index) throws MyException;
    T get(int index) throws MyException;

    String toLogString();

    List<T> toList();
}
