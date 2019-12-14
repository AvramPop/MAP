package avram.pop;

import avram.pop.api.controller.Controller;
import avram.pop.api.model.value.StringValue;
import avram.pop.api.model.value.Value;
import avram.pop.api.utils.MyException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class ProgramRun {
    @FXML
    public Label programNameLabel;
    @FXML
    public Label doneLabel;
    @FXML
    public Label numberOfProgramStatesLabel;
    @FXML
    public TableView<Map.Entry<Integer, Value>> heapTable;
    @FXML
    public ListView<String> outListView;
    @FXML
    public ListView<String> filetableListView;

    @FXML
    public ListView executionStackListView;
    @FXML
    public Button oneStepButton;
    @FXML
    public TableView<Map.Entry<String, Value>> symbolTable;
    @FXML
    public ListView<String> programStateListView;
    @FXML
    public TableColumn<Map.Entry<String, Value>, String> symbolTableNameColumn;
    @FXML
    public TableColumn<Map.Entry<String, Value>, String> symbolTableValueColumn;
    @FXML
    public TableColumn<Map.Entry<Integer, Value>, String> heapAddressColumn;
    @FXML
    public TableColumn<Map.Entry<Integer, Value>, String> heapValueColumn;
    private Controller controller;
    private int lastSelectedProgram;

    public ProgramRun(Controller controller){
        this.controller = controller;
        controller.prepareForExecution();
        lastSelectedProgram = 0;
    }

    @FXML
    public void initialize(){
        programNameLabel.setText(controller.name);
        numberOfProgramStatesLabel.setText("Program states: 1");
        doneLabel.setText("Execution done!");
        doneLabel.setVisible(false);
        heapAddressColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getKey().toString()));
        heapValueColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getValue().toString()));
        symbolTableNameColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getKey()));
        symbolTableValueColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getValue().toString()));
        programStateListView.getItems().addAll(controller.getRepository().getProgramList().stream().map(ps -> String.valueOf(ps.getId())).collect(Collectors.toList()));
        //programStateListView.getItems().addAll(controller.getRepository().getProgramList().stream().map(ProgramState::toLogString).collect(Collectors.toList()));
    }

    public void runOneStep(){
        if(!controller.done()){
            try{
                controller.oneStep();
            } catch(MyException | InterruptedException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
            refreshUI();
        } else {
            controller.endExecution();
            oneStepButton.setVisible(false);
            doneLabel.setVisible(true);
        }
    }

    private void refreshUI(){
        refreshNumberOfProgramStatesLabel();
        refreshHeapTable();
        refreshSymbolTable();
        refreshOutListView();
        refreshFiletableListView();
        refreshProgramStateListView();
        refreshExecutionStackListView();
        if(lastSelectedProgram >= controller.getNumberOfPrograms()){
            lastSelectedProgram = 0;
        }
    }

    private void refreshSymbolTable(){
        symbolTable.refresh();
        if(lastSelectedProgram < controller.getNumberOfPrograms() && lastSelectedProgram >= 0){
            symbolTable.setItems(FXCollections.observableArrayList(new ArrayList<>(controller.getRepository().getProgramList().get(lastSelectedProgram).getSymbolTable().getContent().entrySet())));
        }
    }

    private void refreshExecutionStackListView(){
        executionStackListView.getItems().clear();
        if(lastSelectedProgram < controller.getNumberOfPrograms() && lastSelectedProgram >= 0){
            executionStackListView.getItems().addAll(controller.getRepository().getProgramList().get(lastSelectedProgram).getExecutionStack().toList());
        }
    }

    private void refreshProgramStateListView(){
        programStateListView.getItems().clear();
      //  programStateListView.getItems().addAll(controller.getRepository().getProgramList().stream().map(ProgramState::toLogString).collect(Collectors.toList()));
        programStateListView.getItems().addAll(controller.getRepository().getProgramList().stream().map(programState -> String.valueOf(programState.getId())).collect(Collectors.toList()));
    }

    private void refreshFiletableListView(){
        filetableListView.getItems().clear();
        filetableListView.getItems().addAll(
                controller.getRepository().getProgramList().get(0)
                .getFileTable()
                .getContent().keySet()
                .stream()
                .map(StringValue::getValue)
                .collect(Collectors.toList()));
    }

    private void refreshOutListView(){
        outListView.getItems().clear();
        outListView.getItems().addAll(controller.getRepository().getProgramList().get(0).getOutputBuffer().toList().stream().map(Object::toString).collect(Collectors.toList()));
    }

    private void refreshHeapTable(){
        heapTable.refresh();
        if(lastSelectedProgram < controller.getNumberOfPrograms() && lastSelectedProgram >= 0){
            heapTable.setItems(FXCollections.observableArrayList(new ArrayList<>(controller.getRepository().getProgramList().get(0).getHeap().getContent().entrySet())));
        }
    }

    private void refreshNumberOfProgramStatesLabel(){
        numberOfProgramStatesLabel.setText("Program states: " + controller.getNumberOfPrograms());
    }

    public void setLastSelectedProgram(MouseEvent mouseEvent){
        lastSelectedProgram = programStateListView.getSelectionModel().getSelectedIndex();
        if(lastSelectedProgram >= controller.getNumberOfPrograms()){
            lastSelectedProgram = 0;
        }
        refreshExecutionStackListView();
        refreshSymbolTable();
    }
}
