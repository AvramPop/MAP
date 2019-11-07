package avram.pop.utils;

public interface MyIList<T> {
    void add(int index, T elem) throws MyException;
    void add(T elem);
    void remove(int index) throws MyException;
    T get(int index) throws MyException;

}
