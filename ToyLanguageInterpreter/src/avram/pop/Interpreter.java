package avram.pop;

import avram.pop.controller.Controller;
import avram.pop.model.command.ExitCommand;
import avram.pop.model.command.RunExample;
import avram.pop.model.control.ProgramState;
import avram.pop.model.expression.*;
import avram.pop.model.statement.*;
import avram.pop.model.type.BoolType;
import avram.pop.model.type.IntType;
import avram.pop.model.type.ReferenceType;
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
        Statement program1 = new CompoundStatement(
                new VariableDeclareStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        DictionaryInterface<String, Value> symbolTable1 = new MyDictionary<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable1 = new MyDictionary<>();
        HeapInterface<Integer, Value> heap1 = new Heap<>();
        ListInterface<Value> out1 = new MyList<>();
        StackInterface<Statement> executionStack1 = new MyStack<>();
        ProgramState programState1 = new ProgramState(executionStack1, symbolTable1, out1, fileTable1, program1, heap1);
        Repository repository1 = null;
        try{
            repository1 = new ListRepository("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/log1.log");
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
        repository1.addState(programState1);
        Controller controller1 = new Controller(repository1);

        Statement program2 = new CompoundStatement(new VariableDeclareStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclareStatement("b", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+', new ValueExpression(new IntValue(2)), new
                                ArithmeticExpression('*', new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression('+', new VariableExpression("a"), new
                                        ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        DictionaryInterface<String, Value> symbolTable2 = new MyDictionary<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable2 = new MyDictionary<>();
        ListInterface<Value> out2 = new MyList<>();
        HeapInterface<Integer, Value> heap2 = new Heap<>();
        StackInterface<Statement> executionStack2 = new MyStack<>();
        ProgramState programState2 = new ProgramState(executionStack2, symbolTable2, out2, fileTable2, program2, heap2);
        Repository repository2 = null;
        try{
            repository2 = new ListRepository("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/log2.log");
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
        repository2.addState(programState2);
        Controller controller2 = new Controller(repository2);

        Statement program3 = new CompoundStatement(new VariableDeclareStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclareStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new
                                        IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));
        DictionaryInterface<String, Value> symbolTable3 = new MyDictionary<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable3 = new MyDictionary<>();
        ListInterface<Value> out3 = new MyList<>();
        HeapInterface<Integer, Value> heap3 = new Heap<>();
        StackInterface<Statement> executionStack3 = new MyStack<>();
        ProgramState programState3 = new ProgramState(executionStack3, symbolTable3, out3, fileTable3, program3, heap3);
        Repository repository3 = null;
        try{
            repository3 = new ListRepository("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/log3.log");
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
        repository3.addState(programState3);
        Controller controller3 = new Controller(repository3);

        Statement program4 = new CompoundStatement(new VariableDeclareStatement("varf", new StringType()),
                new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/test.in"))),
                new CompoundStatement(new OpenReadFileStatement(new VariableExpression("varf")),
                new CompoundStatement(new VariableDeclareStatement("varc", new IntType()),
                new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                new CompoundStatement(new PrintStatement(new VariableExpression("varc")), new CloseReadFileStatement(new VariableExpression("varf"))))))))));
        DictionaryInterface<String, Value> symbolTable4 = new MyDictionary<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable4 = new MyDictionary<>();
        ListInterface<Value> out4 = new MyList<>();
        StackInterface<Statement> executionStack4 = new MyStack<>();
        HeapInterface<Integer, Value> heap4 = new Heap<>();
        ProgramState programState4 = new ProgramState(executionStack4, symbolTable4, out4, fileTable4, program4, heap4);
        Repository repository4 = null;
        try{
            repository4 = new ListRepository("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/log4.log");
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
        repository4.addState(programState4);
        Controller controller4 = new Controller(repository4);

        Statement program5 = new CompoundStatement(new VariableDeclareStatement("a", new IntType()),
                new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new IntValue(5))),
                        new WhileStatement(new RelationalExpression(new VariableExpression("a"), new ValueExpression(new IntValue(0)), ">"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("a")),
                                        new AssignmentStatement("a",
                                                new ArithmeticExpression('-', new VariableExpression("a"), new ValueExpression(new IntValue(1))))))));
        DictionaryInterface<String, Value> symbolTable5 = new MyDictionary<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable5 = new MyDictionary<>();
        ListInterface<Value> out5 = new MyList<>();
        StackInterface<Statement> executionStack5 = new MyStack<>();
        HeapInterface<Integer, Value> heap5 = new Heap<>();
        ProgramState programState5 = new ProgramState(executionStack5, symbolTable5, out5, fileTable5, program5, heap5);
        Repository repository5 = null;
        try{
            repository5 = new ListRepository("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/log5.log");
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
        repository5.addState(programState5);
        Controller controller5 = new Controller(repository5);

        Statement program6 = new CompoundStatement(new VariableDeclareStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclareStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a")))))));
        DictionaryInterface<String, Value> symbolTable6 = new MyDictionary<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable6 = new MyDictionary<>();
        ListInterface<Value> out6 = new MyList<>();
        StackInterface<Statement> executionStack6 = new MyStack<>();
        HeapInterface<Integer, Value> heap6 = new Heap<>();
        ProgramState programState6 = new ProgramState(executionStack6, symbolTable6, out6, fileTable6, program6, heap6);
        Repository repository6 = null;
        try{
            repository6 = new ListRepository("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/log6.log");
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
        repository6.addState(programState6);
        Controller controller6 = new Controller(repository6);

        Statement program7 = new CompoundStatement(new VariableDeclareStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclareStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression('+',
                                                        new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5)))))))));
        DictionaryInterface<String, Value> symbolTable7 = new MyDictionary<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable7 = new MyDictionary<>();
        ListInterface<Value> out7 = new MyList<>();
        StackInterface<Statement> executionStack7 = new MyStack<>();
        HeapInterface<Integer, Value> heap7 = new Heap<>();
        ProgramState programState7 = new ProgramState(executionStack7, symbolTable7, out7, fileTable7, program7, heap7);
        Repository repository7 = null;
        try{
            repository7 = new ListRepository("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/log7.log");
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
        repository7.addState(programState7);
        Controller controller7 = new Controller(repository7);

        Statement program8 = new CompoundStatement(new VariableDeclareStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                new CompoundStatement(new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression('+',
                                                new HeapReadingExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5))))))));
        DictionaryInterface<String, Value> symbolTable8 = new MyDictionary<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable8 = new MyDictionary<>();
        ListInterface<Value> out8 = new MyList<>();
        StackInterface<Statement> executionStack8 = new MyStack<>();
        HeapInterface<Integer, Value> heap8 = new Heap<>();
        ProgramState programState8 = new ProgramState(executionStack8, symbolTable8, out8, fileTable8, program8, heap8);
        Repository repository8 = null;
        try{
            repository8 = new ListRepository("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/log8.log");
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
        repository8.addState(programState8);
        Controller controller8 = new Controller(repository8);

        Statement program9 = new CompoundStatement(new VariableDeclareStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclareStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("a")),
                                        new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a")))))))));
        DictionaryInterface<String, Value> symbolTable9 = new MyDictionary<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable9 = new MyDictionary<>();
        ListInterface<Value> out9 = new MyList<>();
        StackInterface<Statement> executionStack9 = new MyStack<>();
        HeapInterface<Integer, Value> heap9 = new Heap<>();
        ProgramState programState9 = new ProgramState(executionStack9, symbolTable9, out9, fileTable9, program9, heap9);
        Repository repository9 = null;
        try{
            repository9 = new ListRepository("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/log9.log");
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
        repository9.addState(programState9);
        Controller controller9 = new Controller(repository9);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", "program1", controller1));
        menu.addCommand(new RunExample("2", "program2", controller2));
        menu.addCommand(new RunExample("3", "program3", controller3));
        menu.addCommand(new RunExample("4", "program4", controller4));
        menu.addCommand(new RunExample("5", "program5", controller5));
        menu.addCommand(new RunExample("6", "program6", controller6));
        menu.addCommand(new RunExample("7", "program7", controller7));
        menu.addCommand(new RunExample("8", "program8", controller8));
        menu.addCommand(new RunExample("9", "program9", controller9));
        menu.show();
    }
}
