package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils; // DbUtils is a class
import java.awt.event.*;

public class CustomerDetails extends JFrame implements ActionListener {
    
    JTextField meternumber,name;
    JTable table;
    JButton search,print;
    CustomerDetails(){
        super("Customer Details");
        setSize(1200,650);
        setLocation(200,100);
        setVisible(true);
        setLayout(null);
        
        getContentPane().setBackground(Color.WHITE);
        
        //----------------------------------------Search by Meter Number---------------------------
        JLabel lblmeternumber=new JLabel("Search by Meter Number");
        lblmeternumber.setBounds(20,20,150,20);
        add(lblmeternumber);
        
        meternumber=new JTextField();
        meternumber.setBounds(190,20,100,20);
        add(meternumber);
        
        //------------------------------------------Search by Month-----------------------------
        JLabel lblname=new JLabel("Search by Name");
        lblname.setBounds(370,20,100,20);
        add(lblname);
        
        name=new JTextField();
        name.setBounds(600,20,200,20);
        add(name);
        
       
        
        //-------------------------------------------Tabel-------------------------------------------
        
        table=new JTable();
        
        try{
            Conn c=new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(0,100,1200,550);
        add(sp);
        
        
        //---------------------------------Button Search-------------------------------
        search=new JButton("Search");
        search.setBounds(40,70,80,20);
        search.addActionListener(this);
        add(search);
        
        //---------------------------------Button Search-------------------------------
        print=new JButton("Print");
        print.setBounds(140,70,80,20);
        print.addActionListener(this);
        add(print);   
        
        
    }
    
    public void actionPerformed(ActionEvent ae){
        String msg= ae.getActionCommand();
        
        if(msg.equals("Search")){
            String meter=meternumber.getText();
            String month=name.getText();
            
            try{
                Conn c=new Conn();
                ResultSet rs=c.s.executeQuery("select * from customer where meter_no='"+meter+"' or name='"+month+"' ");
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(msg.equals("Print")){
            
            try{
                table.print();
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args){
        new CustomerDetails();
    }
}
