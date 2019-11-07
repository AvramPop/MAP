package avram.pop;

import avram.pop.controller.Controller;
import avram.pop.model.control.PrgState;
import avram.pop.model.expression.ArithExp;
import avram.pop.model.expression.ValueExp;
import avram.pop.model.expression.VarExp;
import avram.pop.model.statement.*;
import avram.pop.model.type.BoolType;
import avram.pop.model.type.IntType;
import avram.pop.model.value.BoolValue;
import avram.pop.model.value.IntValue;
import avram.pop.model.value.Value;
import avram.pop.repository.MyIRepository;
import avram.pop.repository.MyRepository;
import avram.pop.utils.*;

public class Main {

    public static void main(String[] args){
        MyIDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyIList<Value> out = new MyList<>();
        MyIStack<IStmt> executionStack = new MyStack<>();
        IStmt program1 = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        IStmt program2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new
                                ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new
                                        ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        IStmt program3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        PrgState programState = new PrgState(executionStack, symbolTable, out, program3);
        MyIRepository repository = new MyRepository();
        repository.addState(programState);
        Controller controller = new Controller(repository);
        try{
            controller.allStep();
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
    }
}
