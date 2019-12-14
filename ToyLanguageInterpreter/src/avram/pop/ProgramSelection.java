package avram.pop;

import avram.pop.api.controller.Controller;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProgramSelection {
    @FXML
    public ListView<String> programListView;

    private List<Statement> programList;
    public void show(MouseEvent mouseEvent){
        System.out.println("hello");
    }

    public ProgramSelection(){
        programList = new ArrayList<>();
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

        programList.add(program1);
        programList.add(program2);
        programList.add(program3);
        programList.add(program4);
        programList.add(program5);
        programList.add(program6);
        programList.add(program7);
        programList.add(program8);
        programList.add(program9);
        programList.add(program10);
        programList.add(test);
    }

    @FXML
    private void initialize() {
        programListView.getItems().addAll(programList.stream().map(Statement::toString).collect(Collectors.toList()));
    }


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
            return new Controller(repository, programName);
        }
        return null;
    }

    public void runSelectedProgram() throws IOException{
        int selectedIndex = programListView.getSelectionModel().getSelectedIndex();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProgramRun.fxml"));
        loader.setControllerFactory(c -> new ProgramRun(createProgram("Program " + selectedIndex, programList.get(selectedIndex))));
        Parent programWindowRoot = loader.load();
        Scene scene = new Scene(programWindowRoot, 400, 400);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
