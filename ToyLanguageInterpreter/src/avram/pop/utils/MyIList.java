package avram.pop.utils;

import java.util.ArrayList;
import java.util.List;

public interface MyIList<T> {
    void add(int index, T elem);
    void add(T elem);
    void remove(int index);
    T get(int index);

}
