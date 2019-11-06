package avram.pop.utils;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    private List<T> list;

    public MyList(){
        list = new ArrayList<>();
    }

    @Override
    public void add(int index, T elem){
        list.add(index, elem);
    }

    @Override
    public void remove(int index){
        list.remove(index);
    }

    @Override
    public T get(int index){
        return list.get(index);
    }
}
