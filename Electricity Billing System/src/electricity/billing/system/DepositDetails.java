package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils; // DbUtils is a class
import java.awt.event.*;

public class DepositDetails extends JFrame implements ActionListener {
    
    Choice meternumber, cmonth;
    JTable table;
    JButton search,print;
    DepositDetails(){
        super("Deposit Details");
        setSize(700,700);
        setLocation(400,100);
        setVisible(true);
        setLayout(null);
        
        getContentPane().setBackground(Color.WHITE);
        
        //----------------------------------------Search by Meter Number---------------------------
        JLabel lblmeternumber=new JLabel("Search by Meter Number");
        lblmeternumber.setBounds(20,20,150,20);
        add(lblmeternumber);
        
        meternumber=new Choice();
        
        try{
            Conn c=new Conn();
            String query="select * from customer";
            
            ResultSet rs=c.s.executeQuery(query);
            
            while(rs.next()){
                meternumber.add(rs.getString("meter_no"));
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        meternumber.setBounds(190,20,100,20);
        add(meternumber);
        
        //------------------------------------------Search by Month-----------------------------
        JLabel lblmonth=new JLabel("Search by Month");
        lblmonth.setBounds(370,20,100,20);
        add(lblmonth);
        
        cmonth=new Choice();
        
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        
        cmonth.setBounds(490,20,100,20);
        add(cmonth);
        
        //-------------------------------------------Tabel-------------------------------------------
        
        table=new JTable();
        
        try{
            Conn c=new Conn();
            ResultSet rs = c.s.executeQuery("select * from bill");
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(0,100,700,600);
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
            String meter=meternumber.getSelectedItem();
            String month=cmonth.getSelectedItem();
            
            try{
                Conn c=new Conn();
                ResultSet rs=c.s.executeQuery("select * from bill where meter_no='"+meter+"' and month='"+month+"' ");
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
        new DepositDetails();
    }
}
