import java.awt.*;
import java.awt.event.*;
import javax.swing.*;              
import java.awt.Cursor;
import java.awt.Toolkit;


public class java_homework 
{
	static JFrame frm=new JFrame("paper war");
	static Container cp=frm.getContentPane();
	
	
	static ImageIcon background=new ImageIcon("Background.jpg");           //­I´º
	static Image man_right = new ImageIcon("man_right.jpg").getImage();    //¤H
	
		
	static JLabel lab=new JLabel();  
		
		
    public static void main(String args[])
   {
      
       cp.setLayout(new FlowLayout());
       cp.add(lab);
       lab.setIcon(background);
   
       frm.setSize(640,480);
       frm.setVisible(true);
       
       
//§ï·Æ¹«´å¼Ð
       Cursor cr = Toolkit.getDefaultToolkit().createCustomCursor( man_right , new Point(0,0) ,"MyCursor" );
       frm.setCursor( cr );

   }
}