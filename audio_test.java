import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.util.*;  
import sun.audio.*; // 引 入sun.audio 包
import java.io.*;


public class audio_test extends Frame implements MouseMotionListener, MouseListener
{
   
   static audio_test frm = new audio_test();                                   //視窗
   static Image background;
   static Image red_right , red_left , pur_right , pur_left , yell_right , yell_left , man_right , man_left , bullet_pic , blood;
   static Image doubleBuffer;
  
//        ArrayList<class名稱> arraylist變數名稱
   public static ArrayList<red> redAll = new ArrayList<red>();               //裝怪的ArrayList              //.....
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

      Toolkit tk=Toolkit.getDefaultToolkit();                                //匯圖
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

      frm.setTitle("objTest");                                              //設視窗
      frm.setLayout(null);
      frm.setSize(640,480);
      frm.setLocation(250,150);
      frm.setVisible(true);
     
      
      frm.addMouseMotionListener(frm);                                 //滑鼠移動註冊
      frm.addMouseListener(frm);
     
     
      mc = new mainChar();
      mc.start();
     
      for(int i=0; i<20; i++)                                            //建構怪物  丟進ArrayList           //.....
      {
       redAll.add(new red(bulletAll));
       redAll.get(i).start();                                           //每隻怪的執行緒
      }
      
      
      for(int i=0; i<20; i++)                                            //建構怪物  丟進ArrayList           //.....
      {
       purAll.add(new pur(bulletAll));
       purAll.get(i).start();                                           //每隻怪的執行緒
      }
     
     
      for(int i=0; i<20; i++)                                            //建構怪物  丟進ArrayList           //.....
      {
       yellAll.add(new yell(bulletAll));
       yellAll.get(i).start();                                           //每隻怪的執行緒
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

  
       

      frm.repaint();                                                   //隨時畫圖
      
       
      }
      
     }
   
      public void paint(Graphics g)
   {
      
         g.drawImage(background,0,0,640,480,this);                                 // 畫背景
         
         

         
          
         for(int i=0; i<redAll.size(); i++)                         //怪消失+音效+計分                           //.....
         {
            if(redAll.get(i).isAlive == false)
           {
    //        g.drawImage(blood, redAll.get(i).x, redAll.get(i).y, 100, 100, this);     爆血gg
              redAll.remove(redAll.get(i));
              break;
           }
         }
         for(int i=0; i<redAll.size(); i++)
         g.drawImage(red_right, redAll.get(i).x, redAll.get(i).y, 30, 30, this);   // 畫怪                      //.....
         
         
         
 
         
         for(int i=0; i<purAll.size(); i++)                         //怪消失+音效+計分                           //.....
         {
            if(purAll.get(i).isAlive == false)
           {
              purAll.remove(purAll.get(i));
              break;
              }
         } 
         for(int i=0; i<purAll.size(); i++)
         g.drawImage(pur_right, purAll.get(i).x, purAll.get(i).y, 30, 30, this);   // 畫怪                      //.....
         
         
         
         
         
         for(int i=0; i<yellAll.size(); i++)                         //怪消失+音效+計分                           //.....
         {
            if(yellAll.get(i).isAlive == false)
           {
              yellAll.remove(yellAll.get(i));
              break;
              }
         } 
         for(int i=0; i<yellAll.size(); i++)
         g.drawImage(yell_right, yellAll.get(i).x, yellAll.get(i).y, 40, 40, this);   // 畫怪                      //.....
         
         
         
         
         if(mc.x > 280)                                                            // 畫角色 左右轉向
         g.drawImage(man_left, mc.x, mc.y, 60, 60, this);
         else
         g.drawImage(man_right, mc.x, mc.y, 60, 60, this);
         
         for(int i=0; i<bulletAll.size(); i++)                                     //畫子彈
             g.drawImage(bullet_pic, bulletAll.get(i).x, bulletAll.get(i).y, this);
            
               
    }
  
   
   
   
   
   //覆寫update 使用雙倍緩衝避免閃爍
   public void update(Graphics g)
   {
      Dimension size = getSize();
      if(doubleBuffer == null ||doubleBuffer.getWidth(this)!=size.width || doubleBuffer.getHeight(this)!=size.height)
      {
         doubleBuffer = createImage(size.width, size.height);
      }
      
      if(doubleBuffer!=null)
      {
         //描繪到雙倍緩衝區
         Graphics g2 = doubleBuffer.getGraphics();
         paint(g2);
         g2.dispose();
         //拷貝雙倍緩衝至畫面
         g.drawImage(doubleBuffer, 0, 0, null);
      }
      else
      {
         //無法建立雙倍緩衝，直接描繪至畫面
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
   
   
   
   
   
   
   
   
class red extends Thread                                        //紅怪的class
{
     
   int x, y;
   Boolean isAlive;
   ArrayList<bullet> b;
 
   
   public red(ArrayList<bullet> b1)                            //建構函式
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
 
 
 
 
 
 
 
 class pur extends Thread                                        //紫怪的class
{
     
   int x, y;
   Boolean isAlive;
   ArrayList<bullet> b;
 
   
   public pur(ArrayList<bullet> b1)                            //建構函式
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
 
 
 
 
 
 class yell extends Thread                                        //紫怪的class
{
     
   int x, y;
   Boolean isAlive;
   ArrayList<bullet> b;
 
   
   public yell(ArrayList<bullet> b1)                            //建構函式
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
 
 
 
 
 
 
 class mainChar extends Thread                                    //角色的class
 {
   
   int x, y;
   
   
   public void run()
   {}
   
   
   }
   
   
   
   
   
 class bullet extends Thread                                         //子彈的class
 {
   int x, y;
   static int speed=15;
   Boolean LorR; 
   
   public bullet(int a, int b)       //建構函式
   { 
      
      
      if(a > 320)                  
      {
       LorR = false;              //  false是角色偏右邊
       x = a - 40;
       y=b;
         }
      
      else                         //  true是角色偏左邊
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