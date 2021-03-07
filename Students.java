import java.sql.*;

public class Students extends Person { //subclass students

    private int course;


    public Students(int id, String fname, String lname,int course) {
        super(id, fname, lname);
        this.course = course;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

}
