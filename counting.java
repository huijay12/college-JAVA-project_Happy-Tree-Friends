import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.*;

public class counting extends TimerTask
{
          PointerInfo pi = null;
          public int px, py;
          
          
         public void run() {
            pi = MouseInfo.getPointerInfo();
            Point point = pi.getLocation();
            System.out.println("x:" + point.x + " | y: "  + point.y);
            px = point.x;
            py = point.y;
         }
      }