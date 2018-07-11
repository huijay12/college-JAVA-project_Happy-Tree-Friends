import java.awt.*;
import java.awt.event.*;
import javax.swing.*;              
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.util.*;


class mainChar
{
   private int x, y, height, width;
   public Image img;
   public Graphics g;
   
   
   public mainChar(int h, int w)
   {
      height = h;
      width = w;
      img = new ImageIcon("man_right.jpg").getImage();
      g = img.getGraphics();
      
      }
      
   public void setPos(int a, int b)
   {
      x = a;
      y = b;      
      }   
      
   
   public void drawMainChar(Graphics g)
   {
      g.drawImage(img, x,y,width,height,null);
      
      }
   
   
   }



public class trytrytry 
{
   static JFrame frm=new JFrame("paper war");
   static Container cp=frm.getContentPane();
   
   
   static ImageIcon background=new ImageIcon("Background.jpg");           //­I´º
  // static Image man_right = new ImageIcon("man_right.jpg").getImage();    //¤H
   
      
   static JLabel lab=new JLabel();  
      
      
    public static void main(String args[])
   {
 
       cp.setLayout(new FlowLayout());
       cp.add(lab);
       lab.setIcon(background);
   
       frm.setSize(640,480);
       frm.setVisible(true);
       
       
       mainChar mc = new mainChar(50, 30);                            
       int delay = 100; //milliseconds                              
       counting count = new counting();         
       Timer timer = new Timer();
       timer.schedule(new counting(), 100, delay);                       
       
       
       
       while(true)
       {
         mc.setPos(count.px, count.py);
         
         mc.drawMainChar(mc.g);
         }
       
       
       


   }
   
   
   
   
   
   
   
}