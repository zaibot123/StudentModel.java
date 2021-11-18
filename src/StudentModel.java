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
    public void makeStatement() throws SQLException{

        Stment=conn.createStatement();
        }
    public ArrayList<String> SQLStudentQuery() throws SQLException{
        ArrayList<String> student = new ArrayList<>();
        String select="Select StudentName from Student;";
        result=Stment.executeQuery(select);
        while(result!=null  && result.next()){
            String names = result.getString(1);
            student.add(names);
        }
        return student;
    }


    public ArrayList<StudentInfo> RetrieveGradeForStudent(String StudentName) throws SQLException {
        ArrayList<StudentInfo> averagegrade=new ArrayList<>();
        String sql = "SELECT avg(CourseRegistration.Grade) as Average, Student.StudentName FROM Student INNER JOIN CourseRegistration ON CourseRegistration.StudentID=Student.StudentID WHERE StudentName = ?;";
        Preparedstment = conn.prepareStatement(sql);
        Preparedstment.setString(1, StudentName);
        result = Preparedstment.executeQuery();
        while (result != null && result.next()) {
            double GradeAverage = result.getDouble("Average");
            System.out.println(GradeAverage);
            StudentInfo a =new StudentInfo(StudentName, "",GradeAverage);
            averagegrade.add(a);
        }
        return averagegrade;
    }

    public ArrayList<StudentInfo> QueryforGrades(String StudentName) throws SQLException{
            ArrayList<StudentInfo> studentinfolist=new ArrayList<>();
            String sql="SELECT Student.StudentID, Student.StudentName as name, CourseRegistration.Grade, CourseRegistration.CourseID, Course.Semester  FROM Student INNER JOIN CourseRegistration ON CourseRegistration.StudentID=Student.StudentID INNER JOIN Course ON Course.CourseID=CourseRegistration.CourseID WHERE StudentName= ? ;";
            Preparedstment=conn.prepareStatement(sql);
            Preparedstment.setString(1,StudentName);
            result =Preparedstment.executeQuery();

            while(result!=null && result.next()){

                String CoursesTaken=result.getString("Semester");
                double GradeCourse = result.getDouble("Grade");
                StudentInfo s =new StudentInfo(StudentName, CoursesTaken, GradeCourse);
                studentinfolist.add(s);
            }
            return studentinfolist;
        }




    }

class StudentInfo{
        String StudentName;
        String CoursesTaken;
        double GradeCourse;
        double GradeAverage;


    StudentInfo(String name, String courses,double grade){               //constructor

        StudentName=name;
        CoursesTaken=courses;
        GradeCourse=grade;


    }

}

