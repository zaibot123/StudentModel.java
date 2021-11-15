import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;

public class Main extends Application {

    public void start(Stage primaryStage) {
        StudentView view = new StudentView();
        StudentModel model = new StudentModel("jdbc:sqlite:C:\\Users\\Bruger\\IdeaProjects\\FXtrainJDBC\\src\\TrainData.db");
        StudentController controller = null;
    }
}
