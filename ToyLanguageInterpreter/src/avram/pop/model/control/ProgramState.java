package avram.pop.model.control;

import avram.pop.model.statement.Statement;
import avram.pop.model.value.StringValue;
import avram.pop.model.value.Value;
import avram.pop.utils.DictionaryInterface;
import avram.pop.utils.HeapInterface;
import avram.pop.utils.ListInterface;
import avram.pop.utils.StackInterface;

import java.io.BufferedReader;

public class ProgramState {
    private StackInterface<Statement> executionStack;
    private DictionaryInterface<String, Value> symbolTable;
    private DictionaryInterface<StringValue, BufferedReader> fileTable;
    private HeapInterface<Integer, Value> heap;
    private ListInterface<Value> outputBuffer;
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
        originalProgram = deepCopy(program);//recreate the entire original prg
        executionStack.push(program);
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

    @Override
    public String toString(){
        return "PrgState{" +
                "\nexeStack\n" + executionStack.toString() +
                "symTable\n" + symbolTable.toString() +
                "fileTable\n" + fileTable.toString() +
                "out\n" + outputBuffer.toString() +
                "heap\n" + heap.toString() +
                "\n}\n";
    }

    public String toLogString(){
        return "ExeStack:\n" +
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
