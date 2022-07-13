package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils; // DbUtils is a class
import java.awt.event.*;

public class BillDetails extends JFrame implements ActionListener {
    
    Choice meternumber, cmonth;
    JTable table;
    JButton search,print;
    String meter;
    BillDetails(String meter){
        super("Bill Details");
        this.meter=meter;
        setSize(700,700);
        setLocation(400,100);
        setVisible(true);
        setLayout(null);
        
        getContentPane().setBackground(Color.WHITE);
        
        
        
        //------------------------------------------Search by Month-----------------------------
        JLabel heading=new JLabel("Bill Details");
        heading.setBounds(270,20,400,40);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(heading);
        
        
        
        //-------------------------------------------Tabel-------------------------------------------
        
        table=new JTable();
        
        try{
            Conn c=new Conn();
            ResultSet rs = c.s.executeQuery("select * from bill where meter_no='"+meter+"' ");
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(0,100,700,600);
        add(sp);
        
           
        
    }
    
    public void actionPerformed(ActionEvent ae){
        
        
       
    }
    
    public static void main(String[] args){
        new BillDetails("");
    }
}
