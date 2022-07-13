package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JButton Login,Cancel,Signup;
    JTextField username,password;
    Choice Logginas;
    Login(){
        super("Login Page");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        //--------------------username--------------------------------
        JLabel lblusername=new JLabel("username");
        lblusername.setBounds(300,20,100,20);  // to setBounds(left,top, self-width, self-height)   the setLayout() must be null 
        add(lblusername);
        
        username=new JTextField();
        username.setBounds(400,20,100,20);
        add(username);
        
        //-------------------password---------------------------------
        JLabel lblpassword=new JLabel("password");
        lblpassword.setBounds(300,60,100,20);
        add(lblpassword);
        
        password=new JTextField();
        password.setBounds(400,60,100,20);
        add(password);
        
        //-------------------Login as---------------------------------
        JLabel LoginAs=new JLabel("Login as");
        LoginAs.setBounds(300,100,100,20);
        add(LoginAs);
        
        Logginas=new Choice();
        Logginas.add("Admin");
        Logginas.add("Customer");
        Logginas.setBounds(400,100,100,20);
        add(Logginas);
        
        //----------------------Login-button-----------------------------
        ImageIcon L1=new ImageIcon(ClassLoader.getSystemResource("icons/login.png"));
        Image L2=L1.getImage().getScaledInstance(16, 16,Image.SCALE_DEFAULT);
        Login=new JButton("Login",new ImageIcon(L2));
        Login.setBounds(300,150,100,20);
        Login.addActionListener(this);
        add(Login);
        
        //----------------------Cancel-button----------------------------
        ImageIcon C1=new ImageIcon(ClassLoader.getSystemResource("icons/cancel.jpg"));
        Image C2=C1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        Cancel=new JButton("Cancel",new ImageIcon(C2));
        Cancel.setBounds(410,150,100,20);
        Cancel.addActionListener(this);
        add(Cancel);
        
        //-----------------------Signup-button---------------------------
        ImageIcon S1=new ImageIcon(ClassLoader.getSystemResource("icons/signup.png"));
        Image S2=S1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        Signup=new JButton("Sign up",new ImageIcon(S2));
        Signup.setBounds(350,200,100,20);
        Signup.addActionListener(this);
        add(Signup);
        
        //------------------------Main Image-------------------------------
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2=i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,250,250);
        add(image);
        
        setSize(640,300);
        setLocation(500,200);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==Login){
            String susername=username.getText();
            String spassword=password.getText();
            String user=Logginas.getSelectedItem();
                        
            try{
                Conn c=new Conn();
                String query= "select * from login where username='"+susername+"' and password= '"+spassword+"' and user = '"+user+"' ";
            
                ResultSet rs= c.s.executeQuery(query);
                
                if(rs.next()){
                    String meter=rs.getString("meter_no");
                    setVisible(false);
                    new Project(user,meter);
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid Login Credentials");  
                    username.setText("");
                    password.setText("");
                }              
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
        else if(ae.getSource()==Cancel){
            setVisible(false);
        }
        else if(ae.getSource()==Signup){
            setVisible(false);
            
            new Signup();
        }
    }
    
    public static void main(String[] args){
        new Login();
    }
}
