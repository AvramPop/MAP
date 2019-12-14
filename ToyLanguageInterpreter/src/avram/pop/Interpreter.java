package avram.pop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Interpreter extends Application {

    public static void main(String[] args){ launch(args); }


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProgramSelection.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 300);
        scene.getStylesheets().add(getClass().getResource("ui/css/ProgramSelection.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
