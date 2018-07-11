
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.util.*;  
public class java_game extends Frame implements Runnable,MouseMotionListener,MouseListener
{
	
	Image pur_left,red_right,yell_left,man_right,man_left,Background,bullet;

	int y_pur_left;          //pur_left的y座標
	int y_red_right;          //red_left的y座標
	
	int x_yell_left=640, y_yell_left=360; //yell_left的x,y座標
	int x=0, y=0;            //man的x,y座標
    int y_bullet;        
	
	int x_pur_left[]=new int [15];
    int x_red_right[]=new int [5];
    int x_bullet[]=new int [15];	
    	
    static Label labx=new Label();
    static Label laby=new Label();
    static Label lab=new Label(); 
    	
    int ran = (int) (10*Math.random()); //隨機取亂數
    	
	private Image doubleBuffer;
	
	public static void main(String args[])
	{
		java_game workStart=new java_game();
		
		workStart.setLayout(null);
		workStart.addMouseMotionListener(workStart); 
        workStart.addMouseListener(workStart); 
		
		labx.setBounds(40,450,40,30);
        laby.setBounds(100,450,40,30);
        lab.setBounds(200,440,100,40);
      
        workStart.add(labx);
        workStart.add(laby);
        workStart.add(lab);

	}

	
	public java_game()
	{
		super("java_game");	
		setSize(644,481);
		setLocation(250,150);
 
		Toolkit tk=Toolkit.getDefaultToolkit();   //匯圖
		
		pur_left=tk.getImage("pur_left.jpg");
		red_right=tk.getImage("red_right.jpg");
		yell_left=tk.getImage("yell_left.jpg");
	    man_right=tk.getImage("man_right.jpg");
	    man_left=tk.getImage("man_left.jpg");
	    bullet=tk.getImage("bullet.jpg");
		
		Background=tk.getImage("Background.jpg");
		setVisible(true);
		new Thread(this).start();
	}
		

	public void run()   //改寫
	{
		
		for(int s=0;s<15;s++)
		{
			x_pur_left[s]=680+s*(40);
		}
		
			for(int s=0;s<5;s++)
		{
			x_red_right[s]=0-s*(40);
		}
		
	
		while(true)
		{
			if(x>322)
				{
					for(int i=0 ; i<15 ; i++)
						x_bullet[i]+=(-50);
				}
			else
		   	    {
	            	for(int i=0 ; i<15 ; i++)
				    	x_bullet[i]+=(50);
            	}
            	repaint();//更新畫面
				try{Thread.sleep(100);}
				catch(InterruptedException e){;}
				
				
			for(int i=0 ; i<15 ; i++)
			{
				x_pur_left[i]+=-10;
				if(x_pur_left[i]<0)
					x_pur_left[i]=680;
			}
			
			
				for(int i=0 ; i<5 ; i++)
			{
				x_red_right[i]+=10;
				if(x_red_right[i]>680)
					x_red_right[i]=0;
			}
			
			
	        
            	
			x_yell_left = x_yell_left -10 ;
			y_yell_left = y_yell_left  ;
			
			repaint();//更新畫面
			try{Thread.sleep(50);}
			catch(InterruptedException e){;}
		}
		

		

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



   public void mouseEntered(MouseEvent e){;}	// 滑鼠的指標進入btn上方
   public void mouseClicked(MouseEvent e)       // 按下並放開滑鼠按鈕
   {
          x=e.getX();
          y=e.getY();
          x_bullet[0]=x+30;
          y_bullet=y+40;
   }
   public void mouseExited(MouseEvent e){;}     // 滑鼠的指標移開btn上方
   public void mousePressed(MouseEvent e){;}    // 按下滑鼠按鈕
   public void mouseReleased(MouseEvent e){;}   // 放開滑鼠按鈕


 // 當滑鼠移動時
	public void mouseMoved(MouseEvent e)     
   {                  
      labx.setText("x="+e.getX());           // 顯示x座標
      laby.setText("y="+e.getY());           // 顯示y座標
      x=e.getX();
      y=e.getY();
      
      lab.setText("Mouse Moved!!");          // 顯示"Mouse Moved!!"字串
   }
   
      public void mouseDragged(MouseEvent e)    // 當滑鼠拖曳時
   {  
      labx.setText("x="+e.getX());           // 顯示x座標
      laby.setText("y="+e.getY());           // 顯示y座標
      lab.setText("Mouse Dragged!!");        // 顯示"Mouse Dragged!!"字串
   } 
   

	public void paint(Graphics g)  //可能也是改寫
	  {
       
        g.drawImage(Background,0,30,644,481,this);       //(名字,x,y,寬,長,this)
        
        for(int i=0;i<15;i++)
        {	
        	if(i%2==0)
        	   y_pur_left= 0 + (int)(100*Math.random());
        	else if(i%2==1)
        	   y_pur_left= 0 + (int)(100*Math.random());	
        	   	
        	g.drawImage(pur_left,x_pur_left[i],y_pur_left,30,30,this);
        	
        }
        
       
        if( ran%2==0  ) 
        {
       	       y_red_right= 200 ;
        }
        else if( ran%3==0  )
        {     
       	       y_red_right= 300 ;
        }
     	else
        {     
       	       y_red_right= 400 ;
        }
       for(int i=0;i<5;i++)
       	       g.drawImage(red_right,x_red_right[i],y_red_right,30,30,this);
        
//中線換邊        
    if(x>322)    
       g.drawImage(man_left,x,y,60,60,this);
    else
       g.drawImage(man_right,x,y,60,60,this);
       
       
       
       g.drawImage(yell_left,x_yell_left,y_yell_left,this);


    for(int i=0;i<15;i++)
      g.drawImage(bullet,x_bullet[i],y_bullet,this);
      
      
      }

}
