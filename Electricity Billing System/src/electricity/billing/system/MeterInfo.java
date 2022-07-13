package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;


public class MeterInfo extends JFrame implements ActionListener {
    
    
    JButton submit;
   
    Choice mlocation, mtype, mpcode, mbilltype;
    String meterNumber;
    MeterInfo(String meterNumber){
        this.meterNumber=meterNumber;
        setSize(700,500);
        setLocation(400,200);
        setVisible(true);
        
        JPanel p=new JPanel();
        p.setBackground(new Color(173, 216, 230));
        p.setLayout(null);
        add(p);
        
        //----------------------------------------Heading-----------------------------------
        JLabel heading=new JLabel("New Customer");
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        heading.setBounds(180,10,200,25);
        p.add(heading);
        
        //----------------------------Meter Number----------------------------------------
        JLabel lblMeterNo=new JLabel("Meter Number");
        lblMeterNo.setBounds(100,80,100,20);
        p.add(lblMeterNo);
        
         JLabel lblmeternumber=new JLabel(meterNumber);
        lblmeternumber.setBounds(240,80,100,20);
        p.add(lblmeternumber);
        
        //--------------------------------Meter Loaction-------------------------------------
        JLabel lblmeterlocation=new JLabel("Meter Location");
        lblmeterlocation.setBounds(100,120,100,20);
        p.add(lblmeterlocation);
        
        mlocation=new Choice();
        mlocation.add("Outside");
        mlocation.add("Inside");
        mlocation.setBounds(240,120,200,20);
        p.add(mlocation);
        
       
        //------------------------------------Meter Type---------------------------------------
        JLabel lblmetertype=new JLabel("Meter Type");
        lblmetertype.setBounds(100,160,100,20);
        p.add(lblmetertype);
        
        mtype=new Choice();
        mtype.add("Electric Meter");
        mtype.add("Solar Meter");
        mtype.add("Smart Meter");
        mtype.setBounds(240,160,200,20);
        p.add(mtype);
        
        
        //------------------------------------Phase Code---------------------------------------
        JLabel lblphasecode=new JLabel("Phase Code");
        lblphasecode.setBounds(100,200,100,20);
        p.add(lblphasecode);
        
        mpcode=new Choice();
        mpcode.add("011");
        mpcode.add("022");
        mpcode.add("033");
        mpcode.add("044");
        mpcode.add("055");
        mpcode.add("066");
        mpcode.add("077");
        mpcode.add("088");
        mpcode.add("099");
        mpcode.setBounds(240,200,200,20);
        p.add(mpcode);
        
        //------------------------------------Bill Type---------------------------------------
        JLabel lblBilltype=new JLabel("Bill Type");
        lblBilltype.setBounds(100,240,100,20);
        p.add(lblBilltype);
        
        mbilltype=new Choice();
        mbilltype.add("Normal");
        mbilltype.add("Industrial");
        mbilltype.setBounds(240,240,200,20);
        p.add(mbilltype);
        
        //------------------------------------Days---------------------------------------
        JLabel lbldays=new JLabel("Days");
        lbldays.setBounds(100,280,100,20);
        p.add(lbldays);
        
        JLabel lbldaysNo=new JLabel("30 Days");
        lbldaysNo.setBounds(240,280,100,20);
        p.add(lbldaysNo);
        
        
        
        //------------------------------------Note---------------------------------------
        JLabel lblnote=new JLabel("Note");
        lblnote.setBounds(100,320,100,20);
        p.add(lblnote);
        
        JLabel note=new JLabel("By default Bill is calculated for 30 days");
        note.setBounds(240,320,500,20);
        p.add(note);
                
        //-----------------------------------Button Submit----------------------------------
        submit=new JButton("Submit");
        submit.setBounds(200,380,100,25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        p.add(submit);
        
        
        //------------------------------------Image----------------------------------------
        
        //By default the layout of the frame is Border Layout
        //here we will create a new Border Layout
        setLayout(new BorderLayout());
        
        add(p,"Center");
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/hicon1.jpg"));
        Image i2=i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        add(image, "West");
        
        getContentPane().setBackground(Color.WHITE);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==submit){
            String meterNo=meterNumber;
            String location=mlocation.getSelectedItem();
            String type=mtype.getSelectedItem();
            String phasecode=mpcode.getSelectedItem();
            String billtype=mbilltype.getSelectedItem();
            String days="30";
            
            String query="insert into meter_info values('"+meterNo+"', '"+location+"', '"+type+"', '"+phasecode+"', '"+billtype+"', '"+days+"')";
            
            
            try{
                
                Conn c=new Conn();
                
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Meter Details Added Successfully");
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
        new MeterInfo("");
    }
}
