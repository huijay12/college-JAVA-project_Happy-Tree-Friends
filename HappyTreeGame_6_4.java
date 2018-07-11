import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.util.*;
import javax.sound.sampled.*;
import java.io.*;

public class HappyTreeGame_6_4 extends Frame implements MouseMotionListener, MouseListener, ActionListener
{
   
   //�I��
public static void playSound()
{
     try
 {
	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Happy.wav").getAbsoluteFile());
	Clip clip = AudioSystem.getClip();
	clip.open(audioInputStream);
	clip.start();
	//System.out.println("sound.");
}
	catch(Exception ex)
{
	System.out.println("Error with playing sound.");
	ex.printStackTrace();
}
}
   
   
   //�j�n
   
   
   
   public static void playSound1()
{
	try
	{
	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("gun.wav").getAbsoluteFile());
	Clip clip = AudioSystem.getClip();
	clip.open(audioInputStream);
	clip.start();
	//System.out.println("sound.");
}
	catch(Exception ex)
	{
	System.out.println("Error with playing sound.");
	ex.printStackTrace();
}
}
   
   //�y�s
   public static void playSound2()
{
	try
	{
	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("scream.wav").getAbsoluteFile());
	Clip clip = AudioSystem.getClip();
	clip.open(audioInputStream);
	clip.start();
//	System.out.println("sound.");
}
	catch(Exception ex)
{
	System.out.println("Error with playing sound.");
	ex.printStackTrace();
}
}
   
   
   static HappyTreeGame_6_4 frm = new HappyTreeGame_6_4();                                   //����
   static Image background;  //�C���I����
   static Image background1; //�}�l��
   static Image gameover; //������
   static Image win; //���\��
   static Image red_right , red_left , pur_right , pur_left , yell_right , yell_left , shoot_right , shoot_left ,scream_right , scream_left , bullet_pic , blood, heart;
   static Image doubleBuffer;
   static int heart1[]=new int[5];
   static Button btn = new Button("  Start ");   // �}�l���s
   static int now=-1;
   
   String str="Score: ";
   int score=0;            //�p��
    String str1="Life: ";        
        
        
//        ArrayList<class�W��> arraylist�ܼƦW��
   public static ArrayList<red> redAll = new ArrayList<red>();               //�˩Ǫ�ArrayList              //.....
   public static ArrayList<pur> purAll = new ArrayList<pur>();
   public static ArrayList<yell> yellAll = new ArrayList<yell>();
   
   public static ArrayList<bullet> bulletAll = new ArrayList<bullet>();
   public static mainChar mc;
   
   public static timer timer1;
   
   
   
   public static void main(String args[])
   {
   	       
     playSound();

      Toolkit tk=Toolkit.getDefaultToolkit();                                //�׹�
      background=tk.getImage("Background.jpg");
      background1=tk.getImage("Background_1.jpg");
      gameover=tk.getImage("gameover.jpg");
      blood = tk.getImage("blood.jpg");
      heart = tk.getImage("heart.jpg");
       bullet_pic = tk.getImage("bullet_pic.jpg");
          win=tk.getImage("win.jpg");
      
      red_right = tk.getImage("red_right.jpg");
      red_left = tk.getImage("red_left.jpg");
      
      pur_right = tk.getImage("pur_right.jpg");
      pur_left = tk.getImage("pur_left.jpg");
      
      yell_right = tk.getImage("yell_right.jpg");
      yell_left = tk.getImage("yell_left.jpg");
      
      shoot_right = tk.getImage("p1.jpg");
      shoot_left = tk.getImage("p2.jpg");
      scream_right = tk.getImage("p3.jpg");
      scream_left = tk.getImage("p4.jpg");
      
      
     

      frm.setTitle("HappyTreeGame");                                              //�]����
      frm.setLayout(null);
      frm.setSize(640,480);
      frm.setLocation(250,150);
      frm.setVisible(true);
     
      
      frm.addMouseMotionListener(frm);                                 //�ƹ����ʵ��U
      frm.addMouseListener(frm);
     
     
      mc = new mainChar();
      
      timer1 = new timer(redAll, purAll, yellAll, bulletAll, mc);
     
     
      btn.setBounds(440, 420, 100, 35);
   	  btn.addActionListener(frm);
	  frm.add(btn);
	  

     
     while(true)
     {

       if(mc.isAlive == false)
        {
         System.out.println("Flippy die!!");
         break;
         }
      

      frm.repaint();                                                   //�H�ɵe��
    
    
      }
      
     }
   

   
      public void paint(Graphics g)
   {
        
     if(now>0   &&   mc.life >0   &&  score < 1500) 
     {
        
      
     	g.drawImage(background,0,0,640,480,this);                                 // �e�I��
     	
//�e�o��
        g.setColor(Color.blue);                                             
        g.setFont(new Font("DIALOG", Font.BOLD, 20));
        g.drawString(str + score ,100,60);
        
 //�e�ͩR

        g.setColor(Color.blue);                                             
        g.setFont(new Font("DIALOG", Font.BOLD, 20));
        
       for(int i=0; i<5 ; i++)
      	 heart1[i] = 350+i*30;
      	
        g.drawString(str1 ,300,60) ;
      for(int i=0 ; i<mc.life ; i++)
        g.drawImage(heart,heart1[i],40,30,30,this);

        
         for(int i=0; i<redAll.size(); i++)                         //�Ǯ���+����                            //.....
         {
            
            if(redAll.get(i).isAlive == false)
           {
              redAll.remove(redAll.get(i));
               score=score+10;                                    //�o��(+10)
              break;
           }
         }
         
         for(int i=0; i<redAll.size(); i++)
          {
            if(redAll.get(i).beKilled == true)
              g.drawImage(blood, redAll.get(i).x, redAll.get(i).y, 70, 50, this);
            else if(redAll.get(i).c > 0)
               g.drawImage(red_right, redAll.get(i).x, redAll.get(i).y, 30, 30, this);   // �e��                      //.....
            else
               g.drawImage(red_left, redAll.get(i).x, redAll.get(i).y, 30, 30, this);
             } 
         
        
          for(int i=0; i<yellAll.size(); i++)
          {
            if(yellAll.get(i).beKilled == true)
              g.drawImage(blood, yellAll.get(i).x, yellAll.get(i).y, 70, 50, this);
            else if(yellAll.get(i).c > 0)
               g.drawImage(yell_right, yellAll.get(i).x, yellAll.get(i).y, 40, 40, this);   // �e��                      //.....
            else
               g.drawImage(yell_left, yellAll.get(i).x, yellAll.get(i).y, 40, 40, this);
            } 
     
 
         
         for(int i=0; i<purAll.size(); i++)                         //�Ǯ���+����                            //.....
         {
            if(purAll.get(i).isAlive == false)
           {
              purAll.remove(purAll.get(i));
               score=score+10;                                        //�o��(+10)
              break;
              }
         } 
         
         
          for(int i=0; i<purAll.size(); i++)
          {
            if(purAll.get(i).beKilled == true)
              g.drawImage(blood, purAll.get(i).x, purAll.get(i).y, 70, 50, this);
            else if(purAll.get(i).c >0)
               g.drawImage(pur_right, purAll.get(i).x, purAll.get(i).y, 30, 30, this);   // �e��                      //.....
            else
               g.drawImage(pur_left, purAll.get(i).x, purAll.get(i).y, 30, 30, this);
             } 
         
         
         
         
         for(int i=0; i<yellAll.size(); i++)                         //�Ǯ���+����                             //.....
         {
            if(yellAll.get(i).isAlive == false)
           {
              yellAll.remove(yellAll.get(i));
               score=score+10;                                        //�o��(+10)
              break;
              }
         } 
        
  
         
         for(int i=0; i<bulletAll.size(); i++)                         //�l�u����                           //.....
         {
            if(bulletAll.get(i).isAlive == false)
           {
              bulletAll.remove(bulletAll.get(i));
             playSound2();
              break;
              }
         } 
         	
         	
        
         for(int i=0; i<bulletAll.size(); i++)                                     //�e�l�u
             g.drawImage(bullet_pic, bulletAll.get(i).x, bulletAll.get(i).y, this);
            
            
            
         if(mc.x > 280 && mc.getHurt == false)                                              // �e���� ���k��V
         g.drawImage(shoot_left, mc.x, mc.y, 60, 60, this);
         else if(mc.x > 280 && mc.getHurt == true)              
         g.drawImage(scream_left, mc.x, mc.y, 60, 60, this);
         else if(mc.x < 280 && mc.getHurt == false)              
         g.drawImage(shoot_right, mc.x, mc.y, 60, 60, this);
         else
         g.drawImage(scream_right, mc.x, mc.y, 60, 60, this);   
            
            
            
         }
     else if( score == 1500  )
      {
      	 g.drawImage(win,0,0,640,480,this);
      	 g.setColor(Color.red);                                             
         g.setFont(new Font("DIALOG", Font.BOLD, 48));
      	 g.drawString("You got  "+ score + "  point" ,20,420);
      }
        
     else if( mc.life <= 0 )
         g.drawImage(gameover,0,0,640,480,this);
     else
     	g.drawImage(background1,0,0,640,480,this);        //�}�l��
            
            
            
               
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
       playSound1();
      }
       
    public void mouseDragged(MouseEvent e)
    {
       mc.x=e.getX()-40;
       mc.y=e.getY()-40;
      }
       
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}    
    public void mouseReleased(MouseEvent e){}    
    	
    	
    	
    	
    	
     public void actionPerformed(ActionEvent e) 
   	{
   		    Button btn1=(Button) e.getSource();
   			if (now < 0 && btn1 == btn) {// ���U���s
			now = 1;
			timer1.start();
			mc.start();
			btn.setVisible(false);
		}
   	}	
    	
}
   
   
   
  
   
   
   
   
class red extends Thread                                        //���Ǫ�class
{
     
   int x, y, c, d;
   int[][] detectTable = new int[4][3];                     // 4�ӸI�������I
   Boolean isAlive, beKilled;
   ArrayList<bullet> b;
   mainChar c0;
   
   
   public red(ArrayList<bullet> b1, mainChar c1)                            //�غc�禡
   {
      b = b1;
      c0 = c1;
      x = ((int)(100*Math.random()) % 2 ) * 650;
      y = ((int)(100*Math.random()) % 5 ) * 100;
      
      for(int i=0; i<4; i++)                              //�P�_�O�_�I�쨤��: �U�����I�w�]1
         detectTable[i][2] = 1;
      
  //    do{
  //    c = (int)(5*(Math.random()-0.5)) * 3;
  //    d = (int)(5*(Math.random()-0.5)) * 3;
   //   }while((c<1&&c>-1)||(d<1&&d>-1));
      
      isAlive = true;
      beKilled = false;

    }
   
   public void run()                           
   {
         
      while(true)
      {    
         
         c = (c0.x-x)/20;
         d = (c0.y-y)/20;
      
      
          x += c;
          y += d;
          
          
          detectTable[0][0] = x;
          detectTable[0][1] = y;
          detectTable[1][0] = x + 30;
          detectTable[1][1] = y;
          detectTable[2][0] = x;
          detectTable[2][1] = y + 30;
          detectTable[3][0] = x + 30;
          detectTable[3][1] = y + 30;
          
          
            for(int i=0; i<4; i++)
              {
                if((detectTable[i][0]>c0.x+15) && (detectTable[i][0]<c0.x+35) && (detectTable[i][1]>c0.y+10) && (detectTable[i][1]<c0.y+35))
                detectTable[i][2] = 0;
              }
              
            if(detectTable[0][2] * detectTable[1][2] * detectTable[2][2] * detectTable[3][2] == 0)
             { //isAlive = false;
               beKilled = true;
               c0.life--;
               c0.getHurt = true;
               System.out.println("Flippy's lif = " + c0.life );
               }                    
          
         
         
         if(x>700 || x<-50)
          c = c*-1;
          
          
          if(y>650 || y<-80)
          d = d*-1; 
         
         
          try
          { Thread.sleep(50); }
         
          catch(InterruptedException e){}   
            
                 
         for(int i=0; i<b.size(); i++)
         {
            if(b.get(i).x > x && b.get(i).x < x+30 && b.get(i).y > y && b.get(i).y < y+30 )
              {
               //isAlive = false;
               beKilled = true;
               b.get(i).isAlive = false;
               }
          
         } 
         
         
         if(beKilled == true)
         {
             try
          { Thread.sleep(2000); }
         
          catch(InterruptedException e){}   
           isAlive = false;
            
            }
         
         
         
         if(isAlive == false)
         {
            x = 10000;
            y = 10000;
            break;
            }
         
             
      }
   
   }
          
 }
 
 
 
 
 
 
 
 class pur extends Thread                                        //���Ǫ�class
{
   
   int x, y, c, d;
   int[][] detectTable = new int[4][3];                     // 4�ӸI�������I
   Boolean isAlive, beKilled;
   ArrayList<bullet> b;
   mainChar c0;
 
   
   public pur(ArrayList<bullet> b1, mainChar c1)                            //�غc�禡
   {
      b = b1;
      c0 = c1;
      x = ((int)(100*Math.random()) % 2 ) * 650;
      y = ((int)(100*Math.random()) % 5 ) * 100;
      
       for(int i=0; i<4; i++)                              //�P�_�O�_�I�쨤��: �U�����I�w�]1
          detectTable[i][2] = 1;
      
      do{
      c = (int)(5*(Math.random()-0.5)) * 3;
      d = (int)(5*(Math.random()-0.5)) * 3;
      }while((c<1&&c>-1)||(d<1&&d>-1));
      
      isAlive = true;
      beKilled = false;
    }
   
   public void run()                           
   {
         
      while(true)
      {    
          x += c;
          y += d;
          
          
          detectTable[0][0] = x;
          detectTable[0][1] = y;
          detectTable[1][0] = x + 30;
          detectTable[1][1] = y;
          detectTable[2][0] = x;
          detectTable[2][1] = y + 30;
          detectTable[3][0] = x + 30;
          detectTable[3][1] = y + 30;
          
          
            for(int i=0; i<4; i++)
              {
                if((detectTable[i][0]>c0.x+15) && (detectTable[i][0]<c0.x+35) && (detectTable[i][1]>c0.y+10) && (detectTable[i][1]<c0.y+35))
                detectTable[i][2] = 0;
              }
              
            if(detectTable[0][2] * detectTable[1][2] * detectTable[2][2] * detectTable[3][2] == 0)
             { //isAlive = false;
               beKilled = true;
               c0.life--;
               c0.getHurt = true;
               System.out.println("Flippy's lif = " + c0.life );
               }                    
          
          
           if(x>700 || x<-80)
          c = c*-1;
          
          
          if(y>550 || y<-60)
          d = d*-1; 
  
         
          try
          { Thread.sleep(50); }
         
          catch(InterruptedException e){}   
                 
                 
                 
         for(int i=0; i<b.size(); i++)
         {
            if(b.get(i).x > x && b.get(i).x < x+30 && b.get(i).y > y && b.get(i).y < y+30 )
              {
               //isAlive = false;
               beKilled = true;
               b.get(i).isAlive = false;
               }
               
         }    
         
         
           if(beKilled == true)
         {
             try
          { Thread.sleep(2000); }
         
          catch(InterruptedException e){}   
           isAlive = false;
            
            }
         
          
         if(isAlive == false)
         {
            x = 10000;
            y = 10000;
            break;
            }
          
      }
   
   }
          
 }
 
 
 
 
 
 class yell extends Thread                                        //���Ǫ�class
{
   
   int x, y, c, d;
   int[][] detectTable = new int[4][3];                     // 4�ӸI�������I
   Boolean isAlive, beKilled;
   ArrayList<bullet> b;
   mainChar c0;
   
   public yell(ArrayList<bullet> b1, mainChar c1)                            //�غc�禡
   {
      b = b1;
      c0 = c1;
      x = ((int)(100*Math.random()) % 6 ) * 100;
      y = ((int)(100*Math.random()) % 2 ) * 490;
      
       for(int i=0; i<4; i++)                              //�P�_�O�_�I�쨤��: �U�����I�w�]1
          detectTable[i][2] = 1;
      
      do{
      c = (int)(5*(Math.random()-0.5)) * 3;
      d = (int)(5*(Math.random()-0.5)) * 3;
      }while((c<1&&c>-1)||(d<1&&d>-1));
      
      isAlive = true;
      beKilled = false;

    }
   
   
   public void run()                           
   {
         
      while(true)
      {    
          x += c;
          y += d;
          
          
          detectTable[0][0] = x;
          detectTable[0][1] = y;
          detectTable[1][0] = x + 30;
          detectTable[1][1] = y;
          detectTable[2][0] = x;
          detectTable[2][1] = y + 30;
          detectTable[3][0] = x + 30;
          detectTable[3][1] = y + 30;
          
          
            for(int i=0; i<4; i++)
              {
                if((detectTable[i][0]>c0.x+15) && (detectTable[i][0]<c0.x+35) && (detectTable[i][1]>c0.y+10) && (detectTable[i][1]<c0.y+35))
                detectTable[i][2] = 0;
              }
              
            if(detectTable[0][2] * detectTable[1][2] * detectTable[2][2] * detectTable[3][2] == 0)
             { //isAlive = false;
               beKilled = true;
               c0.life--;
               c0.getHurt = true;
               System.out.println("Flippy's lif = " + c0.life );
               }                                                           //�A�[���⦩��
          
          
          
          if(x>700 || x<-50)
          c = c*-1;
          
          
          if(y>550 || y<-80)
          d = d*-1; 

         
          try
          { Thread.sleep(50); }
         
          catch(InterruptedException e){}   
                 
                 
                 
         for(int i=0; i<b.size(); i++)
         {
            if(b.get(i).x > x && b.get(i).x < x+30 && b.get(i).y > y && b.get(i).y < y+30 )
              {
              
               //isAlive = false;
               beKilled = true;
               b.get(i).isAlive = false;
               }

         }  
         
         
           if(beKilled == true)
         {
             try
          { Thread.sleep(2000); }
         
          catch(InterruptedException e){}   
           isAlive = false;
            
            }
         
         
         
         
           if(isAlive == false)
         {
            x = 10000;
            y = 10000;
            break;
            } 
      }
   
   }
          
 }
 
 
 
 
 
 
 class mainChar extends Thread                                    //���⪺class
 {
   Boolean isAlive, getHurt;
   int x, y, life;
   
   public mainChar()
   {life = 5;
    isAlive = true;
    getHurt = false;}
   
   public void run()
   {
      while(true)
      {
         
         if(getHurt == true)
          {
             try
             { Thread.sleep(1000); }
         
             catch(InterruptedException e){}
            getHurt = false;
            }
         
         
         
         if(life == 0)
          isAlive = false;
         }
      }
   
   
   }
   
   
   
   
   
 class bullet extends Thread                                         //�l�u��class
 {
   int x, y;
   static int speed=20;
   Boolean LorR, isAlive; 
   
   public bullet(int a, int b)       //�غc�禡(mc��X����, mc��Y����)
   { 
      isAlive = true;
      
      if(a > 320)                  
      {
       LorR = false;              
       x = a - 35;               //  false�O���ⰾ�k��
       y=b-10; 
         }
      
      else                         //  true�O���ⰾ����
      {
       LorR = true;
       x = a;
       y = b-10;
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
   
   
   


class earnMon extends TimerTask 
 {
   
   mainChar c1;
   ArrayList<red> rr;
   ArrayList<pur> pp;
   ArrayList<yell> yy;
   ArrayList<bullet> bb;
   
   public earnMon(ArrayList<red> i, ArrayList<pur> j, ArrayList<yell> k, ArrayList<bullet> l, mainChar c0)
   {rr = i;
    pp = j;
    yy = k;
    bb = l;
    c1 = c0;
   }
   
    public void run()
    {
     // System.out.println("new a mons");
      
      red a = new red(bb, c1);
      rr.add(a);
      a.start();
      
      pur b = new pur(bb, c1);
      pp.add(b);
      b.start();
      
      yell c = new yell(bb, c1);
      yy.add(c);
      c.start();
      
      
    
      
      }
  }
  
  
class timer extends Thread
{
   ArrayList<red> redA;
   ArrayList<pur> purA;
   ArrayList<yell> yellA;
   ArrayList<bullet> bulletA;
   mainChar mc;
   int i = 0;
   
   public timer(ArrayList<red> a, ArrayList<pur> b, ArrayList<yell> c, ArrayList<bullet> d, mainChar cc)
   {redA = a;
    purA = b;
    yellA = c;
    bulletA = d;
    mc = cc; }
   
   
   public void run(){
 
         Timer t = new Timer();
        
        while(true)
        {
         //System.out.println("ya");
         t.schedule(new earnMon(redA, purA, yellA, bulletA, mc), 10);
       
         i++;
         //System.out.println("i = " + i);
         
         
         try
         {Thread.sleep(1000);}
         
         catch(InterruptedException e){}
         
         
         
         if(i == 5)
         {
          t.cancel();
          break;
          }
        
        }
    
     }
   
   }