package avram.pop.api.model.control;

import avram.pop.api.model.statement.Statement;
import avram.pop.api.model.value.StringValue;
import avram.pop.api.model.value.Value;
import avram.pop.api.utils.*;

import java.io.BufferedReader;

public class ProgramState {
    private StackInterface<Statement> executionStack;
    private DictionaryInterface<String, Value> symbolTable;
    private DictionaryInterface<StringValue, BufferedReader> fileTable;
    private HeapInterface<Integer, Value> heap;
    private ListInterface<Value> outputBuffer;
    private static int lastId;
    private int id;
    private Statement originalProgram; //optional field, but good to have

    public DictionaryInterface<StringValue, BufferedReader> getFileTable(){
        return fileTable;
    }


    public void setFileTable(DictionaryInterface<StringValue, BufferedReader> fileTable){
        this.fileTable = fileTable;
    }

    public ProgramState(StackInterface<Statement> executionStack, DictionaryInterface<String, Value> symbolTable, ListInterface<Value> outputBuffer, DictionaryInterface<StringValue, BufferedReader> fileTable, Statement program, HeapInterface<Integer, Value> heap){
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.outputBuffer = outputBuffer;
        this.heap = heap;
        this.fileTable = fileTable;
        id = getNewId();
        originalProgram = deepCopy(program);//recreate the entire original prg
        executionStack.push(program);
    }

    private synchronized int getNewId(){
        lastId++;
        return lastId;
    }

    public HeapInterface<Integer, Value> getHeap(){
        return heap;
    }

    public void setHeap(HeapInterface<Integer, Value> heap){
        this.heap = heap;
    }

    private Statement deepCopy(Statement program){
        return program;
    }

    public StackInterface<Statement> getExecutionStack(){
        return executionStack;
    }

    public DictionaryInterface<String, Value> getSymbolTable(){
        return symbolTable;
    }

    public ListInterface<Value> getOutputBuffer(){
        return outputBuffer;
    }

    public void setExecutionStack(StackInterface<Statement> executionStack){
        this.executionStack = executionStack;
    }

    public void setSymbolTable(DictionaryInterface<String, Value> symbolTable){
        this.symbolTable = symbolTable;
    }

    public void setOutputBuffer(ListInterface<Value> outputBuffer){
        this.outputBuffer = outputBuffer;
    }

    public boolean isNotCompleted(){
        return !executionStack.isEmpty();
    }

    public ProgramState oneStep() throws MyException{
        if(executionStack.isEmpty()) throw new MyException("Program execution stack is empty");
        Statement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    @Override
    public String toString(){
        return "PrgState{" +
                "\nid = " + id +
                "\nexeStack\n" + executionStack.toString() +
                "symTable\n" + symbolTable.toString() +
                "fileTable\n" + fileTable.toString() +
                "out\n" + outputBuffer.toString() +
                "heap\n" + heap.toString() +
                "\n}\n";
    }

    public String toLogString(){
        return "id = " + id + '\n' +
                "ExeStack:\n" +
                executionStack.toLogString() +
                "SymTable:\n" +
                symbolTable.toLogString() +
                "Out:\n" +
                outputBuffer.toLogString() +
                "FileTable:\n" +
                fileTable.toLogString() +
                "Heap:\n" +
                heap.toLogString() + "\n";
    }
}
