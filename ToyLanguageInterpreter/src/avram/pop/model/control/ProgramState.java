package avram.pop.model.control;

import avram.pop.model.statement.Statement;
import avram.pop.model.value.StringValue;
import avram.pop.model.value.Value;
import avram.pop.utils.DictionaryInterface;
import avram.pop.utils.ListInterface;
import avram.pop.utils.StackInterface;

import java.io.BufferedReader;

public class ProgramState {
    private StackInterface<Statement> exeStack;
    private DictionaryInterface<String, Value> symTable;
    private DictionaryInterface<StringValue, BufferedReader> fileTable;
    private ListInterface<Value> out;
    private Statement originalProgram; //optional field, but good to have

    public DictionaryInterface<StringValue, BufferedReader> getFileTable(){
        return fileTable;
    }

    public void setFileTable(DictionaryInterface<StringValue, BufferedReader> fileTable){
        this.fileTable = fileTable;
    }

    public ProgramState(StackInterface<Statement> stk, DictionaryInterface<String, Value> symtbl, ListInterface<Value> ot, DictionaryInterface<StringValue, BufferedReader> fileTable, Statement prg){
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        this.fileTable = fileTable;
        originalProgram = deepCopy(prg);//recreate the entire original prg
        stk.push(prg);
    }

    private Statement deepCopy(Statement prg){
        return prg;
    }

    public StackInterface<Statement> getStk(){
        return exeStack;
    }

    public DictionaryInterface<String, Value> getSymTable(){
        return symTable;
    }

    public ListInterface<Value> getOut(){
        return out;
    }

    public void setExeStack(StackInterface<Statement> exeStack){
        this.exeStack = exeStack;
    }

    public void setSymTable(DictionaryInterface<String, Value> symTable){
        this.symTable = symTable;
    }

    public void setOut(ListInterface<Value> out){
        this.out = out;
    }

    @Override
    public String toString(){
        return "PrgState{" +
                "\nexeStack\n" + exeStack.toString() +
                "symTable\n" + symTable.toString() +
                "fileTable\n" + fileTable.toString() +
                "out\n" + out.toString() +
                "\n}\n";
    }

    public String toLogString(){
        return "ExeStack:\n" +
                exeStack.toLogString() +
                "SymTable:\n" +
                symTable.toLogString() +
                "Out:\n" +
                out.toLogString() +
                "FileTable:\n" +
                fileTable.toLogString() + "\n";
    }
}
