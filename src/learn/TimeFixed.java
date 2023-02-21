package learn;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class TimeFixed {
    static class LongRunningTask extends TimerTask{

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Long running finshed");
            long currentTimeMillis = System.currentTimeMillis();
            SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss-SSS");
            String timenow = sdt.format(currentTimeMillis);
            System.out.println("1:"+timenow);
        }
    }

    static class FixedDelayTask extends TimerTask{

        @Override
        public void run() {
            long currentTimeMillis = System.currentTimeMillis();
            SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss-SSS");
            String timenow = sdt.format(currentTimeMillis);
            System.out.println(timenow);
        }
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new LongRunningTask(),0);
        timer.schedule(new FixedDelayTask(),1000,1000);
    }
}
