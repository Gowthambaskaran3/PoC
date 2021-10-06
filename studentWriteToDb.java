package POC2;

import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

public class studentWriteToDb {
    public static void main(String[] args) {
        Connection Conn = null;
        Statement Stmnt = null;
        Statement stmt = null;
        ResultSet rsSet = null;
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
            stmt = Conn.createStatement();

            FileInputStream file = new FileInputStream("D:\\java\\inputpoc2.txt");
            DataInputStream in = new DataInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            ArrayList list = new ArrayList();
            while ((strLine = br.readLine()) != null) {
                list.add(strLine);
            }
            Iterator itr;
            for (itr = list.iterator(); itr.hasNext(); ) {
                String str = itr.next().toString();
                String[] splitSt = str.split("\t");
                String id = "", firstname = "", lastname = "", deptid = "", joiningdate = "", studentDoB = "",mobileNo = "",email = "";
                for (int i = 0; i < splitSt.length; i++) {
                    id = splitSt[0];
                    firstname = splitSt[1];
                    lastname = splitSt[2];
                    deptid = splitSt[3];
                    joiningdate = splitSt[4];
                    studentDoB = splitSt[5];
                    mobileNo = splitSt[6];
                    email= splitSt[7];
                }

                rsSet = Stmnt.executeQuery("select * from department");
                while(rsSet.next()){
                    if(deptid.equals(rsSet.getString("departmentName"))){
                        deptid=new String(rsSet.getString("departmentId"));
                    }
                }
                /*
                ResultSet resSet = stmt.executeQuery("select * from student where studentId = "+id);
                while (resSet.next()) {
                    System.out.println(resSet.getString("studentId")+" "+id);
                    if (id.equals(resSet.getString("studentId"))) {
                        System.out.println("Entered if");
                        String query ="update student set studentId=" + id + ",firstName=" + firstname + ",lastName=" + lastname + ",departmentId=" + deptid + ",joiningDate=" + joiningdate + ",studentDob=" + studentDoB + ",mobileNo=" + mobileNo + ",email=" + email + "where sudentId=" + resSet.getString("studentId");
//                        String query = "UPDATE student SET fullName = gowtham, lastName = baskaran, departmentId = 3, joiningDate = 2017-08-12, studentDob = 2000-07-03, mobileNo = 41387089127, email = gowtham@gmail.com WHERE (studentId = ?)";
//                        String query = "update student set firstName = 'philips' where studentId = 3";
                        System.out.println(query);
                        Stmnt.executeUpdate(query);
                    } else {
                        System.out.println("Entered else");
                        Stmnt.executeUpdate("insert into student(studentId,firstName,lastName,departmentId,joiningDate,studentDob,mobileNo,email) values('" + id + "','" + firstname + "','" + lastname + "','" + deptid + "','" + joiningdate + "','" + studentDoB + "','" + mobileNo + "','" + email + "')");
                        System.out.println("inserted " + firstname + "into db");
                    }
                }*/

                Stmnt.executeUpdate("insert into student(studentId,firstName,lastName,departmentId,joiningDate,studentDob,mobileNo,email) values('" + id + "','" + firstname + "','" + lastname + "','" + deptid + "','" + joiningdate + "','" + studentDoB + "','" + mobileNo + "','" + email + "')");
                System.out.println("inserted " + firstname + "into db");

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
