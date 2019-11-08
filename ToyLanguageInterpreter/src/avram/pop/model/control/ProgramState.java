package avram.pop.model.control;

import avram.pop.model.statement.IStmt;
import avram.pop.model.value.StringValue;
import avram.pop.model.value.IValue;
import avram.pop.utils.MyIDictionary;
import avram.pop.utils.MyIList;
import avram.pop.utils.MyIStack;

import java.io.BufferedReader;

public class ProgramState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, IValue> symTable;
    private MyIDictionary<StringValue, BufferedReader> fileTable;
    private MyIList<IValue> out;
    private IStmt originalProgram; //optional field, but good to have

    public MyIDictionary<StringValue, BufferedReader> getFileTable(){
        return fileTable;
    }

    public void setFileTable(MyIDictionary<StringValue, BufferedReader> fileTable){
        this.fileTable = fileTable;
    }

    public ProgramState(MyIStack<IStmt> stk, MyIDictionary<String, IValue> symtbl, MyIList<IValue> ot, MyIDictionary<StringValue, BufferedReader> fileTable, IStmt prg){
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        this.fileTable = fileTable;
        originalProgram = deepCopy(prg);//recreate the entire original prg
        stk.push(prg);
    }

    private IStmt deepCopy(IStmt prg){
        return prg;
    }

    public MyIStack<IStmt> getStk(){
        return exeStack;
    }

    public MyIDictionary<String, IValue> getSymTable(){
        return symTable;
    }

    public MyIList<IValue> getOut(){
        return out;
    }

    public void setExeStack(MyIStack<IStmt> exeStack){
        this.exeStack = exeStack;
    }

    public void setSymTable(MyIDictionary<String, IValue> symTable){
        this.symTable = symTable;
    }

    public void setOut(MyIList<IValue> out){
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
