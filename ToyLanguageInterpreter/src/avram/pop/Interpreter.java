package avram.pop;

import avram.pop.api.controller.Controller;
import avram.pop.api.model.command.ExitCommand;
import avram.pop.api.model.command.RunExample;
import avram.pop.api.model.control.ProgramState;
import avram.pop.api.model.expression.*;
import avram.pop.api.model.statement.*;
import avram.pop.api.model.type.BoolType;
import avram.pop.api.model.type.IntType;
import avram.pop.api.model.type.ReferenceType;
import avram.pop.api.model.type.StringType;
import avram.pop.api.model.value.BoolValue;
import avram.pop.api.model.value.IntValue;
import avram.pop.api.model.value.StringValue;
import avram.pop.api.model.value.Value;
import avram.pop.api.repository.ListRepository;
import avram.pop.api.repository.Repository;
import avram.pop.api.utils.*;
import avram.pop.api.view.TextMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;

public class Interpreter extends Application {

    public static void main(String[] args){ launch(args); }

    private static Controller createProgram(String programName, Statement code){
        try{
            code.typecheck(new MyDictionary<>());
        } catch(MyException e){
            System.err.println(programName + " doesn't pass type checking");
            System.err.println(e.toString());
        }
        DictionaryInterface<String, Value> symbolTable = new MyDictionary<>();
        DictionaryInterface<StringValue, BufferedReader> fileTable = new MyDictionary<>();
        HeapInterface<Integer, Value> heap = new Heap<>();
        ListInterface<Value> out = new MyList<>();
        StackInterface<Statement> executionStack = new MyStack<>();
        ProgramState programState = new ProgramState(executionStack, symbolTable, out, fileTable, code, heap);
        Repository repository = null;
        try{
            repository = new ListRepository("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/" + programName + ".log");
        } catch(MyException e){
            System.err.println(e.getMessage());
        }
        if(repository != null){
            repository.addState(programState);
            return new Controller(repository);
        }
        return null;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ui/fxml/ProgramSelection.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 300, 275);
        scene.getStylesheets().add(getClass().getResource("ui/css/ProgramSelection.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

        Statement program1 = new CompoundStatement(
                new VariableDeclareStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));


        Statement program2 = new CompoundStatement(new VariableDeclareStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclareStatement("b", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+', new ValueExpression(new IntValue(2)), new
                                ArithmeticExpression('*', new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression('+', new VariableExpression("a"), new
                                        ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));


        Statement program3 = new CompoundStatement(new VariableDeclareStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclareStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new
                                        IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));

        Statement program4 = new CompoundStatement(new VariableDeclareStatement("varf", new StringType()),
                new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("/home/dani/Desktop/code/faculta/an2/sem1/map/ToyLanguageInterpreter/logs/test.in"))),
                        new CompoundStatement(new OpenReadFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(new VariableDeclareStatement("varc", new IntType()),
                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")), new CloseReadFileStatement(new VariableExpression("varf"))))))))));


        Statement program5 = new CompoundStatement(new VariableDeclareStatement("a", new IntType()),
                new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new IntValue(5))),
                        new WhileStatement(new RelationalExpression(new VariableExpression("a"), new ValueExpression(new IntValue(0)), ">"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("a")),
                                        new AssignmentStatement("a",
                                                new ArithmeticExpression('-', new VariableExpression("a"), new ValueExpression(new IntValue(1))))))));


        Statement program6 = new CompoundStatement(new VariableDeclareStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclareStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a")))))));


        Statement program7 = new CompoundStatement(new VariableDeclareStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclareStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression('+',
                                                        new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5)))))))));


        Statement program8 = new CompoundStatement(new VariableDeclareStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                new CompoundStatement(new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression('+',
                                                new HeapReadingExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5))))))));


        Statement program9 = new CompoundStatement(new VariableDeclareStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclareStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a")))))))));


        Statement program10 = new CompoundStatement(new VariableDeclareStatement("v", new IntType()),
                new CompoundStatement(new VariableDeclareStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new NewStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(
                                                new CompoundStatement(new WriteHeapStatement("a", new ValueExpression(new IntValue(30))),
                                                        new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                                                new CompoundStatement(
                                                                        new PrintStatement(new VariableExpression("v")),
                                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))))))));

        Statement test = new CompoundStatement(
                new VariableDeclareStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", "program1", createProgram("program1", program1)));
        menu.addCommand(new RunExample("2", "program2", createProgram("program2", program2)));
        menu.addCommand(new RunExample("3", "program3", createProgram("program3", program3)));
        menu.addCommand(new RunExample("4", "program4", createProgram("program4", program4)));
        menu.addCommand(new RunExample("5", "program5", createProgram("program5", program5)));
        menu.addCommand(new RunExample("6", "program6", createProgram("program6", program6)));
        menu.addCommand(new RunExample("7", "program7", createProgram("program7", program7)));
        menu.addCommand(new RunExample("8", "program8", createProgram("program8", program8)));
        menu.addCommand(new RunExample("9", "program9", createProgram("program9", program9)));
        menu.addCommand(new RunExample("10", "program10", createProgram("program10", program10)));
        menu.addCommand(new RunExample("11", "test", createProgram("test", test)));

//        try{
//            menu.show();
//        } catch(InterruptedException e){
//            e.printStackTrace();
//        }
    }
}
