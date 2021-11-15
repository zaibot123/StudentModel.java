import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentModel {

    Connection conn=null;
    String url = null;
    Statement Stment=null;
    PreparedStatement Preparedstment=null;
    ResultSet result=null;

    public StudentModel(String url){
        this.url=url;                       //constructor
    }
    public void establishConnection() throws SQLException{
        conn= DriverManager.getConnection(url);
    }
    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
    public void makeStatement(Statement Statement) throws SQLException{
        Statement=Stment;
        Statement=conn.createStatement();
        }
    public ArrayList<String> SQLStudentQuery() throws SQLException{
        ArrayList<String> student = new ArrayList<>();
        String select="Select Name from Student;";
        result=Stment.executeQuery(select);
        while(result!=null  && result.next()){
            String names = result.getString(1);
            student.add(names);
        }
        return student;
    }
    public void RetrieveGradeForStudent() throws SQLException{
        System.out.println("Which student do you wish to find average grade for?");
        Scanner scanner = new Scanner(System.in);
        String studentName = scanner.nextLine();
        String sql= "SELECT Grades, CourseID FROM CourseRegistration;";
        result=Stment.executeQuery(sql);
        while(result!=null && result.next()){
            int grades=result.getInt(1);
            int courses=result.getInt(2);
            System.out.print(grades+courses);
        }
}
    public void PstmRetrieveGradeForStudent() throws SQLException {
        System.out.println("Which student do you wish to find average grade and courses for?");
        Scanner scanner = new Scanner(System.in);
        String studentName = scanner.nextLine();
        String sql = "SELECT StudentName,CourseName,Grade FROM CourseRegistration WHERE StudentName = ?;";
        Preparedstment = conn.prepareStatement(sql);
        Preparedstment.setString(1, studentName);
        result = Preparedstment.executeQuery();
        while (result != null && result.next()) {
            System.out.println(result.getString(1));
        }
    }

    public ArrayList<StudentInfo> QueryforGrades(String studentName) throws SQLException{
            ArrayList<StudentInfo> studentinfolist=new ArrayList<>();

            String sql="SELECT StudentName as name, AVGGrades as grades, CoursesTaken as courses WHERE StudentName= ? ;";

            Preparedstment=conn.prepareStatement(sql);
            Preparedstment.setString(1,studentName);
            result =Preparedstment.executeQuery();

            while(result!=null && result.next()){
                String StudentName=result.getString("name");
                String CoursesTaken=result.getString("courses");
                double AVGGrade = result.getDouble("grade");

                System.out.println(StudentName+ "har deltaget i " + CoursesTaken + " og har en karakter på " + AVGGrade);
                StudentInfo s =new StudentInfo(StudentName, CoursesTaken, AVGGrade);
                studentinfolist.add(s);
            }
            return studentinfolist;
        }
}
class StudentInfo{
        String StudentName;
        String CoursesTaken;
        double AVGGrade;

    StudentInfo(String name, String courses,double grade){               //constructor

        StudentName=name;
        CoursesTaken=courses;
        AVGGrade=grade;
    }
}

