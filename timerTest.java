import java.util.*;

 class DateTask extends TimerTask 
 {
   
    public void run()
    {
      System.out.println("time go through");
     System.out.println("Task 執行時間：" + new Date());
      }
  }
  
  
  
public class timerTest {
     public static void main(String[] args) {
 
         timerTest timerDemo = new timerTest();
         timerDemo.testScheduleDelay();
         timerDemo.testScheduleDelayAndPeriod();
         timerDemo.testScheduleDateAndPeriod();
     }
     
     void testScheduleDelay(){
 
         Timer timer = new Timer();
         System.out.println("Delay：3秒");
         System.out.println("In testScheduleDelay：" + new Date());
 
         // schedule(TimerTask task, long delay)
         timer.schedule(new DateTask(), 3000);
         
         try {
             Thread.sleep(10000);
         }
             catch(InterruptedException e) {
         }
 
         timer.cancel();
         System.out.println("End testScheduleDelay：" 
             + new Date() + "\n");
     }
     
     void testScheduleDelayAndPeriod(){
 
         Timer timer = new Timer();
         System.out.println("Delay：3秒, Period：2秒");
         System.out.println("In testScheduleDelayAndPeriod：" 
             + new Date());
         
         // schedule(TimerTask task, long delay, long period)
         timer.schedule(new DateTask(), 3000, 2000);
        
         try {
             Thread.sleep(10000);
        }
             catch(InterruptedException e) {
         }
 
         timer.cancel();
         System.out.println("End testScheduleDelayAndPeriod：" 
             + new Date() + "\n");
     }
     
     void testScheduleDateAndPeriod(){
 
         Timer timer = new Timer();
         
         // 設定填入schedule中的 Date firstTime 
         
         Calendar calendar = Calendar.getInstance();
         calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND)+15)
                      ;
         Date firstTime = calendar.getTime();
         
         // 也可用 simpleDateFormat 直接設定 firstTime 
       
         // SimpleDateFormat dateFormatter = 
         //      new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
        // Date firstTime = dateFormatter.parse("2011/12/25 13:30:00");
       
         System.out.println("In testScheduleDateAndPeriod：" 
             + new Date());
       System.out.println("設定執行 Date 為15秒後：" 
             + firstTime +", Period：10秒");
                 
         // schedule(TimerTask task, Date firstTime, long period)
         timer.schedule(new DateTask(), firstTime, 10000);
                 
         try {
             Thread.sleep(30000);
         }
             catch(InterruptedException e) {
         }
 
         timer.cancel();
         System.out.println("End testScheduleDateAndPeriod：" 
             + new Date() + "\n");
     }
 }