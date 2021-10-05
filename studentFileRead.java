package PoC;

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

public class studentFileRead {
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

            FileInputStream file = new FileInputStream("D:\\java\\input.txt");
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
                String[] splitSt = str.split(" ");
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
                }}


                int k = Stmnt.executeUpdate("insert into student(studentId,firstName,lastName,departmentId,joiningDate,studentDob,mobileNo,email) values('" + id + "','" + firstname + "','" + lastname + "','" + deptid + "','" + joiningdate + "','"+studentDoB +"','"+mobileNo+"','"+email+"')");
                System.out.println("inserted "+firstname+"into db");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
