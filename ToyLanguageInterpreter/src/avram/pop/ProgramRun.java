package avram.pop;

import avram.pop.api.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ProgramRun {
    @FXML
    public Label programNameLabel;
    @FXML
    public Label numberOfProgramStatesLabel;
    @FXML
    public TableView heapTable;
    @FXML
    public ListView outListView;
    @FXML
    public ListView filetableListView;
    @FXML
    public TableView programStateListView;
    @FXML
    public ListView executionStackListView;
    @FXML
    public Button oneStepButton;
    private Controller controller;

    public ProgramRun(Controller controller){
        this.controller = controller;
    }

    @FXML
    public void initialize(){
        programNameLabel.setText(controller.name);
    }

    public void runOneStep(MouseEvent mouseEvent){

    }
}
