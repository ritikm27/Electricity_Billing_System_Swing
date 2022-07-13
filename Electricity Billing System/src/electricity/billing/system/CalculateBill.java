package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;


public class CalculateBill extends JFrame implements ActionListener {
    
    JTextField tfname,tfaddress,tfunits,tfemail,tfphone;
    JButton submit,cancel;
    JLabel lblmeter;
    Choice meterNo, cmonth;
    CalculateBill(){
        setSize(700,500);
        setLocation(400,200);
        setVisible(true);
        
        JPanel p=new JPanel();
        p.setBackground(new Color(173, 216, 230));
        p.setLayout(null);
        add(p);
        
        //----------------------------------------Heading-----------------------------------
        JLabel heading=new JLabel("Calculate Electricity Bill");
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        heading.setBounds(140,10,400,25);
        p.add(heading);
        
        
        //--------------------------------Meter Number-------------------------------------
        JLabel lblMeterNo=new JLabel("Meter Number");
        lblMeterNo.setBounds(100,80,100,20);
        p.add(lblMeterNo);
        
        meterNo=new Choice();
        meterNo.setBounds(240,80,200,20);
        try{
            Conn c=new Conn();
            String query="select * from customer";
            
            ResultSet rs= c.s.executeQuery(query);
            
            while(rs.next()){
                
                meterNo.add(rs.getString("meter_no"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        p.add(meterNo);
        
        //------------------------------------Name and Address---------------------------------------
        
        JLabel lbladdress=new JLabel("Address");
        lbladdress.setBounds(100,160,100,20);
        p.add(lbladdress);
        
        JLabel addressvalue=new JLabel();
        addressvalue.setBounds(240,160,200,20);
        p.add(addressvalue);
        
        JLabel lblname=new JLabel("Name");
        lblname.setBounds(100,120,100,20);
        p.add(lblname);
        
        JLabel namevalue=new JLabel();
        
        try{
            Conn c=new Conn();
            
            String query="Select * from customer where meter_no = '"+meterNo.getSelectedItem()+"'";
            
            ResultSet rs=c.s.executeQuery(query);
            
            if(rs.next()){
            namevalue.setText(rs.getString("name"));
            addressvalue.setText(rs.getString("address"));
            }
            
        }catch(Exception  e){
            e.printStackTrace();
        }
        namevalue.setBounds(240,120,200,20);
        p.add(namevalue);
        
        //-----------------------------------------------------------------------------------------------------------
        
        
        // now we will create functionality so that name and address changes dynamically when we change the meter number
        
        meterNo.addItemListener(new ItemListener(){
            
            public void itemStateChanged(ItemEvent ie){
            try{
                Conn c=new Conn();

                String query="Select * from customer where meter_no = '"+meterNo.getSelectedItem()+"'";

                ResultSet rs=c.s.executeQuery(query);

                if(rs.next()){
                namevalue.setText(rs.getString("name"));
                addressvalue.setText(rs.getString("address"));
                }
            
            }catch(Exception  e){
                e.printStackTrace();
            }
            }
        });
        
        
        
        //------------------------------------Units Consumed---------------------------------------
        JLabel lblunits=new JLabel("Units Consumed");
        lblunits.setBounds(100,200,100,20);
        p.add(lblunits);
        
        tfunits=new JTextField();
        tfunits.setBounds(240,200,200,20);
        p.add(tfunits);
        
        //------------------------------------Month---------------------------------------
        JLabel lblmonth=new JLabel("Month");
        lblmonth.setBounds(100,240,100,20);
        p.add(lblmonth);
        
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
        
        cmonth.setBounds(240,240,200,20);
        p.add(cmonth);
        
        
        
        //-----------------------------------Button next----------------------------------
        submit=new JButton("Submit");
        submit.setBounds(120,380,100,25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        p.add(submit);
        
        //-----------------------------------Button cancel----------------------------------
        cancel=new JButton("Cancel");
        cancel.setBounds(280,380,100,25);        
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);
        
        //------------------------------------Image----------------------------------------
        
        //By default the layout of the frame is Border Layout
        //here we will create a new Border Layout
        setLayout(new BorderLayout());
        
        add(p,"Center");
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/hicon2.jpg"));
        Image i2=i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        add(image, "West");
        
        getContentPane().setBackground(Color.WHITE);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==submit){
            String meter =meterNo.getSelectedItem();
            String units =tfunits.getText();
            String month =cmonth.getSelectedItem();
            
            String query="select * from tax";
            
            int totalbill=0;
            
            int units_consumed=Integer.parseInt(units);
            
            try{
                
                Conn c=new Conn();                
                ResultSet rs=c.s.executeQuery(query);
                while(rs.next()){
                totalbill += units_consumed * Integer.parseInt(rs.getString("cost_per_unit"));
                totalbill += Integer.parseInt(rs.getString("meter_rent"));
                totalbill += Integer.parseInt(rs.getString("service_charge"));
                totalbill += Integer.parseInt(rs.getString("service_tax"));
                totalbill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
                totalbill += Integer.parseInt(rs.getString("fixed_tax"));                                           
                }
 
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
            String query2="insert into bill values('"+meter+"', '"+month+"', '"+units+"','"+totalbill+"','Not Paid')";
            
            try{
                Conn c=new Conn();
                c.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "Customer Bill Updated successfully");
                setVisible(false);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new CalculateBill();
    }
}
