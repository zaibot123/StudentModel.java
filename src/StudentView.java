import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;



public class StudentView {

    GridPane startview;
    Label Student;
    Label Grade;
    Label Courses;
    Button exitBtn;
    Button FindAVG;
    Button FindCourses;
    ComboBox<String> StudentChoice;
    TextArea TextField;

    ObservableList<String> nameslist;


    public StudentView() {
        CreateView();
    }

    public Parent asParent() {
        return startview;
    }


    public void CreateView() {
        startview = new GridPane();
        startview.setMinSize(300, 200);
        startview.setPadding(new Insets(20, 20, 20, 20));
        startview.setVgap(5);
        startview.setHgap(1);

        Student = new Label("Select Student");
        startview.add(Student, 1, 1);


        exitBtn = new Button("Exit");
        startview.add(exitBtn, 20, 20);
        FindAVG = new Button("Find Average Grades");
        startview.add(FindAVG, 15, 9);
        FindCourses = new Button("Find Courses");
        startview.add(FindCourses, 20, 9);

        StudentChoice = new ComboBox<>();
        startview.add(StudentChoice,2,1);
    }
    public void configure(){
        StudentChoice.setItems(nameslist);
        StudentChoice.getSelectionModel().selectFirst();


    }
}
