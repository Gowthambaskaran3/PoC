package PoC;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class studentInsert {
    public static void main(String[] args) {
        Connection Conn = null;
        Statement Stmnt = null;
        ResultSet rsSet = null;
        try {

            Properties props = new Properties();
            props.load(new FileInputStream("D:\\java\\db.properties"));

            String theUser = props.getProperty("user");
            String thePassword = props.getProperty("password");
            String theDburl = props.getProperty("dburl");

            System.out.println("Connecting to database...");
            System.out.println("Database URL: " + theDburl);
            System.out.println("User: " + theUser);


            Conn = DriverManager.getConnection(theDburl, theUser, thePassword);

            System.out.println("\nConnection successful!\n");

            Stmnt = Conn.createStatement();

            Stmnt.executeUpdate("insert into student(studentId,firstName,lastName,departmentId,joiningDate,studentDob,mobileNo,email) " +
                    "values" +
                    "('8', + 'Gowtham','Baskaran','4','2017-08-12','2000-07-03','8190810974','gowtham@gmail.com')");

            System.out.println("Student has been added into the DB");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
