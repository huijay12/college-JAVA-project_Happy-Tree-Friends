import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.util.*;  

public class objTest_3_2 extends Frame implements MouseMotionListener
{
   
   static objTest_3_2 frm = new objTest_3_2();                                   //����
   static Image background;
   static Image red_right, man_right, man_left;
   static Image doubleBuffer;
  
   public static ArrayList<man> redAll = new ArrayList<man>();               //�˩Ǫ�ArrayList
   public static mainChar mc;
   
   
   public static timerTest timerDemo = new timerTest(redAll, mc);
   
   
   public static void main(String args[])
   {
      
      
      
      Toolkit tk=Toolkit.getDefaultToolkit();                                //�׹�
      background=tk.getImage("Background.jpg");
      red_right = tk.getImage("red_right.jpg");
      man_right = tk.getImage("man_right.jpg");
      man_left = tk.getImage("man_left.jpg");
      
      
      frm.setTitle("objTest");                                              //�]����
      frm.setLayout(null);
      frm.setSize(640,480);
      frm.setLocation(250,150);
      frm.setVisible(true);
     
      
      frm.addMouseMotionListener(frm);                                 //�ƹ����ʵ��U
     
     
      mc = new mainChar();
      mc.start();
      
      timerDemo = new timerTest(redAll, mc);
       timerDemo.start();
     
    //  for(int i=0; i<10; i++)                                            //�غc�Ǫ�  ��iArrayList
   //   {
      
    //   redAll.add(new man());
    //   redAll.get(i).start();                                           //�C���Ǫ������
  //      }
     
    
     
     
     while(true)
      frm.repaint();                                                    //�H�ɵe��
     
     
     
      }
      
     
   
      public void paint(Graphics g)
     {
      
         g.drawImage(background,0,0,640,480,this);                                 // �e�I��
         
         for(int i=0; i<redAll.size(); i++)
         g.drawImage(red_right, redAll.get(i).x, redAll.get(i).y, 30, 30, this);   // �e��
         
         if(mc.x > 280)                                                            // �e���� ���k��V
         g.drawImage(man_left, mc.x, mc.y, 60, 60, this);
         else
         g.drawImage(man_right, mc.x, mc.y, 60, 60, this);
      
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
   
   
    public void mouseDragged(MouseEvent e) {}
  

   
   }
   
   
   
class man extends Thread
{
     
   int x, y;
   int a, b;
   //int detectPoint1X, detectPoint1Y, detectPoint2X, detectPoint2Y, detectPoint3X, detectPoint3Y, detectPoint4X, detectPoint4Y;
   int[][] detectTable = new int[4][3];
   mainChar c;
 
   
   public man(mainChar c1)                                                //�غc�禡
   {
      c = c1;
      
      x = (int)(100*(Math.random()-0.5));                                      
      y = 200+(int)(300*(Math.random()-0.5));
      
      for(int i=0; i<4; i++)                              //�P�_�O�_�I�쨤��: �U�����I�w�]1
          detectTable[i][2] = 1;
      
      do{
      a = (int)(5*(Math.random()));
      b = (int)(5*(Math.random()-0.5));
      }while(a<1 && b<1);
      
      
         }
   
   
   
   public void run()                             //��g
   {
    
   // System.out.println(10*(Math.random()));
      while(true)
      {    
          x += a;
          y += b;
          
          
          
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
                if((detectTable[i][0]>c.x) && (detectTable[i][0]<c.x+60) && (detectTable[i][1]>c.y) && (detectTable[i][1]<c.y+60))
                detectTable[i][2] = 0;
              }
              
            if(detectTable[0][2] * detectTable[1][2] * detectTable[2][2] * detectTable[3][2] == 0)
             System.out.println("hit!");
              
          
          try
          { Thread.sleep(50); }
         
          catch(InterruptedException e){}   
                 
           
          }
   
      }
      
   
           
 }
 
 
 
 class mainChar extends Thread
 {
   
   int x, y;
   
   
   public void run()
   {}
   
   
   }
   
   
   
class DateTask extends TimerTask 
 {
   ArrayList<man> red;
   mainChar c;
   
   public DateTask(ArrayList<man> m, mainChar cc)
   {red = m;
    c = cc;}
   
    public void run()
    {
      System.out.println("new a man");
      man a = new man(c);
      red.add(a);
      a.start();
      }
  }
  
  
class timerTest extends Thread
{
   ArrayList<man> m;
   mainChar c;
   
   public timerTest(ArrayList<man> a, mainChar c1)
   {m = a;
    c = c1;}
   
   
   public void run(){
 
         Timer timer = new Timer();
        
         timer.schedule(new DateTask(m, c), 1000, 400);
         
      
     }
   
   }