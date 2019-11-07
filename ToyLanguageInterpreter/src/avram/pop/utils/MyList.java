package avram.pop.utils;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    private List<T> list;

    public MyList(){
        list = new ArrayList<>();
    }

    @Override
    public void add(int index, T elem) throws MyException{
        if(index < list.size()){
            list.add(index, elem);
        } else {
            throw new MyException("index not in list");
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("List: [");
        list.forEach(e -> sb.append(e.toString()).append(", "));
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void add(T elem){
        list.add(elem);
    }

    @Override
    public void remove(int index) throws MyException{
        if(index < list.size()){
            list.remove(index);
        } else {
            throw new MyException("index not in list");
        }
    }

    @Override
    public T get(int index) throws MyException{
        if(index < list.size()){
            return list.get(index);
        } else {
            throw new MyException("index not in list");
        }
    }
}
