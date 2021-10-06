package POC2;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class countDepartmentwise {
    public static void main(String[] args) {
        Connection Conn = null;
        Statement Stmnt = null;
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

            rsSet = Stmnt.executeQuery("select * from " +
                    "student " +
                    "NATURAL JOIN " +
                    "department");

            int cs=0,it=0,eee=0,ece=0,arch=0,mech=0;


            while (rsSet.next()) {
                String deptid = rsSet.getString("departmentName");

                if(deptid.equals("CS")){
                    cs++;
                }
                else if(deptid.equals("IT")){
                    it++;
                }
                else if(deptid.equals("EEE")){
                    eee++;
                }
                else if(deptid.equals("ECE")){
                    ece++;
                }
                else if(deptid.equals("Arch")){
                    arch++;
                }
                else{
                    mech++;
                }

            }
            FileOutputStream file = new FileOutputStream("D:\\java\\departmentcount.txt");
            DataOutputStream data = new DataOutputStream(file);
            String s = "CS "+cs;
            data.writeBytes(s);
            String e = "\nEEE "+eee;
            data.writeBytes(e);
            String i = "\nIT "+it;
            data.writeBytes(i);
            String ec = "\nECE "+ece;
            data.writeBytes(ec);
            String ar = "\nArch "+arch;
            data.writeBytes(ar);
            String m = "\nMech "+mech;
            data.writeBytes(m);
            System.out.println("Department wise data have been creted and stored in departmentcount.txt");

        }catch (Exception e){
            e.printStackTrace();}
    }

}
