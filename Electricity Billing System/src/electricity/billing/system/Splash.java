package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {
    
    Thread t;
    // creating constructor for Splash class
    Splash() {
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/elect.jpg"));
        // we can set any image on frame by using ImageIcon class
        // we use ImageIcon class to set the background image on the frame by taking the image in ImageIcon class's object
        // we use ClassLoader class to take the image from the system. If the image is not in our system or we are using online image then we can directly use the url without using ClassLoader class
        // getSystemResource() is a static function in ClassLoader class
        Image i2=i1.getImage().getScaledInstance(730, 550, Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        add(image);             // we use add function to place the image on frame but we can not directly use image object in add() so we use another class object
        setVisible(true);       // by default visibility of a frame is hidden and setVisible(true) changes visibility frome hidden to show
        
        for(int i=0,x=0;i<600;i++, x+=1){
        
            setSize(i+x/4,i);   // setSize() will create a frame of required size
            setLocation(700-i/2, 400-(i/2)); // default location of a frame is top-left corner  by setLocation(left,top) we can change its location
            
            try{
                Thread.sleep(2);          // here argument is in milli-seconds
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        t=new Thread(this);
        t.start();
        
        
    }
    
    @Override
    public void run(){
        
        try{
            Thread.sleep(5000);
            setVisible(false);
            new Login();
//            new Signup();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        new Splash();
    }
}
