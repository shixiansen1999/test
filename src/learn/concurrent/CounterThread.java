package learn.concurrent;

import org.junit.Test;

public class CounterThread<T> extends Thread {
    private Counter counter;
    private T[] objects;

    public CounterThread(){}

    public CounterThread(Counter counter){
        this.counter = counter;
    }


    public CounterThread(T[] objects){
        this.objects = objects;
    }
    @Override
    public void run() {
        for (int i=0; i<=100; i++){
            counter.incr();
            System.out.print(i);
        }
    }

    @Test
    public static void main(String[] args) throws InterruptedException {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();

        Thread thread1 = new CounterThread(counter1);
        Thread thread2 = new CounterThread(counter2);
        thread1.start();
        //thread1.join();//12345678910
        //thread2.start();
        for (int i=100; i<110; ++i){
            System.out.print("a"+" ");
        }
        thread1.join();//随机的1 6 2 3 4 5 7 8 9 10 ；6 7 8 9 10 1 2 3 4 5

        //thread2.join();



    }

    /*public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        int num = 1000;
        Counter counter = new Counter();
        Thread[] threads = new Thread[num];
        for (int i=0;i<num;++i){
            threads[i] = new CounterThread(counter);
            threads[i].start();
            //threads[i].join();//这种方式运行时间较长

        }
        //这种方式运行时间较短
        for (int i=0; i<num; ++i){
            threads[i].join();
        }
        System.out.println(counter.getCount());
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }*/
}
