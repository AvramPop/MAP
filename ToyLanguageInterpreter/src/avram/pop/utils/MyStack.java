package avram.pop.utils;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private Stack<T> stack;

    public MyStack(){
        stack = new Stack<>();
    }

    @Override
    public T pop() throws MyException{
        if(!stack.isEmpty()){
            return stack.pop();
        } else {
            throw new MyException("stack is empty");
        }
    }

    @Override
    public void push(T v){
        stack.push(v);
    }

    @Override
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    @Override
    public String toLogString(){
        StringBuilder sb = new StringBuilder();
        for(T entry : stack){
            sb.append(entry.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        stack.forEach(e -> sb.append(e.toString()).append('\n'));
        return sb.toString();
    }
}
