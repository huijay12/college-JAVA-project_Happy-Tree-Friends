//package flower;

import java.awt.*;
import java.awt.event.*;

import javax.swing.Timer;

public class Flower extends Frame implements KeyListener, ActionListener {
	private static final long serialVersionUID = 1L;
	int timerflag = 0;
	int px = 245, py = 230;// player��m�ܼ�
	boolean rol = false, c1rol = false, c2rol = true;// �H�����V(0=right,1=left)
	Image startbg = getToolkit().getImage("start_page.jpg");// �}�l�e��
	static Button btn = new Button("Start!");// �}�l���s
	Image pr = getToolkit().getImage("playerright.png");// �Ϥ����| player���V�k(19*45)
	Image pl = getToolkit().getImage("playerleft.png");// player���V��
	Image pb = getToolkit().getImage("playerback.png");// player���a��(65*20)
	Image prr1 = getToolkit().getImage("prr1.png");
	Image prr2 = getToolkit().getImage("prr2.png");
	Image prl1 = getToolkit().getImage("prl1.png");
	Image prl2 = getToolkit().getImage("prl2.png");
	Image c1r = getToolkit().getImage("com1right.png");// computer1
	Image c1l = getToolkit().getImage("com1left.png");
	Image c1b = getToolkit().getImage("com1back.png");
	Image c1sc1 = getToolkit().getImage("c1scold1.png");// c1�d�|
	Image c1sc2 = getToolkit().getImage("c1scold2.png");
	Image c2r = getToolkit().getImage("com2right.png");// computer1
	Image c2l = getToolkit().getImage("com2left.png");
	Image c2b = getToolkit().getImage("com2back.png");
	Image c2sc1 = getToolkit().getImage("c2scold1.png");// c1�d�|
	Image c2sc2 = getToolkit().getImage("c2scold2.png");
	Image poo = getToolkit().getImage("poo.png");
	Image sl1 = getToolkit().getImage("sleep1.png");// �Qpoo�{���ı,��Z�M�SZ�r
	Image sl2 = getToolkit().getImage("sleep2.png");
	Image sor = getToolkit().getImage("sorry.png");// player sorry
	Image hap = getToolkit().getImage("happy.png");// player happy
	Image plp = getToolkit().getImage("plpush.png");// player������
	Image prp = getToolkit().getImage("prpush.png");// player������
	int pbx = px - 20, pby = py + 35;// player���a���l��m�]�� �P�H���_�l��m���۹��m
	int pbw = 95;// player�a��e���ܼ�
	int c1x = 90, c1y = 230;// computer1�H����l��m
	int c1bx = c1x - 20, c1by = c1y + 35;// com1�a��
	int c1bw = 150;// com1�a��e
	int c2x = 345, c2y = 230;// computer2�H����l��m
	int c2bx = c2x - 20, c2by = c2y + 35;// com2�a��
	int c2bw = 150;// com2�a��e
	static Flower frm = new Flower();// �����ܼ�
	static TextField txf = new TextField(18);// Ū����L��
	static Timer timer2 = new Timer(100, null);
	static Timer timer = new Timer(500, null);
	int s = -1;// �ɶ�������
	int t = 41, pox = 0, poy = 0;// poo ��ܮɾ� t%40==0,poo x,y����
	int sleep = 0, sorry = 0;// �Q�j�K�{��,�����Q�ݨ쪺����
	int push = 0;// ���a��ʧ@

	Image imgB;
	Graphics gB;
	Graphics g;

	public static void main(String[] args) {
		frm.setTitle("���");
		frm.setSize(560, 400);// �����j�p
		frm.setLocation(200, 200);// ��m
		frm.setLayout(null);
		frm.addWindowListener(new WindowAdapter() {// �������������䦳��
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
		if (s >= 120)// �C�������ɲM���e���W���F������time's up!
		{
			timer.removeActionListener(frm);
			timer2.removeActionListener(frm);
			txf.removeKeyListener(frm);
			imgB = createImage(getWidth(), getHeight());// �إ�imgB�ϧΪ���
			g.drawImage(imgB, 0, 0, this);// �M��ø�ϰ�
			g.setFont(new Font("Arial", Font.ITALIC, 60));// �r��
			g.drawString("time's up!", 150, 200);// ���time's up!
			g.setFont(new Font("Arial", Font.ITALIC, 25));// �r��
			g.drawString("score=" + pbw * 100 / 255, 200, 250);// ��ܤ���
			if (pbw * 100 / 255 > 60)// �W�L60��ܨ�ӤHhappy
			{
				g.drawImage(hap, 175, 220, this);
				// g.drawImage(hap, 325, 220, this);
				for (int i = 0; i < (pbw * 100 / 255) % 60 && i <= 5; i++)
					g.drawImage(hap, 325 + 25 * i, 220, this);
			} else {
				g.drawImage(sor, 170, 220, this);
			}// ���W�L60���sorry

		} else if (s >= 0)// �C���i�椤
		{
			// �ҥ�21-28 �e���{�{�B�z,�إߤ@�өM�e���j�p�@�˪�ø�ϰ�
			imgB = createImage(getWidth(), getHeight());// �إ�imgB�ϧΪ���
			gB = imgB.getGraphics();// ���oimgB��ø�ϰ�
			// ����Ҧ��Ϥ����e��gB�W�A�̫�M���쥻��ø�ϰ�
			// �Y��paint()�̫�@�檺g.drawImage(imgB, 0, 0, this);

			gB.drawString("time", 375, 65);// �Х�time
			gB.drawRect(400, 50, 120, 20);// �ɶ������
			gB.fillRect(400, 50, 120 - s, 20);// �ɶ���
			// t++;
			gB.setColor(Color.red);
			gB.drawString("score", 18, 65);// �Х�score
			gB.drawString("60", 105, 45);// �Хܤ���60
			gB.drawString("100", 140, 45);// �Хܤ���100
			gB.drawRect(50, 50, 60, 20);// ����60�����
			gB.drawRect(50, 50, 100, 20);// ����100�����
			gB.fillRect(50, 50, pbw * 100 / 255, 20);// ���Ʊ�(�ʤ���)---�W��255

			gB.drawImage(c1b, c1bx, c1by, c1bw, 20, this);// c1���a��
			if (sorry == 0 || sorry >= 5 && sorry <= 10) {

				if (c1rol == false) {// c1�����V
					gB.drawImage(c1r, c1x, c1y, this);
				}
				if (c1rol == true) {
					gB.drawImage(c1l, c1x, c1y, this);
				}
			}// c1 scold 1~4 5����
			else if (sorry == 2 || sorry == 4)
				gB.drawImage(c1sc1, c1x, c1y, this);
			else if (sorry == 1 || sorry == 3 || sorry == 5)
				gB.drawImage(c1sc2, c1x, c1y, this);

			gB.drawImage(c2b, c2bx, c2by, c2bw, 20, this);// c2���a��
			if (sorry >= 0 && sorry <= 5) {

				if (c2rol == false) {// c2�����V
					gB.drawImage(c2r, c2x, c2y, this);
				}
				if (c2rol == true) {
					gB.drawImage(c2l, c2x, c2y, this);
				}
			}// c2 scold 6~9 10����
			else if (sorry % 2 == 0)
				gB.drawImage(c2sc1, c2x - 14, c2y, this);
			else if (sorry % 2 == 1)
				gB.drawImage(c2sc2, c2x - 14, c2y, this);

			gB.drawImage(pb, pbx, pby, pbw, 20, this);// ���a���a��

			if (sleep == 0 && sorry == 0) {// �p�G���a�S���Q�j�K�{��]�S�������Q�o�{

				if (rol == false) {// ���V�k
					if (prightarrow == true)// �]�B�ʧ@
					{
						if (run1or2 == false)
							gB.drawImage(prr1, px, py, this);
						if (run1or2 == true)
							gB.drawImage(prr2, px, py, this);
					} else if (push == 2) {
						gB.drawImage(prp, px, py, this);

					} else
						// ����
						gB.drawImage(pr, px, py, this);
				}
				if (rol == true) {// ���V��
					if (pleftarrow == true)// �]�B�ʧ@
					{
						if (run1or2 == false)
							gB.drawImage(prl1, px, py, this);
						if (run1or2 == true)
							gB.drawImage(prl2, px, py, this);
					} else if (push == 1) {
						gB.drawImage(plp, px - 20, py, this);

					}

					else
						// ����
						gB.drawImage(pl, px, py, this);
				}
			} else if (sorry != 0)// sorry!=0,�Q�o�{�F,���a����p���A
				gB.drawImage(sor, px, py, this);
			else if (sleep % 2 == 0)// sleep!=0,�Q�{�F
				gB.drawImage(sl1, px, py, this);
			else if (sleep % 2 == 1)
				gB.drawImage(sl2, px, py, this);

			if (t % 40 == 0)// �X�{poo
			{
				pox = (int) (Math.random() * pbw) + pbx;// �H����player�a��d��̪�x��@poo��x�y��
				gB.drawImage(poo, pox, 50, this);
				poy = 0;// �k�s
			} else if (pox != 0 && poy <= 160)// ���Upoo
			{
				gB.drawImage(poo, pox, 50 + poy, this);// poo����m
			}
			if (pox + 20 >= px && pox <= px + 20 && 50 + poy + 20 == py) {// �Q�j�K�{��
				sleep = 1;
				prightarrow = pleftarrow = false;
				txf.removeKeyListener(frm);// ���i����
				// System.out.println("hit");
			}
			g.drawImage(imgB, 0, 0, this);
		} else {// �}�l�e��
			g.drawImage(startbg, 0, 30, this);
		}
	}

	boolean pleftarrow = false, prightarrow = false;// �ϤH���s�򲾰ʥ�Arrow
	int value = 5;

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {// ���V��
			pleftarrow = true;
			rol = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {// ���V�k
			prightarrow = true;
			rol = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && px == pbx && c1bw > 70) {// �a����פU��
			// ���a������a��

			if (c1rol == false) {// ����c1�ݥk��A�����Q�o�{
				txf.removeKeyListener(frm);// ���a���i�ʧ@,������
				prightarrow = pleftarrow = false;
				sorry = 1;
			} else {
				push = 1;
				pbx -= value;
				pbw += value;
				c1bw -= value;// c1�a���ܵu
				if (c1x + 20 >= c1bx + c1bw)// �קKplayer���a�઺�ɭ�c1���b��t�A�y�����~
					c1x -= value;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && px + 20 == pbx + pbw
				&& c2bw > 70) {
			// ���a���k��a��
			if (c2rol == true) {// ����c2�ݥ���A�����Q�o�{
				txf.removeKeyListener(frm);// ���a���i�ʧ@,������
				prightarrow = pleftarrow = false;
				sorry = 6;
			} else {
				push = 2;
				pbw += value;
				c2bw -= value;// c2�a���ܵu
				c2bx += value;
				if (c2x <= c2bx)// �קKplayer���a�઺�ɭ�c1���b��t�A�y�����~
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
		if (s >= 0 && (Timer) e.getSource() == timer) {// �C500�@����檺�禡�Acomputer1
			if (push != 0)
				push = 0;// player������_����
			if (sleep == 5 || sleep == 10) {// �[��5�ɫ�_keylistener,sleep=0 ���_��
				sleep = 0;
				txf.addKeyListener(frm);
			} else if (sleep > 0 && sleep < 5) {// ��ı������,sleep=1~4
				sleep += 1;
			}

			if (sorry == 5 || sorry == 10) {// �[��5�ɫ�_keylistener,sorry=0 ���_��
				sorry = 0;
				txf.addKeyListener(frm);
			} else if (sorry > 0 && sorry < 5 || sorry > 5 && sorry < 10) {// sorry������,sorry=1~4
				sorry += 1;
			}

			if (run1or2 == false)
				run1or2 = true;// �]�B�ʧ@
			else if (run1or2 == true)
				run1or2 = false;

			s++;// �ɶ�����������

			if (sorry == 0 || sorry >= 6 && sorry <= 10) {// c1���bsorry���A�~����
				
				if (c1rol == true && c1x > c1bx)// �����
					c1x -= value;
				if (c1rol == false && (c1x + 20) < (c1bx + c1bw))// �k���
					c1x += value;
				if (c1x + 20 == (c1bx + c1bw)) {// ��F�k�����V 20=�H���e
					if ((sleep != 0 || rol == false) && pbw > 35&&c2rol==false) {// �a��U��35
						c1bw += value;// com1�����a��
						pbw -= value;// player�a���ܵu
						pbx += value;
						if (px == pbx - 5) {// �קKc1���a�઺�ɭ�player���b��t�A�y�����~
							px += value;
						}
					}
					c1rol = true;
				}
				if (c1x == c1bx) {// ��F�������V
					c1rol = false;
				}
				if (rol == true && c1rol == true)// ���]���a���V���Ac1�H����V�k
				{
					if ((int) (4 * Math.random()) == 1)
						c1rol = false;
				}
			}
			if (sorry >= 0 && sorry <= 5) {// c2���bsorry���A�~����
				
				if (c2rol == true && c2x > c2bx)// �����
					c2x -= value;
				if (c2rol == false && (c2x + 20) < (c2bx + c2bw))// �k���
					c2x += value;
				if (c2x + 20 == (c2bx + c2bw)) {// ��F�k�����V 20=�H���e

					c2rol = true;
				}
				if (c2x == c2bx) {// ��F�������V

					if ((sleep != 0 || rol == true) && pbw > 35&&c2rol==true) {// �a��U��35
						c2bw += value;// com2�����a��
						c2bx -= value;
						pbw -= value;// player�a���ܵu
						if (px + 20 >= pbx + pbw) {// �קKc2���a�઺�ɭ�player���b��t�A�y�����~
							px -= value;
						}

					}
					c2rol = false;
				}
				if (rol == false && c2rol == false)// ���]���a���V�k�Ac2�H����V��
				{
					if ((int) (4 * Math.random()) == 1)
						c2rol = true;
				}
			}
		}
		if (s >= 0 && (Timer) e.getSource() == timer2) {// �C100�@����檺�禡 �ˬdarrow
			t++;
			if (poy <= 160)
				poy += 10;// poo���U
			if (pleftarrow == true && px > pbx)// �����
				px -= value;
			if (prightarrow == true && (px + 20) < (pbx + pbw))// �k��� 20=�H���e
				px += value;
		}
		if (s < 0 && (Button) e.getSource() == btn) {// ���U���s
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