package avram.pop;

import avram.pop.api.controller.Controller;
import avram.pop.api.model.value.StringValue;
import avram.pop.api.utils.MyException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import java.util.stream.Collectors;

public class ProgramRun {
    @FXML
    public Label programNameLabel;
    @FXML
    public Label doneLabel;
    @FXML
    public Label numberOfProgramStatesLabel;
    @FXML
    public TableView heapTable;
    @FXML
    public ListView<String> outListView;
    @FXML
    public ListView<String> filetableListView;

    @FXML
    public ListView executionStackListView;
    @FXML
    public Button oneStepButton;
    @FXML
    public TableView symbolTableTable;
    @FXML
    public ListView<String> programStateListView;
    private Controller controller;

    public ProgramRun(Controller controller){
        this.controller = controller;
        controller.prepareForExecution();
    }

    @FXML
    public void initialize(){
        programNameLabel.setText(controller.name);
        numberOfProgramStatesLabel.setText("Program states: 1");
        doneLabel.setText("Execution done!");
        doneLabel.setVisible(false);
        programStateListView.getItems().addAll(controller.getRepository().getProgramList().stream().map(ps -> String.valueOf(ps.getId())).collect(Collectors.toList()));
        //programStateListView.getItems().addAll(controller.getRepository().getProgramList().stream().map(ProgramState::toLogString).collect(Collectors.toList()));
    }

    public void runOneStep(){
        if(!controller.done()){
            try{
                controller.oneStep();
            } catch(MyException | InterruptedException e){
                e.printStackTrace();
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
        refreshOutListView();
        refreshFiletableListView();
        refreshProgramStateListView();
        refreshExecutionStackListView();
    }

    private void refreshExecutionStackListView(){

    }

    private void refreshProgramStateListView(){
        programStateListView.getItems().clear();
      //  programStateListView.getItems().addAll(controller.getRepository().getProgramList().stream().map(ProgramState::toLogString).collect(Collectors.toList()));
        programStateListView.getItems().addAll(controller.getRepository().getProgramList().stream().map(ps -> String.valueOf(ps.getId())).collect(Collectors.toList()));
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

    }

    private void refreshHeapTable(){

    }

    private void refreshNumberOfProgramStatesLabel(){
        numberOfProgramStatesLabel.setText("Program states: " + controller.getNumberOfPrograms());
    }
}
