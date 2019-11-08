package avram.pop;

import avram.pop.controller.Controller;
import avram.pop.model.command.ExitCommand;
import avram.pop.model.command.RunExample;
import avram.pop.model.control.ProgramState;
import avram.pop.model.expression.ArithmeticExpression;
import avram.pop.model.expression.ValueExpression;
import avram.pop.model.expression.VariableExpression;
import avram.pop.model.statement.*;
import avram.pop.model.type.BoolType;
import avram.pop.model.type.IntType;
import avram.pop.model.type.StringType;
import avram.pop.model.value.BoolValue;
import avram.pop.model.value.IntValue;
import avram.pop.model.value.Value;
import avram.pop.model.value.StringValue;
import avram.pop.repository.Repository;
import avram.pop.repository.ListRepository;
import avram.pop.utils.*;
import avram.pop.view.TextMenu;

import java.io.BufferedReader;

public class Interpreter {

    public static void main(String[] args){
        Statement program1 = new CompareStatement(
                new VariableDeclareStatement("v", new IntType()),
                new CompareStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        DictionaryInterface<String, Value> symbolTable1 = new MyDictionary<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable1 = new MyDictionary<>();
        ListInterface<Value> out1 = new MyList<>();
        StackInterface<Statement> executionStack1 = new MyStack<>();
        ProgramState programState1 = new ProgramState(executionStack1, symbolTable1, out1, fileTable1, program1);
        Repository repository1 = null;
        try{
            repository1 = new ListRepository("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/log1.log");
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
        repository1.addState(programState1);
        Controller controller1 = new Controller(repository1);

        Statement program2 = new CompareStatement(new VariableDeclareStatement("a", new IntType()),
                new CompareStatement(new VariableDeclareStatement("b", new IntType()),
                        new CompareStatement(new AssignmentStatement("a", new ArithmeticExpression('+', new ValueExpression(new IntValue(2)), new
                                ArithmeticExpression('*', new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompareStatement(new AssignmentStatement("b", new ArithmeticExpression('+', new VariableExpression("a"), new
                                        ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        DictionaryInterface<String, Value> symbolTable2 = new MyDictionary<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable2 = new MyDictionary<>();
        ListInterface<Value> out2 = new MyList<>();
        StackInterface<Statement> executionStack2 = new MyStack<>();
        ProgramState programState2 = new ProgramState(executionStack2, symbolTable2, out2, fileTable2, program2);
        Repository repository2 = null;
        try{
            repository2 = new ListRepository("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/log2.log");
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
        repository2.addState(programState2);
        Controller controller2 = new Controller(repository2);

        Statement program3 = new CompareStatement(new VariableDeclareStatement("a", new BoolType()),
                new CompareStatement(new VariableDeclareStatement("v", new IntType()),
                        new CompareStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompareStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new
                                        IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));
        DictionaryInterface<String, Value> symbolTable3 = new MyDictionary<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable3 = new MyDictionary<>();
        ListInterface<Value> out3 = new MyList<>();
        StackInterface<Statement> executionStack3 = new MyStack<>();
        ProgramState programState3 = new ProgramState(executionStack3, symbolTable3, out3, fileTable3, program3);
        Repository repository3 = null;
        try{
            repository3 = new ListRepository("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/log3.log");
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
        repository3.addState(programState3);
        Controller controller3 = new Controller(repository3);

        Statement program4 = new CompareStatement(new VariableDeclareStatement("varf", new StringType()),
                new CompareStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/test.in"))),
                new CompareStatement(new OpenReadFileStatement(new VariableExpression("varf")),
                new CompareStatement(new VariableDeclareStatement("varc", new IntType()),
                new CompareStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                new CompareStatement(new PrintStatement(new VariableExpression("varc")),
                new CompareStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                new CompareStatement(new PrintStatement(new VariableExpression("varc")), new CloseReadFileStatement(new VariableExpression("varf"))))))))));
        DictionaryInterface<String, Value> symbolTable4 = new MyDictionary<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable4 = new MyDictionary<>();
        ListInterface<Value> out4 = new MyList<>();
        StackInterface<Statement> executionStack4 = new MyStack<>();
        ProgramState programState4 = new ProgramState(executionStack4, symbolTable4, out4, fileTable4, program4);
        Repository repository4 = null;
        try{
            repository4 = new ListRepository("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/log4.log");
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
