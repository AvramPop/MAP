package avram.pop.model.control;

import avram.pop.model.statement.IStmt;
import avram.pop.model.value.Value;
import avram.pop.utils.MyIDictionary;
import avram.pop.utils.MyIList;
import avram.pop.utils.MyIStack;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;
    private IStmt originalProgram; //optional field, but good to have

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, IStmt prg){
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        originalProgram = deepCopy(prg);//recreate the entire original prg
        stk.push(prg);
    }

    private IStmt deepCopy(IStmt prg){
        return prg;
    }

    public MyIStack<IStmt> getStk(){
        return exeStack;
    }

    public MyIDictionary<String, Value> getSymTable(){
        return symTable;
    }

    public MyIList<Value> getOut(){
        return out;
    }

    public void setExeStack(MyIStack<IStmt> exeStack){
        this.exeStack = exeStack;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable){
        this.symTable = symTable;
    }

    public void setOut(MyIList<Value> out){
        this.out = out;
    }

    @Override
    public String toString(){
        return "PrgState: {\n" +
                "exeStack = " + exeStack.toString() +
                ",\nsymTable = " + symTable.toString() +
                ",\nout = " + out.toString() +
                "\n}";
    }
}
