import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import org.w3c.dom.Text;

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
        view.nameslist = FXCollections.observableArrayList(model.SQLStudentQuery());
        view.FindGradesCourses.setOnAction(e -> HandlerPrintGrades(view.StudentChoice.getValue(), view.TextField));
        view.FindAVG.setOnAction(e -> HandlerPrintFindAVG(view.StudentChoice.getValue(), view.TextField));


        view.configure();

    }

    public void HandlerPrintGrades(String StudentName, TextArea txtArea) {
        txtArea.clear();
        txtArea.appendText("Grades for student " + StudentName + " in courses: \n");
        try {
            ArrayList<StudentInfo> grades = model.QueryforGrades(StudentName);  //method
            for (int i = 0; i < grades.size(); i++) {
                txtArea.appendText(grades.get(i).CoursesTaken + ": " + grades.get(i).GradeCourse + "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void HandlerPrintFindAVG(String StudentName, TextArea txtArea) {
        txtArea.clear();
        txtArea.appendText("Average grade for " + StudentName +" : \n");
        try {
            ArrayList<StudentInfo> grades = model.RetrieveGradeForStudent(StudentName);  //method
            for (int i = 0; i < grades.size(); i++) {
                txtArea.appendText(""+ grades.get(i).GradeCourse + "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}


