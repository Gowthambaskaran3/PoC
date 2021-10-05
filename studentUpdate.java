package PoC;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class studentUpdate {
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

            Stmnt.executeUpdate("update student set lastName = 'Ramadoss Baskaran' where studentId = '8'");
            System.out.println("Student details has been updated in the DB");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
