package learn.concurrent;

public class StaticSyncDemo {
    public synchronized static void demo(){
        while (true){
            System.out.println(Thread.currentThread());
        }
    }
}
