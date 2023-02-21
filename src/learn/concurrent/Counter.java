package learn.concurrent;

public class Counter {
    private int count;
    private Integer a=0;

    public synchronized void incr(){
        count++;
        sync1();
    }

    public void sync1(){
        synchronized (a){
            System.out.println("sync1-lock");
        }
    }
    public synchronized int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
