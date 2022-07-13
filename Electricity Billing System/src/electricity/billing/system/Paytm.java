package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Paytm extends JFrame implements ActionListener {
    
    JButton back;
    String meter;
    Paytm(String meter){
        this.meter=meter;
        setSize(800,600);
        setLocation(400,150);
        setVisible(true);
        
        JEditorPane j=new JEditorPane();
        j.setEditable(false);
        
        try{
            j.setPage("https://paytm.com/online-payments");
            
        }catch(Exception e){
            j.setContentType("text/html");
            j.setText("<html>Could not load</html>");
        }
        
        JScrollPane sp=new JScrollPane(j);
        add(sp);
        
        back=new JButton("Back");
        back.setBounds(640,20,80,30);
        back.addActionListener(this);
        j.add(back);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        
        setVisible(false);
        new PayBill(meter);
    }
    
    public static void main(String[] args){
        new Paytm("");
    }
}
