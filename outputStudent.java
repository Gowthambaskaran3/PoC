package POC2;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class outputStudent {
    public static void main(String[] args) {
        Connection Conn = null;
        Statement Stmnt = null;
        ResultSet rsSet = null;
        ResultSet resSet = null;
        try {

            Properties props = new Properties();
            props.load(new FileInputStream("D:\\java\\poc2.properties"));

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
            FileOutputStream file = new FileOutputStream("D:\\java\\studentinfo.txt");
            DataOutputStream data = new DataOutputStream(file);


            while (rsSet.next()) {
                String ID = rsSet.getString("studentID");
                String FirstName = rsSet.getString("firstName");
                String LastName = rsSet.getString("lastName");
                String joiningDate = rsSet.getString("joiningDate");
                String studentDoB = rsSet.getString("studentDob");
                String mobile = rsSet.getString("mobileNo");
                String email = rsSet.getString("email");
                String deptid = rsSet.getString("departmentName");


                String s = "\n" + ID + " " + FirstName + " " + LastName + " " + deptid + " " + joiningDate + " " + studentDoB + " " + mobile + "" + email;
                data.writeBytes(s);

            }
            System.out.println("The student info has been written into the studentinfo file");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
