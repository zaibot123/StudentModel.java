import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentController {
    StudentView view;
    StudentModel model;

   public StudentController(StudentView v, StudentModel m) throws SQLException {
       view = v;
       model = m;
       view.exitBtn.setOnAction(e -> Platform.exit());
       model.establishConnection();
       model.makeStatement();
       view.nameslist=FXCollections.observableArrayList(model.SQLStudentQuery());


       view.configure();

   }

    }
