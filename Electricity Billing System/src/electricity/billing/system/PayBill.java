package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class PayBill extends JFrame implements ActionListener {
    
    JButton pay,back;
    Choice cmonth;
    String meter;
    PayBill(String meter){
        this.meter=meter;
        setBounds(300,150,900,600);
        setLayout(null);
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel heading =new JLabel("Pay Electricity Bill");
        heading.setFont(new Font("Tahoma", Font.PLAIN,24));
        heading.setBounds(120,5,400,30);
        add(heading);
        
        //----------------------------------Meter Number----------------------------------
        JLabel lblmeternumber=new JLabel("Meter Number");
        lblmeternumber.setBounds(35,80,200,20);
        add(lblmeternumber);
        
        JLabel meternumber=new JLabel("");
        meternumber.setBounds(300,80,200,20);
        add(meternumber);
        
        //----------------------------------Name----------------------------------
        JLabel lblname=new JLabel("Name");
        lblname.setBounds(35,140,200,20);
        add(lblname);
        
        JLabel name=new JLabel("");
        name.setBounds(300,140,200,20);
        add(name);
        
        //----------------------------------Month----------------------------------
        JLabel lblmonth=new JLabel("Month");
        lblmonth.setBounds(35,200,200,20);
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
        
        cmonth.setBounds(300,200,200,20);
        add(cmonth);
        
        //----------------------------------Units----------------------------------
        JLabel lblunits=new JLabel("Units");
        lblunits.setBounds(35,260,200,20);
        add(lblunits);
        
        JLabel labelunits=new JLabel("");
        labelunits.setBounds(300,260,200,20);
        add(labelunits);
        
        //----------------------------------Total Bill----------------------------------
        JLabel lbltotal=new JLabel("Total Bill");
        lbltotal.setBounds(35,320,200,20);
        add(lbltotal);
        
        JLabel labeltotal=new JLabel("");
        labeltotal.setBounds(300,320,200,20);
        add(labeltotal);
        
        //----------------------------------Status----------------------------------
        JLabel lblstatus=new JLabel("Status");
        lblstatus.setBounds(35,380,200,20);
        add(lblstatus);
        
        JLabel labelstatus=new JLabel("");
        labelstatus.setBounds(300,380,200,20);
        labelstatus.setForeground(Color.red);
        add(labelstatus);
        
        //------------------------------------Button Pay------------------------------
        pay=new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100,460,100,25);
        pay.addActionListener(this);
        add(pay);
        
        //------------------------------------Button Back------------------------------
        back=new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(230,460,100,25);
        back.addActionListener(this);
        add(back);
        
        //------------------------------------Image-------------------------------------
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/bill.png"));
        Image i2=i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(400,120,600,300);
        add(image);
        
        
        
        //--------------------------------------------------------
        
        try{
            Conn c=new Conn();
            ResultSet rs= c.s.executeQuery("select * from customer where meter_no='"+meter+"' ");
            while(rs.next()){
                meternumber.setText(meter);
                name.setText(rs.getString("name"));
            }
            
            rs=c.s.executeQuery("select * from bill where meter_no='"+meter+"' and month='"+cmonth.getSelectedItem()+"'");
            while(rs.next()){
                labelunits.setText(rs.getString("units"));
                labeltotal.setText(rs.getString("totalbill"));
                labelstatus.setText(rs.getString("status"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //-------------------------------------------------------
        cmonth.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ae){
            
                 try{
                    Conn c=new Conn();
                    
                    ResultSet rs=c.s.executeQuery("select * from bill where meter_no='"+meter+"' and month='"+cmonth.getSelectedItem()+"'");
                    while(rs.next()){
                        labelunits.setText(rs.getString("units"));
                        labeltotal.setText(rs.getString("totalbill"));
                        labelstatus.setText(rs.getString("status"));
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
                
            }
        });
    }  
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==pay){
            try{
                Conn c=new Conn();
                c.s.executeUpdate("update bill set status='Paid' where meter_no='"+meter+"'");
                setVisible(false);
                new Paytm(meter);
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }

        
    public static void main(String[] args){
        new PayBill("");
    }
    
    
    
}
