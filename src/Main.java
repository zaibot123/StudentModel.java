import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;

public class Main extends Application {

    public void start(Stage primaryStage) {
        StudentView view = new StudentView();
        StudentModel model = new StudentModel("jdbc:sqlite:C:\\Users\\Bruger\\IdeaProjects\\Portfolie3\\identifier.sqlite");
        try{
            StudentController controller = new StudentController(view, model);
        }
        catch(SQLException e ){
            System.out.println(e.getMessage());
        }

        primaryStage.setTitle("Student Overview");
        primaryStage.setScene(new Scene(view.asParent(), 600, 472));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }

}
