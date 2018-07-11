//package flower;

import java.awt.*;
import java.awt.event.*;

import javax.swing.Timer;

public class Flower extends Frame implements KeyListener, ActionListener {
	private static final long serialVersionUID = 1L;
	int timerflag = 0;
	int px = 245, py = 230;// player位置變數
	boolean rol = false, c1rol = false, c2rol = true;// 人物面向(0=right,1=left)
	Image startbg = getToolkit().getImage("start_page.jpg");// 開始畫面
	static Button btn = new Button("Start!");// 開始按鈕
	Image pr = getToolkit().getImage("playerright.png");// 圖片路徑 player面向右(19*45)
	Image pl = getToolkit().getImage("playerleft.png");// player面向左
	Image pb = getToolkit().getImage("playerback.png");// player的地毯(65*20)
	Image prr1 = getToolkit().getImage("prr1.png");
	Image prr2 = getToolkit().getImage("prr2.png");
	Image prl1 = getToolkit().getImage("prl1.png");
	Image prl2 = getToolkit().getImage("prl2.png");
	Image c1r = getToolkit().getImage("com1right.png");// computer1
	Image c1l = getToolkit().getImage("com1left.png");
	Image c1b = getToolkit().getImage("com1back.png");
	Image c1sc1 = getToolkit().getImage("c1scold1.png");// c1責罵
	Image c1sc2 = getToolkit().getImage("c1scold2.png");
	Image c2r = getToolkit().getImage("com2right.png");// computer1
	Image c2l = getToolkit().getImage("com2left.png");
	Image c2b = getToolkit().getImage("com2back.png");
	Image c2sc1 = getToolkit().getImage("c2scold1.png");// c1責罵
	Image c2sc2 = getToolkit().getImage("c2scold2.png");
	Image poo = getToolkit().getImage("poo.png");
	Image sl1 = getToolkit().getImage("sleep1.png");// 被poo砸到睡覺,有Z和沒Z字
	Image sl2 = getToolkit().getImage("sleep2.png");
	Image sor = getToolkit().getImage("sorry.png");// player sorry
	Image hap = getToolkit().getImage("happy.png");// player happy
	Image plp = getToolkit().getImage("plpush.png");// player推左邊
	Image prp = getToolkit().getImage("prpush.png");// player推左邊
	int pbx = px - 20, pby = py + 35;// player的地毯原始位置設為 與人物起始位置的相對位置
	int pbw = 95;// player地毯寬度變數
	int c1x = 90, c1y = 230;// computer1人物原始位置
	int c1bx = c1x - 20, c1by = c1y + 35;// com1地毯
	int c1bw = 150;// com1地毯寬
	int c2x = 345, c2y = 230;// computer2人物原始位置
	int c2bx = c2x - 20, c2by = c2y + 35;// com2地毯
	int c2bw = 150;// com2地毯寬
	static Flower frm = new Flower();// 視窗變數
	static TextField txf = new TextField(18);// 讀取鍵盤用
	static Timer timer2 = new Timer(100, null);
	static Timer timer = new Timer(500, null);
	int s = -1;// 時間條長度
	int t = 41, pox = 0, poy = 0;// poo 顯示時機 t%40==0,poo x,y坐標
	int sleep = 0, sorry = 0;// 被大便砸到,偷推被看到的延遲
	int push = 0;// 推地毯動作

	Image imgB;
	Graphics gB;
	Graphics g;

	public static void main(String[] args) {
		frm.setTitle("賞花");
		frm.setSize(560, 400);// 視窗大小
		frm.setLocation(200, 200);// 位置
		frm.setLayout(null);
		frm.addWindowListener(new WindowAdapter() {// 使關閉視窗按鍵有效
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		btn.setBounds(390, 290, 70, 70);
		btn.addActionListener(frm);

		frm.add(btn);
		frm.add(txf);
		frm.setBackground(Color.pink);
		frm.setVisible(true);
	}

	boolean run1or2 = false;// 1=false 2=true

	public void paint(Graphics g) {
		if (s >= 120)// 遊戲結束時清除畫面上的東西並顯示time's up!
		{
			timer.removeActionListener(frm);
			timer2.removeActionListener(frm);
			txf.removeKeyListener(frm);
			imgB = createImage(getWidth(), getHeight());// 建立imgB圖形物件
			g.drawImage(imgB, 0, 0, this);// 清除繪圖區
			g.setFont(new Font("Arial", Font.ITALIC, 60));// 字體
			g.drawString("time's up!", 150, 200);// 顯示time's up!
			g.setFont(new Font("Arial", Font.ITALIC, 25));// 字體
			g.drawString("score=" + pbw * 100 / 255, 200, 250);// 顯示分數
			if (pbw * 100 / 255 > 60)// 超過60顯示兩個人happy
			{
				g.drawImage(hap, 175, 220, this);
				// g.drawImage(hap, 325, 220, this);
				for (int i = 0; i < (pbw * 100 / 255) % 60 && i <= 5; i++)
					g.drawImage(hap, 325 + 25 * i, 220, this);
			} else {
				g.drawImage(sor, 170, 220, this);
			}// 不超過60顯示sorry

		} else if (s >= 0)// 遊戲進行中
		{
			// 課本21-28 畫面閃爍處理,建立一個和畫面大小一樣的繪圖區
			imgB = createImage(getWidth(), getHeight());// 建立imgB圖形物件
			gB = imgB.getGraphics();// 取得imgB的繪圖區
			// 先把所有圖片都畫到gB上，最後清除原本的繪圖區
			// 即為paint()最後一行的g.drawImage(imgB, 0, 0, this);

			gB.drawString("time", 375, 65);// 標示time
			gB.drawRect(400, 50, 120, 20);// 時間條邊框
			gB.fillRect(400, 50, 120 - s, 20);// 時間條
			// t++;
			gB.setColor(Color.red);
			gB.drawString("score", 18, 65);// 標示score
			gB.drawString("60", 105, 45);// 標示分數60
			gB.drawString("100", 140, 45);// 標示分數100
			gB.drawRect(50, 50, 60, 20);// 分數60的邊框
			gB.drawRect(50, 50, 100, 20);// 分數100的邊框
			gB.fillRect(50, 50, pbw * 100 / 255, 20);// 分數條(百分比)---上限255

			gB.drawImage(c1b, c1bx, c1by, c1bw, 20, this);// c1的地毯
			if (sorry == 0 || sorry >= 5 && sorry <= 10) {

				if (c1rol == false) {// c1的面向
					gB.drawImage(c1r, c1x, c1y, this);
				}
				if (c1rol == true) {
					gB.drawImage(c1l, c1x, c1y, this);
				}
			}// c1 scold 1~4 5結束
			else if (sorry == 2 || sorry == 4)
				gB.drawImage(c1sc1, c1x, c1y, this);
			else if (sorry == 1 || sorry == 3 || sorry == 5)
				gB.drawImage(c1sc2, c1x, c1y, this);

			gB.drawImage(c2b, c2bx, c2by, c2bw, 20, this);// c2的地毯
			if (sorry >= 0 && sorry <= 5) {

				if (c2rol == false) {// c2的面向
					gB.drawImage(c2r, c2x, c2y, this);
				}
				if (c2rol == true) {
					gB.drawImage(c2l, c2x, c2y, this);
				}
			}// c2 scold 6~9 10結束
			else if (sorry % 2 == 0)
				gB.drawImage(c2sc1, c2x - 14, c2y, this);
			else if (sorry % 2 == 1)
				gB.drawImage(c2sc2, c2x - 14, c2y, this);

			gB.drawImage(pb, pbx, pby, pbw, 20, this);// 玩家的地毯

			if (sleep == 0 && sorry == 0) {// 如果玩家沒有被大便砸到也沒有偷推被發現

				if (rol == false) {// 面向右
					if (prightarrow == true)// 跑步動作
					{
						if (run1or2 == false)
							gB.drawImage(prr1, px, py, this);
						if (run1or2 == true)
							gB.drawImage(prr2, px, py, this);
					} else if (push == 2) {
						gB.drawImage(prp, px, py, this);

					} else
						// 站立
						gB.drawImage(pr, px, py, this);
				}
				if (rol == true) {// 面向左
					if (pleftarrow == true)// 跑步動作
					{
						if (run1or2 == false)
							gB.drawImage(prl1, px, py, this);
						if (run1or2 == true)
							gB.drawImage(prl2, px, py, this);
					} else if (push == 1) {
						gB.drawImage(plp, px - 20, py, this);

					}

					else
						// 站立
						gB.drawImage(pl, px, py, this);
				}
			} else if (sorry != 0)// sorry!=0,被發現了,玩家為抱歉狀態
				gB.drawImage(sor, px, py, this);
			else if (sleep % 2 == 0)// sleep!=0,被砸了
				gB.drawImage(sl1, px, py, this);
			else if (sleep % 2 == 1)
				gB.drawImage(sl2, px, py, this);

			if (t % 40 == 0)// 出現poo
			{
				pox = (int) (Math.random() * pbw) + pbx;// 隨機取player地毯範圍裡的x當作poo的x座標
				gB.drawImage(poo, pox, 50, this);
				poy = 0;// 歸零
			} else if (pox != 0 && poy <= 160)// 落下poo
			{
				gB.drawImage(poo, pox, 50 + poy, this);// poo的位置
			}
			if (pox + 20 >= px && pox <= px + 20 && 50 + poy + 20 == py) {// 被大便砸到
				sleep = 1;
				prightarrow = pleftarrow = false;
				txf.removeKeyListener(frm);// 不可移動
				// System.out.println("hit");
			}
			g.drawImage(imgB, 0, 0, this);
		} else {// 開始畫面
			g.drawImage(startbg, 0, 30, this);
		}
	}

	boolean pleftarrow = false, prightarrow = false;// 使人物連續移動用Arrow
	int value = 5;

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {// 面向左
			pleftarrow = true;
			rol = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {// 面向右
			prightarrow = true;
			rol = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && px == pbx && c1bw > 70) {// 地毯長度下限
			// 玩家推左邊地毯

			if (c1rol == false) {// 此時c1看右邊，偷推被發現
				txf.removeKeyListener(frm);// 玩家不可動作,延遲兩秒
				prightarrow = pleftarrow = false;
				sorry = 1;
			} else {
				push = 1;
				pbx -= value;
				pbw += value;
				c1bw -= value;// c1地毯變短
				if (c1x + 20 >= c1bx + c1bw)// 避免player推地毯的時候c1站在邊緣，造成錯誤
					c1x -= value;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && px + 20 == pbx + pbw
				&& c2bw > 70) {
			// 玩家推右邊地毯
			if (c2rol == true) {// 此時c2看左邊，偷推被發現
				txf.removeKeyListener(frm);// 玩家不可動作,延遲兩秒
				prightarrow = pleftarrow = false;
				sorry = 6;
			} else {
				push = 2;
				pbw += value;
				c2bw -= value;// c2地毯變短
				c2bx += value;
				if (c2x <= c2bx)// 避免player推地毯的時候c1站在邊緣，造成錯誤
					c2x += value;
			}
		}
		paint(getGraphics());
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			pleftarrow = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			prightarrow = false;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void actionPerformed(ActionEvent e) {
		// System.out.println((int)(2*Math.random()));
		if (s >= 0 && (Timer) e.getSource() == timer) {// 每500毫秒執行的函式，computer1
			if (push != 0)
				push = 0;// player推完恢復站立
			if (sleep == 5 || sleep == 10) {// 加到5時恢復keylistener,sleep=0 站起來
				sleep = 0;
				txf.addKeyListener(frm);
			} else if (sleep > 0 && sleep < 5) {// 睡覺延遲兩秒,sleep=1~4
				sleep += 1;
			}

			if (sorry == 5 || sorry == 10) {// 加到5時恢復keylistener,sorry=0 站起來
				sorry = 0;
				txf.addKeyListener(frm);
			} else if (sorry > 0 && sorry < 5 || sorry > 5 && sorry < 10) {// sorry延遲兩秒,sorry=1~4
				sorry += 1;
			}

			if (run1or2 == false)
				run1or2 = true;// 跑步動作
			else if (run1or2 == true)
				run1or2 = false;

			s++;// 時間條扣的長度

			if (sorry == 0 || sorry >= 6 && sorry <= 10) {// c1不在sorry狀態才走路
				
				if (c1rol == true && c1x > c1bx)// 左邊界
					c1x -= value;
				if (c1rol == false && (c1x + 20) < (c1bx + c1bw))// 右邊界
					c1x += value;
				if (c1x + 20 == (c1bx + c1bw)) {// 到達右邊界轉向 20=人物寬
					if ((sleep != 0 || rol == false) && pbw > 35&&c2rol==false) {// 地毯下限35
						c1bw += value;// com1偷推地毯
						pbw -= value;// player地毯變短
						pbx += value;
						if (px == pbx - 5) {// 避免c1推地毯的時候player站在邊緣，造成錯誤
							px += value;
						}
					}
					c1rol = true;
				}
				if (c1x == c1bx) {// 到達左邊界轉向
					c1rol = false;
				}
				if (rol == true && c1rol == true)// 假設玩家面向左，c1隨機轉向右
				{
					if ((int) (4 * Math.random()) == 1)
						c1rol = false;
				}
			}
			if (sorry >= 0 && sorry <= 5) {// c2不在sorry狀態才走路
				
				if (c2rol == true && c2x > c2bx)// 左邊界
					c2x -= value;
				if (c2rol == false && (c2x + 20) < (c2bx + c2bw))// 右邊界
					c2x += value;
				if (c2x + 20 == (c2bx + c2bw)) {// 到達右邊界轉向 20=人物寬

					c2rol = true;
				}
				if (c2x == c2bx) {// 到達左邊界轉向

					if ((sleep != 0 || rol == true) && pbw > 35&&c2rol==true) {// 地毯下限35
						c2bw += value;// com2偷推地毯
						c2bx -= value;
						pbw -= value;// player地毯變短
						if (px + 20 >= pbx + pbw) {// 避免c2推地毯的時候player站在邊緣，造成錯誤
							px -= value;
						}

					}
					c2rol = false;
				}
				if (rol == false && c2rol == false)// 假設玩家面向右，c2隨機轉向左
				{
					if ((int) (4 * Math.random()) == 1)
						c2rol = true;
				}
			}
		}
		if (s >= 0 && (Timer) e.getSource() == timer2) {// 每100毫秒執行的函式 檢查arrow
			t++;
			if (poy <= 160)
				poy += 10;// poo落下
			if (pleftarrow == true && px > pbx)// 左邊界
				px -= value;
			if (prightarrow == true && (px + 20) < (pbx + pbw))// 右邊界 20=人物寬
				px += value;
		}
		if (s < 0 && (Button) e.getSource() == btn) {// 按下按鈕
			s = 0;
			// System.out.println("!!");
			timer.start();
			timer.addActionListener(frm);
			timer2.start();
			timer2.addActionListener(frm);
			txf.addKeyListener(frm);
			// repaint();
			btn.setVisible(false);
		}
		paint(getGraphics());
	}

}