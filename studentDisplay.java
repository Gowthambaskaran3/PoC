package PoC;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class studentDisplay {
    public static void main(String[] args) {
        Connection Conn = null;
        Statement Stmnt = null;
        ResultSet rsSet = null;
        ResultSet resSet = null;
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

            rsSet = Stmnt.executeQuery("select * from " +
                    "student " +
                    "NATURAL JOIN " +
                    "department");


            while (rsSet.next()) {
                String ID = rsSet.getString("studentID");
                String FirstName = rsSet.getString("firstName");
                String LastName = rsSet.getString("lastName");
                String joiningDate = rsSet.getString("joiningDate");
                String studentDoB = rsSet.getString("studentDob");
                String mobile = rsSet.getString("mobileNo");
                String email = rsSet.getString("email");
                String deptid = rsSet.getString("departmentName");

                System.out.format("%10s%15s%15s%15s%15s%15s%15s%40s\n", ID, FirstName, LastName,deptid,joiningDate,studentDoB,mobile, email);

            }

        }catch (Exception e){
        e.printStackTrace();}
    }
    }