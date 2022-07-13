package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener {
    String atype,meter;
    Project(String atype, String meter){
        this.atype=atype;
        this.meter=meter;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        
        //----------------------------------Background Image----------------------------------------
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/elect1.jpg"));
        Image i2=i1.getImage().getScaledInstance(1550, 850, Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        add(image);
        
        //----------------------------------------Menu Bar--------------------------------------------
        JMenuBar mb =new JMenuBar();
        setJMenuBar(mb);        
        setLayout(new FlowLayout());
        //------------Master-------------------
        JMenu master = new JMenu("Master");
        master.setForeground(Color.BLUE);
        
        
        //New Customer
        JMenuItem newcustomer=new JMenuItem("New Customer");
        newcustomer.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon1=new ImageIcon(ClassLoader.getSystemResource("icons/icon1.png"));
        Image image1=icon1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        newcustomer.setIcon(new ImageIcon(image1));
        newcustomer.setMnemonic('D');
        newcustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));        
        newcustomer.addActionListener(this);
        master.add(newcustomer);
        
        //Customer Details
        JMenuItem customerdetails=new JMenuItem("Customer Details");
        customerdetails.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon2=new ImageIcon(ClassLoader.getSystemResource("icons/icon2.png"));
        Image image2=icon2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        customerdetails.setIcon(new ImageIcon(image2));
        customerdetails.setMnemonic('M');
        customerdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK)); 
        customerdetails.addActionListener(this);
        master.add(customerdetails);
        
        //Deposit Details
        JMenuItem depositdetails=new JMenuItem("Deposit Details");
        depositdetails.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon3=new ImageIcon(ClassLoader.getSystemResource("icons/icon3.png"));
        Image image3=icon3.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        depositdetails.setIcon(new ImageIcon(image3));
        depositdetails.setMnemonic('N');
        depositdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));  
        depositdetails.addActionListener(this);
        master.add(depositdetails);
        
        //Calculate Bill
        JMenuItem calculatebill=new JMenuItem("Calculate Bill");
        calculatebill.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon4=new ImageIcon(ClassLoader.getSystemResource("icons/icon9.png"));
        Image image4=icon4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculatebill.setIcon(new ImageIcon(image4));
        calculatebill.setMnemonic('B');
        calculatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        calculatebill.addActionListener(this);
        master.add(calculatebill);
        
        
        //-----------------Information-----------------------
        JMenu info=new JMenu("Information");
        info.setForeground(Color.red);
        
        
        //Update Information        
        JMenuItem updateinfomation=new JMenuItem("Update Information");
        updateinfomation.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon5=new ImageIcon(ClassLoader.getSystemResource("icons/icon7.png"));
        Image image5=icon5.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        updateinfomation.setIcon(new ImageIcon(image5));
        updateinfomation.setMnemonic('P');
        updateinfomation.addActionListener(this);
        updateinfomation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));        
        info.add(updateinfomation);
        
        //View Information        
        JMenuItem viewinformation=new JMenuItem("View Information");
        viewinformation.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon6=new ImageIcon(ClassLoader.getSystemResource("icons/icon6.png"));
        Image image6=icon6.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        viewinformation.setIcon(new ImageIcon(image6));
        viewinformation.setMnemonic('I');
        viewinformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));    
        viewinformation.addActionListener(this);
        info.add(viewinformation);
        
        
        //-----------------------User-------------------------
        JMenu user=new JMenu("User");
        user.setForeground(Color.BLUE);
        
        
        //Pay Bills        
        JMenuItem paybills=new JMenuItem("Pay Bills");
        paybills.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon7=new ImageIcon(ClassLoader.getSystemResource("icons/icon4.png"));
        Image image7=icon7.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        paybills.setIcon(new ImageIcon(image7));
        paybills.setMnemonic('R');
        paybills.addActionListener(this);
        paybills.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));        
        user.add(paybills);
        
        //Bill Details
        JMenuItem billdetails=new JMenuItem("Bill Details");
        billdetails.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon8=new ImageIcon(ClassLoader.getSystemResource("icons/icon12.png"));
        Image image8=icon8.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        billdetails.setIcon(new ImageIcon(image8));
        billdetails.setMnemonic('L');
        billdetails.addActionListener(this);
        billdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));        
        user.add(billdetails);
        
        
        //------------------------------Report-----------------------
        JMenu report =new JMenu("Report");
        report.setForeground(Color.red);
        
        
        //Generate Bill
        
        JMenuItem generatebill=new JMenuItem("Generate Bill");
        generatebill.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon9=new ImageIcon(ClassLoader.getSystemResource("icons/icon7.png"));
        Image image9=icon9.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        generatebill.setIcon(new ImageIcon(image9));
        generatebill.setMnemonic('G');
        generatebill.addActionListener(this);
        generatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));        
        report.add(generatebill);
        
        
        //-----------------------------Utility-------------------------
        JMenu utility =new JMenu("Utility");
        utility.setForeground(Color.BLUE);
        
        
        //Notepad        
        JMenuItem notepad=new JMenuItem("Notepad");
        notepad.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon10=new ImageIcon(ClassLoader.getSystemResource("icons/icon12.png"));
        Image image10=icon10.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(image10));
        notepad.setMnemonic('N');
        notepad.addActionListener(this);
        notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));        
        utility.add(notepad);
        
        //Calculator
        JMenuItem calculator=new JMenuItem("Calculator");
        calculator.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon11=new ImageIcon(ClassLoader.getSystemResource("icons/icon9.png"));
        Image image11=icon11.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculator.setIcon(new ImageIcon(image11));
        calculator.setMnemonic('C');
        calculator.addActionListener(this);
        calculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));        
        utility.add(calculator);
        
        
        //-----------------------------Exit-------------------
        JMenu mexit=new JMenu("Exit");
        mexit.setForeground(Color.red);
        
        
        //Exit
        JMenuItem exit=new JMenuItem("Exit");
        exit.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon12=new ImageIcon(ClassLoader.getSystemResource("icons/icon11.png"));
        Image image12=icon12.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        exit.setIcon(new ImageIcon(image12));
        exit.setMnemonic('W');
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));        
        mexit.add(exit);
        
        if(this.atype.equals("Admin")){
            mb.add(master);            
        }
        else{
            mb.add(info);
            mb.add(user);
            mb.add(report);
        }
        mb.add(utility);
        mb.add(mexit);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        String msg=ae.getActionCommand();
        
        if(msg.equals("New Customer")){
            new NewCustomer();
        }else if(msg.equals("Deposit Details")){
            new DepositDetails();
            
        }else if(msg.equals("Customer Details")){
            new CustomerDetails();
            
        }else if(msg.equals("Calculate Bill")){
            new CalculateBill();
            
        }else if(msg.equals("View Information")){
            new ViewInformation(meter);
            
        }else if(msg.equals("Update Information")){
            new UpdateInformation(meter);
            
        }else if(msg.equals("Bill Details")){
            new BillDetails(meter);
        }else if(msg.equals("Notepad")){
            
            try{
                Runtime.getRuntime().exec("notepad.exe");                
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(msg.equals("Calculator")){
            
            try{
                Runtime.getRuntime().exec("calc.exe");                
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(msg.equals("Exit")){
            setVisible(false);
            new Login();
        }else if(msg.equals("Pay Bills")){
            new PayBill(meter);
        }else if(msg.equals("Generate Bill")){
            new GenerateBill(meter);
        }
        
    }

    public static void main(String[] args){
        new Project("","");
    }
}
