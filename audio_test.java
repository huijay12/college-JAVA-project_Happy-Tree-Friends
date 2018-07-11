import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.util.*;  
import sun.audio.*; // �� �Jsun.audio �]
import java.io.*;


public class audio_test extends Frame implements MouseMotionListener, MouseListener
{
   
   static audio_test frm = new audio_test();                                   //����
   static Image background;
   static Image red_right , red_left , pur_right , pur_left , yell_right , yell_left , man_right , man_left , bullet_pic , blood;
   static Image doubleBuffer;
  
//        ArrayList<class�W��> arraylist�ܼƦW��
   public static ArrayList<red> redAll = new ArrayList<red>();               //�˩Ǫ�ArrayList              //.....
   public static ArrayList<pur> purAll = new ArrayList<pur>();
   public static ArrayList<yell> yellAll = new ArrayList<yell>();
   
   public static ArrayList<bullet> bulletAll = new ArrayList<bullet>();
   public static mainChar mc;
   
   public static void main(String args[])
   { 
   	
   	
   	InputStream in = new FileInputStream ("Happy Tree Friends.mp3");
    AudioStream as = new AudioStream (in);
    AudioPlayer.player.start (as); 
    AudioPlayer.player.stop (as);
    
      boolean hasRemoved = false;

      Toolkit tk=Toolkit.getDefaultToolkit();                                //�׹�
      background=tk.getImage("Background.jpg");
      
      red_right = tk.getImage("red_right.jpg");
      red_left = tk.getImage("red_left.jpg");
      
      pur_right = tk.getImage("pur_right.jpg");
      pur_left = tk.getImage("pur_left.jpg");
      
      yell_right = tk.getImage("yell_right.jpg");
      yell_left = tk.getImage("yell_left.jpg");
      
      man_right = tk.getImage("man_right.jpg");
      man_left = tk.getImage("man_left.jpg");
      
      blood = tk.getImage("blood.jpg");
      
      bullet_pic = tk.getImage("bullet_pic.jpg");

      frm.setTitle("objTest");                                              //�]����
      frm.setLayout(null);
      frm.setSize(640,480);
      frm.setLocation(250,150);
      frm.setVisible(true);
     
      
      frm.addMouseMotionListener(frm);                                 //�ƹ����ʵ��U
      frm.addMouseListener(frm);
     
     
      mc = new mainChar();
      mc.start();
     
      for(int i=0; i<20; i++)                                            //�غc�Ǫ�  ��iArrayList           //.....
      {
       redAll.add(new red(bulletAll));
       redAll.get(i).start();                                           //�C���Ǫ������
      }
      
      
      for(int i=0; i<20; i++)                                            //�غc�Ǫ�  ��iArrayList           //.....
      {
       purAll.add(new pur(bulletAll));
       purAll.get(i).start();                                           //�C���Ǫ������
      }
     
     
      for(int i=0; i<20; i++)                                            //�غc�Ǫ�  ��iArrayList           //.....
      {
       yellAll.add(new yell(bulletAll));
       yellAll.get(i).start();                                           //�C���Ǫ������
      }
      
 
     
     while(true)
     {
   //    hasRemoved = false;
      
  //     for(int i=0; i<bulletAll.size(); i++) 
  //    {
      
  //    for(int j=0; j<redAll.size(); j++)
  //     {
   //      if(bulletAll.get(i).x > redAll.get(j).x && bulletAll.get(i).y > redAll.get(j).y && bulletAll.get(i).y < redAll.get(j).y+10)
  //         { 
   //          bulletAll.remove(bulletAll.get(j)); 
   //          redAll.get(i).isAlive = false;
             
           
    //        hasRemoved = true;
    //         break;
    //         }  
             
    //          }                                              
          
      //    if(hasRemoved == true)
     //       break;
          
     //     }   

  
       

      frm.repaint();                                                   //�H�ɵe��
      
       
      }
      
     }
   
      public void paint(Graphics g)
   {
      
         g.drawImage(background,0,0,640,480,this);                                 // �e�I��
         
         

         
          
         for(int i=0; i<redAll.size(); i++)                         //�Ǯ���+����+�p��                           //.....
         {
            if(redAll.get(i).isAlive == false)
           {
    //        g.drawImage(blood, redAll.get(i).x, redAll.get(i).y, 100, 100, this);     �z��gg
              redAll.remove(redAll.get(i));
              break;
           }
         }
         for(int i=0; i<redAll.size(); i++)
         g.drawImage(red_right, redAll.get(i).x, redAll.get(i).y, 30, 30, this);   // �e��                      //.....
         
         
         
 
         
         for(int i=0; i<purAll.size(); i++)                         //�Ǯ���+����+�p��                           //.....
         {
            if(purAll.get(i).isAlive == false)
           {
              purAll.remove(purAll.get(i));
              break;
              }
         } 
         for(int i=0; i<purAll.size(); i++)
         g.drawImage(pur_right, purAll.get(i).x, purAll.get(i).y, 30, 30, this);   // �e��                      //.....
         
         
         
         
         
         for(int i=0; i<yellAll.size(); i++)                         //�Ǯ���+����+�p��                           //.....
         {
            if(yellAll.get(i).isAlive == false)
           {
              yellAll.remove(yellAll.get(i));
              break;
              }
         } 
         for(int i=0; i<yellAll.size(); i++)
         g.drawImage(yell_right, yellAll.get(i).x, yellAll.get(i).y, 40, 40, this);   // �e��                      //.....
         
         
         
         
         if(mc.x > 280)                                                            // �e���� ���k��V
         g.drawImage(man_left, mc.x, mc.y, 60, 60, this);
         else
         g.drawImage(man_right, mc.x, mc.y, 60, 60, this);
         
         for(int i=0; i<bulletAll.size(); i++)                                     //�e�l�u
             g.drawImage(bullet_pic, bulletAll.get(i).x, bulletAll.get(i).y, this);
            
               
    }
  
   
   
   
   
   //�мgupdate �ϥ������w���קK�{�{
   public void update(Graphics g)
   {
      Dimension size = getSize();
      if(doubleBuffer == null ||doubleBuffer.getWidth(this)!=size.width || doubleBuffer.getHeight(this)!=size.height)
      {
         doubleBuffer = createImage(size.width, size.height);
      }
      
      if(doubleBuffer!=null)
      {
         //�yø�������w�İ�
         Graphics g2 = doubleBuffer.getGraphics();
         paint(g2);
         g2.dispose();
         //���������w�Ħܵe��
         g.drawImage(doubleBuffer, 0, 0, null);
      }
      else
      {
         //�L�k�إ������w�ġA�����yø�ܵe��
         paint(g);
      }
   }
  
  
  
  
  
  
    public void mouseMoved(MouseEvent e)     
   {                  
      mc.x=e.getX()-40;
      mc.y=e.getY()-40;
   }
   
   public void mousePressed(MouseEvent e)
    {
      bulletAll.add(new bullet(e.getX(), e.getY()));
      bulletAll.get(bulletAll.size()-1).start();
      }
       
    public void mouseDragged(MouseEvent e)
    {
       mc.x=e.getX()-40;
      mc.y=e.getY()-40;
       }
       
     public void mouseClicked(MouseEvent e)
     {
     // redAll.get(0).isAlive = false;
      }
      
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}    
    public void mouseReleased(MouseEvent e){}    
   }
   
   
   
   
   
   
   
   
class red extends Thread                                        //���Ǫ�class
{
     
   int x, y;
   Boolean isAlive;
   ArrayList<bullet> b;
 
   
   public red(ArrayList<bullet> b1)                            //�غc�禡
   {
      b = b1;
      x = 200+(int)(100*(Math.random()-0.5));                                      
      y = 200+(int)(300*(Math.random()-0.5));
      isAlive = true;
    }
   
   public void run()                           
   {
         
      while(true)
      {    
          x += 2;
         
          try
          { Thread.sleep(50); }
         
          catch(InterruptedException e){}   
                 
                 
                 
         for(int i=0; i<b.size(); i++)
         {
            if(b.get(i).x > x && b.get(i).y > y && b.get(i).y < y+30)
            isAlive = false;
            
            
         }     
      }
   
   }
          
 }
 
 
 
 
 
 
 
 class pur extends Thread                                        //���Ǫ�class
{
     
   int x, y;
   Boolean isAlive;
   ArrayList<bullet> b;
 
   
   public pur(ArrayList<bullet> b1)                            //�غc�禡
   {
      b = b1;
      x = 100+(int)(100*(Math.random()-0.5));                                      
      y = 200+(int)(300*(Math.random()-0.5));
      isAlive = true;
    }
   
   public void run()                           
   {
         
      while(true)
      {    
          x += 2;
         
          try
          { Thread.sleep(50); }
         
          catch(InterruptedException e){}   
                 
                 
                 
         for(int i=0; i<b.size(); i++)
         {
            if(b.get(i).x > x && b.get(i).y > y && b.get(i).y < y+30)
            isAlive = false;
            
            
         }     
      }
   
   }
          
 }
 
 
 
 
 
 class yell extends Thread                                        //���Ǫ�class
{
     
   int x, y;
   Boolean isAlive;
   ArrayList<bullet> b;
 
   
   public yell(ArrayList<bullet> b1)                            //�غc�禡
   {
      b = b1;
      x = 0+(int)(100*(Math.random()-0.5));                                      
      y = 200+(int)(300*(Math.random()-0.5));
      isAlive = true;
    }
   
   public void run()                           
   {
         
      while(true)
      {    
          x += 2;
         
          try
          { Thread.sleep(50); }
         
          catch(InterruptedException e){}   
                 
                 
                 
         for(int i=0; i<b.size(); i++)
         {
            if(b.get(i).x > x && b.get(i).y > y && b.get(i).y < y+30)
            isAlive = false;
            
            
         }     
      }
   
   }
          
 }
 
 
 
 
 
 
 class mainChar extends Thread                                    //���⪺class
 {
   
   int x, y;
   
   
   public void run()
   {}
   
   
   }
   
   
   
   
   
 class bullet extends Thread                                         //�l�u��class
 {
   int x, y;
   static int speed=15;
   Boolean LorR; 
   
   public bullet(int a, int b)       //�غc�禡
   { 
      
      
      if(a > 320)                  
      {
       LorR = false;              //  false�O���ⰾ�k��
       x = a - 40;
       y=b;
         }
      
      else                         //  true�O���ⰾ����
      {
       LorR = true;
       x = a;
       y = b;
        }
     
      
        }
   
   
   public void run()
   {
      while(true)
      {
         if(LorR == true)
         x += speed;
         else
         x -= speed;
         
         
         
         
         try
          { Thread.sleep(50); }
         
          catch(InterruptedException e){}
         
        }
     }
   
   
   
   
   }