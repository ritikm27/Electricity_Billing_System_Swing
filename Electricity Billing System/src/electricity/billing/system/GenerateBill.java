package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GenerateBill extends JFrame implements ActionListener {
    
    Choice cmonth;
    JTextArea area;
    JButton bill;
    String meter;
    GenerateBill(String meter){
        this.meter=meter;
        setSize(500,800);
        setLocation(550,20);
        setVisible(true);
        
        setLayout(new BorderLayout());
        
        JPanel panel=new JPanel();
        
        JLabel heading =new JLabel("Generate Bill");
        JLabel meternumber=new JLabel(meter);
        
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
        
        area=new JTextArea(50,15); //  new JTextArea(row,column)
        area.setText("\n\t-----------Click on the ------------\n\t Generate Bill button to get\n\t the bill of selected month");
        area.setFont(new Font("Sanserif", Font.ITALIC,18));
        
        JScrollPane pane =new JScrollPane(area);
        add(pane, "Center");
        
        bill=new JButton("Generate Bill");
        bill.addActionListener(this);
        add(bill, "South");
        
        panel.add(heading);
        panel.add(meternumber);
        panel.add(cmonth);
        add(panel, "North");
        
    }
    
    public void actionPerformed(ActionEvent ae){
        
        try{
            Conn c=new Conn();
            String month=cmonth.getSelectedItem();
            
            area.setText("\n\tReliance Power Limited\n             Electricity Bill Generated for the Month\n\t   of "+month+", 2022");
            
            ResultSet rs=c.s.executeQuery("select * from customer where meter_no='"+meter+"'");
            
            if(rs.next()){
                area.append("\n\n   Customer Name   :"+ rs.getString("name"));
                area.append("\n   Meter Number      :"+ rs.getString("meter_no"));
                area.append("\n   Address            :"+ rs.getString("address"));
                area.append("\n   City                :"+ rs.getString("city"));
                area.append("\n   State               :"+ rs.getString("state"));
                area.append("\n   Email              :"+ rs.getString("email"));
                area.append("\n   Phone             :"+ rs.getString("phone"));
                
            }
            
            rs=c.s.executeQuery("select * from meter_info where meter_no='"+meter+"'");
            
            if(rs.next()){
                area.append("\n--------------------------------------------- ");
                area.append("\n\n   Meter Location  :"+ rs.getString("location"));
                area.append("\n   Meter Type      :"+ rs.getString("meter_type"));
                area.append("\n   Phase Code            :"+ rs.getString("phase_code"));
                area.append("\n   Bill Type                :"+ rs.getString("bill_type"));
                area.append("\n   Days               :"+ rs.getString("days"));
                
            }
            
            rs=c.s.executeQuery("select * from tax");
            
            if(rs.next()){
                area.append("\n--------------------------------------------- ");
                area.append("\n\n   Cost Per Unit   :  "+ rs.getString("cost_per_unit"));
                area.append("\n   Meter Rent      :  "+ rs.getString("meter_rent"));
                area.append("\n   Service Charge     :  "+ rs.getString("service_charge"));
                area.append("\n   Service Tax           :  "+ rs.getString("service_tax"));
                area.append("\n   Swacch Bharat Cess        :  "+ rs.getString("swacch_bharat_cess"));
                area.append("\n   Fixed Tax              :  "+ rs.getString("fixed_tax"));
                area.append("\n ");
                
            }
            
            rs=c.s.executeQuery("select * from bill where meter_no='"+meter+"' and month='"+month+"'");
            
            if(rs.next()){
                area.append("\n--------------------------------------------- ");
                area.append("\n\n   Month           : "+month+"");
                area.append("\n   Units      :"+ rs.getString("units"));
                area.append("\n   Total Bill            :"+ rs.getString("totalbill"));
                area.append("\n--------------------------------------------- ");
                area.append("\n ");
                area.append("\n   Total Payable             :"+ rs.getString("totalbill"));
                
            }
            
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        new GenerateBill("");
    }
}
