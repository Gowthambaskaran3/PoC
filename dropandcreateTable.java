package PoC;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class dropandcreateTable {
    public static void main(String[] args) {
        Connection Conn = null;
        Statement stmt = null;
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

            stmt = Conn.createStatement();

            String drop = "DROP TABLE IF EXISTS student";
            stmt.executeUpdate(drop);
            String sql = "CREATE TABLE IF NOT EXISTS student "
                    + "(studentId int, " + " firstName VARCHAR(255), "
                    + " lastName VARCHAR(255), " + " departmentId int, " + " joiningDate date, " + " studentDob date, "
                    + " mobileNo bigint," + " email VARCHAR(255),"
                    + " PRIMARY KEY ( studentID ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
            System.out.println("Inserting records into the table...");
            sql = "INSERT INTO student VALUES(1, 'Vikesh', 'Vikki', 1 , '2021-10-05' , '2021-10-05' , 8374779566 , 'vikesh.pathipathi@perficient.com')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO student VALUES (2, 'Murali', 'Pathipatti', 1 , '2021-10-05' , '2021-10-05' , 7411112503 , 'sidram@gmail.com')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO student VALUES (3, 'Revanth', 'chinnu', 1 , '2021-10-05' , '2021-10-05' , 9492847372 , 'revanth@gmail.com')";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
