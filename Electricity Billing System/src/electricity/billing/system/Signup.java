package electricity.billing.system;

import javax.swing.*;   //   (.*;) only imports all the classes in the folder/package but it does not imports the sub-folder/sub-packages
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener{
    JButton create,back;
    Choice accountType;
    JTextField username,password,name,meter;
    Signup(){
        super("Sign up");
        setBounds(450,150,700,400);   // we can create a frame with setBounds() also without using setSize()
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JPanel panel=new JPanel();   // panel is like a frame
        panel.setBounds(30,30,640,300);
        panel.setBackground(Color.WHITE);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), "Create Account", TitledBorder.LEADING, TitledBorder.TOP,null, new Color(173, 216, 230)));
        panel.setLayout(null);
        panel.setForeground(new Color(34, 139, 34));
        add(panel);
        
        //-----------------------------------Create Account As-------------------------------
        JLabel heading=new JLabel("Create Account");
        heading.setBounds(100,50,140,20);
        heading.setForeground(Color.GRAY);   // setForeground() is used to color the font
        heading.setFont(new Font("Thahomas", Font.BOLD, 14));
        panel.add(heading);
        
        accountType=new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(260,50,150,20);
        panel.add(accountType);
        
        //------------------------------------Meter Number---------------------------------
        JLabel lblmeter=new JLabel("Meter Number");
        lblmeter.setBounds(100,90,140,20);
        lblmeter.setForeground(Color.GRAY);
        lblmeter.setFont(new Font("Thahomas", Font.BOLD, 14));
        lblmeter.setVisible(false);
        panel.add(lblmeter);
        
        meter=new JTextField();
        meter.setBounds(260,90,150,20);
        meter.setVisible(false);
        panel.add(meter);
        
        //-----------------------------------Username------------------------------------
        JLabel lblusername=new JLabel("Username");
        lblusername.setBounds(100,130,140,20);
        lblusername.setForeground(Color.GRAY);
        lblusername.setFont(new Font("Thahomas", Font.BOLD, 14));
        panel.add(lblusername);
        
        username=new JTextField();
        username.setBounds(260,130,150,20);
        panel.add(username);
        
        //------------------------------------password----------------------------------
        JLabel lblpassword=new JLabel("Password");
        lblpassword.setBounds(100,170,140,20);
        lblpassword.setForeground(Color.GRAY);
        lblpassword.setFont(new Font("Thahomas", Font.BOLD, 14));
        panel.add(lblpassword);
        
        password=new JTextField();
        password.setBounds(260,170,150,20);
        panel.add(password);
        
        //----------------------------------Name-----------------------------------------
        JLabel lblname=new JLabel("Name");
        lblname.setBounds(100,210,140,20);
        lblname.setForeground(Color.GRAY);
        lblname.setFont(new Font("Thahomas", Font.BOLD, 14));
        panel.add(lblname);
        
        name=new JTextField();
        name.setBounds(260,210,150,20);
        panel.add(name);
        
        //-----------------------------------Create button--------------------------
        create=new JButton("Create");
        create.setBounds(140,260,120,25);
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.addActionListener(this);
        panel.add(create);
        
        //-----------------------------------Back button----------------------------
        back=new JButton("Back");
        back.setBounds(280,260,120,25);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        panel.add(back);       
        
        //-------------------------------Image--------------------------------------
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/signupImage.png"));
        Image i2=i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(410,0,250,250);
        panel.add(image);
        
        
         //--------------------------------------
        
        meter.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe){
                
            }
            
            @Override
            public void focusLost(FocusEvent fe){
                
                try{
                    Conn c=new Conn();
                    ResultSet rs=c.s.executeQuery("select * from login where meter_no='"+meter.getText()+"' ");
                    
                    while(rs.next()){
                        name.setText(rs.getString("name"));
                    }
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        
        //--------------------------------------Admin Customer--------------------------------
        accountType.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie){
                
                String user=accountType.getSelectedItem();
                if(user.equals("Admin")){  
                    meter.setVisible(false);
                    lblmeter.setVisible(false);
                    name.setEditable(true);
                }else{
                    meter.setVisible(true);
                    lblmeter.setVisible(true);
                    name.setEditable(false);
                }
            }
        });
        
        
        
    }
    
    public void actionPerformed(ActionEvent  ae){
        if(ae.getSource()==create){
           
            String atype=accountType.getSelectedItem();
            String sname=name.getText();
            String susername=username.getText();
            String smeter=meter.getText();
            String spassword=password.getText();
            
            try{
                Conn c = new Conn();
                
                String query;
                if(atype.equals("Admin")){
                    query    = "insert into login values('"+smeter+"', '"+susername+"', '"+sname+"', '"+spassword+"','"+atype+"')";
                }else{
                    query= "update login set username='"+susername+"', password='"+spassword+"', user='"+atype+"' where meter_no='"+smeter+"'";
                }
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                
                setVisible(false);
                
                new Login();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==back){
            setVisible(false);
            
            new Login();
        }
    }
    
    public static void main(String[] args){
        new Signup();
    }
}








