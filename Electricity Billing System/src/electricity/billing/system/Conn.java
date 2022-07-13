package electricity.billing.system;

import java.sql.*;
public class Conn {
    
    Connection c;
    Statement s;
    Conn(){
        //Step 1:- Register the Driver
        // 
//        Class.forName("com.mysql.cj.jdbc.Driver");

        
        try{
            //Step 2:- Creating connection with sql or any other database with jdbc
            
//             c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "root","123456");
                // we can also write the above statement as below and remove localhost:3306 because it is the default port of mysql
             c = DriverManager.getConnection("jdbc:mysql:///ebs", "root","123456");
             
             //Step 3:- Create Statement
             s=c.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
