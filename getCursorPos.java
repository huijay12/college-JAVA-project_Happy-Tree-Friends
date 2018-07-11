import java.awt.*;
import java.awt.event.*;
//import javax.swing.Timer;
import javax.swing.*;

public class getCursorPos{
   public static void main(String[] args){
      int delay = 100; //milliseconds
      ActionListener taskPerformer = new ActionListener(){
         private PointerInfo pi = null;
         public void actionPerformed(ActionEvent evt) {
            pi = MouseInfo.getPointerInfo();
            Point point = pi.getLocation();
            System.out.println("x:" + point.x + " | y: "  + point.y);
         }
      }; new Timer(delay, taskPerformer).start();

   }


//   public getCursorPos(){
     
  //    JFrame frame = new JFrame("getCursorPos");
   //}
}
