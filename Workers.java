import java.sql.*;

public class Workers extends Person { //subclass workers

    private String position;

    public Workers(){
        super();
    }

    public Workers(int id, String fname, String lname,String position) {
        super(id, fname, lname);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


}
