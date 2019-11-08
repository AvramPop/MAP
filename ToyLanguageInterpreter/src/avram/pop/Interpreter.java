package avram.pop;

import avram.pop.controller.Controller;
import avram.pop.model.command.ExitCommand;
import avram.pop.model.command.RunExample;
import avram.pop.model.control.ProgramState;
import avram.pop.model.expression.ArithExp;
import avram.pop.model.expression.ValueExp;
import avram.pop.model.expression.VarExp;
import avram.pop.model.statement.*;
import avram.pop.model.type.BoolType;
import avram.pop.model.type.IntType;
import avram.pop.model.type.StringType;
import avram.pop.model.value.BoolValue;
import avram.pop.model.value.IntValue;
import avram.pop.model.value.IValue;
import avram.pop.model.value.StringValue;
import avram.pop.repository.MyIRepository;
import avram.pop.repository.MyRepository;
import avram.pop.utils.*;
import avram.pop.view.TextMenu;

import java.io.BufferedReader;

public class Interpreter {

    public static void main(String[] args){
        IStmt program1 = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        MyIDictionary<String, IValue> symbolTable1 = new MyDictionary<>();
        MyIDictionary<StringValue, BufferedReader> fileTable1 = new MyDictionary<>();
        MyIList<IValue> out1 = new MyList<>();
        MyIStack<IStmt> executionStack1 = new MyStack<>();
        ProgramState programState1 = new ProgramState(executionStack1, symbolTable1, out1, fileTable1, program1);
        MyIRepository repository1 = null;
        try{
            repository1 = new MyRepository("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/log1.log");
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
        repository1.addState(programState1);
        Controller controller1 = new Controller(repository1);

        IStmt program2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new
                                ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new
                                        ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        MyIDictionary<String, IValue> symbolTable2 = new MyDictionary<>();
        MyIDictionary<StringValue, BufferedReader> fileTable2 = new MyDictionary<>();
        MyIList<IValue> out2 = new MyList<>();
        MyIStack<IStmt> executionStack2 = new MyStack<>();
        ProgramState programState2 = new ProgramState(executionStack2, symbolTable2, out2, fileTable2, program2);
        MyIRepository repository2 = null;
        try{
            repository2 = new MyRepository("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/log2.log");
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
        repository2.addState(programState2);
        Controller controller2 = new Controller(repository2);

        IStmt program3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        MyIDictionary<String, IValue> symbolTable3 = new MyDictionary<>();
        MyIDictionary<StringValue, BufferedReader> fileTable3 = new MyDictionary<>();
        MyIList<IValue> out3 = new MyList<>();
        MyIStack<IStmt> executionStack3 = new MyStack<>();
        ProgramState programState3 = new ProgramState(executionStack3, symbolTable3, out3, fileTable3, program3);
        MyIRepository repository3 = null;
        try{
            repository3 = new MyRepository("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/log3.log");
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
        repository3.addState(programState3);
        Controller controller3 = new Controller(repository3);

        IStmt program4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/test.in"))),
                new CompStmt(new OpenRFileStmt(new VarExp("varf")),
                new CompStmt(new VarDeclStmt("varc", new IntType()),
                new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                new CompStmt(new PrintStmt(new VarExp("varc")),
                new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFileStmt(new VarExp("varf"))))))))));
        MyIDictionary<String, IValue> symbolTable4 = new MyDictionary<>();
        MyIDictionary<StringValue, BufferedReader> fileTable4 = new MyDictionary<>();
        MyIList<IValue> out4 = new MyList<>();
        MyIStack<IStmt> executionStack4 = new MyStack<>();
        ProgramState programState4 = new ProgramState(executionStack4, symbolTable4, out4, fileTable4, program4);
        MyIRepository repository4 = null;
        try{
            repository4 = new MyRepository("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/log4.log");
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
        repository4.addState(programState4);
        Controller controller4 = new Controller(repository4);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", "program1", controller1));
        menu.addCommand(new RunExample("2", "program2", controller2));
        menu.addCommand(new RunExample("3", "program3", controller3));
        menu.addCommand(new RunExample("4", "program4", controller4));
        menu.show();
    }
}
