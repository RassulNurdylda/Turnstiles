import java.io.InputStreamReader;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DBMSmanagement { //Main class associated with the database
    String url = "jdbc:mysql://localhost:3306/endka";
    String username = "root";
    String password = "";
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now;

    public Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            if (conn != null) {
                System.out.println("Connected to the Database");
            }
            return conn;
        } catch (SQLException ex) {
            System.out.println("Connection failed...");
            ex.printStackTrace();
        }
        return null;
    }

    public void insertNewArrivalTime(Person person) throws SQLException {
        Connection connection = connect();
        if (connection != null) {
            String sql = "SELECT * FROM person where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, person.getId());
            ResultSet result = preparedStatement.executeQuery();
            if (!result.isBeforeFirst()) {
                String sql2 = "INSERT INTO person (id,lname,fname,time) VALUES (?,?,?,?)";  //filling the table with prepareStatement
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);

                now = LocalDateTime.now();

                preparedStatement2.setInt(1, person.getId());
                preparedStatement2.setString(2, person.getLname());
                preparedStatement2.setString(3, person.getFname());
                preparedStatement2.setString(4, dtf.format(now));

                int row = preparedStatement2.executeUpdate();

                if (row > 0) {
                    System.out.println("Welcome");
                } else {
                    System.out.println("Error");
                }
            } else {

                updateArrivalTime(person);
            }
        }


    }

    public void updateArrivalTime(Person person) throws SQLException {
        Connection connection = connect();
        if (connection != null) {
            Scanner scanner = new Scanner(System.in);
            String sql = "";
            String status = getStatus(person);
            String aim = null;

            if (status.equals("arrive")){
                status = "leave";
                sql = "UPDATE person SET `time leave`=?, status=?, aim=?  WHERE id=?";
            }
            else if (status.equals("leave")){
                status = "arrive";
                if(person.getClass()==Students.class){
                    aim="Study";
                } else if (person.getClass()==Workers.class){
                    aim="Work";
                } else if(person.getClass()==Guest.class){
                    aim=scanner.nextLine();
                }
                sql = "UPDATE person SET time=?, status=?, `time leave`='0', aim=? WHERE id=?";
            }
            else {
                System.out.println("Something is wrong with connection");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            now = LocalDateTime.now();
            preparedStatement.setString(1, dtf.format(now));
            preparedStatement.setString(2, status);
            preparedStatement.setInt(4, person.getId());
            preparedStatement.setString(3, aim);
            int row = preparedStatement.executeUpdate();

            if (row > 0) {
                System.out.println("Updated");
            } else {
                System.out.println("Error(update)");
            }
        }
    }

    public String getStatus(Person person) throws SQLException {
        Connection connection = connect();
        if (connection != null) {
            String sql = "SELECT * FROM person where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, person.getId());
            ResultSet result = preparedStatement.executeQuery();

            if (!result.isBeforeFirst()) {
                return null;
            } else {
                while (result.next()) {
                    return result.getString("status");
                }
            }
        }
        return null;
    }
}
