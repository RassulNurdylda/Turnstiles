import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class University {
    public static void main(String[] args) throws SQLException {

        Person worker1 = new Workers(101, "Johnny", "Depp", "Director");
        Person worker2 = new Workers(102, "Cameron", "Diaz", "Teacher");
        Person student1 = new Students(1001, "Angelina", "Jolie", 1);
        Person student2 = new Students(1002, "Megan", "Fox", 2);
        Person guest1 = new Guest(10001,"Tom","Hardy");
        Person guest2 = new Guest(10002,"Vin","Diesel");


        DBMSmanagement dbmSmanagement = new DBMSmanagement();


        dbmSmanagement.insertNewArrivalTime(worker1);
        dbmSmanagement.insertNewArrivalTime(worker2);
        dbmSmanagement.insertNewArrivalTime(student1);
        dbmSmanagement.insertNewArrivalTime(student2);
        dbmSmanagement.insertNewArrivalTime(guest1);
        dbmSmanagement.insertNewArrivalTime(guest2);
}
}
