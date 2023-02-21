package learn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sun.org.apache.xml.internal.security.transforms.implementations.TransformC14NExclusiveWithComments;
import learn.classextends.Animal;
import learn.classextends.Base;
import learn.classextends.Child;
import learn.concurrent.*;
import learn.serializable.Children;
import learn.serializable.ComplexStudent;
import learn.serializable.ContactInfo;
import learn.serializable.Parent;
import org.junit.Test;

import java.awt.*;
import java.io.*;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ExtendsTest implements InterfaceTest {


    @Test
    public void extendsTest() {
        System.out.println("------new child");
        Child c = new Child();
        System.out.println("------c.action");
        c.action();
        Base b = c;
        System.out.println("------b.action");
        b.action();
        System.out.println("------b.s:" + b.s);
        System.out.println("------c.s:" + c.s);

    }

    @Test
    public void utilTest() {
        Integer q = 1;
        int w = 1;
        System.out.println(Integer.valueOf(w));
        System.out.println(q.equals(1));
    }

    @Test
    public void stringBuilderTest() {
        int[] arr = new int[]{1, 2, 3, 4};
        System.arraycopy(arr, 1, arr, 0, 3);
        for (int i : arr) {
            System.out.println(i);
        }

    }

    @Test
    public void dateTest() {
        Date date = new Date();
        System.out.println(date.toString());
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 3, 8, 21, 0, 789);
        calendar.roll(Calendar.MONTH, 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm ss SSS E");
        System.out.println(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(calendar.getTime()));
        System.out.println(simpleDateFormat.format(calendar.getTime()));

    }

    @Test
    public void randomTest() {
        char[] chars = new char[6];
        Random random = new Random();
        for (int i = 0; i < chars.length; i++) {
            //chars[i] = Character.forDigit(random.nextInt(10),10);
            chars[i] = (char) ('0' + random.nextInt(10));
        }
        for (char i : chars) {
            System.out.println(i == 0);
        }


    }

    @Test
    public void treemapTest() {
        TreeMap<String, Integer> map = new TreeMap<>(new Comparator<String>() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            @Override
            public int compare(String o1, String o2) {
                try {
                    return sdf.parse(o1).compareTo(sdf.parse(o2));
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
        map.put("2022-10-11", 100);
        map.put("2022-10-1", 200);
        for (Map.Entry<String, Integer> kv : map.entrySet()) {
            System.out.println(kv.getKey() + "," + kv.getValue());

        }
    }

    @Test
    public void a() {
        Animal a = new Animal();
        System.out.println(a.name);
    }

    @Test
    public void linkedhashsetTest() {
        LinkedHashSet lhs = new LinkedHashSet();
        lhs.add("a");
        lhs.add("b");
        lhs.add("c");
        lhs.add("a");
        System.out.println(lhs);
        LinkedHashMap lhm = new LinkedHashMap();
    }

    @Test
    public void enummapTest() {
        EnumMap<Size, String> em = new EnumMap(Size.class);
        em.put(Size.valueOf("S"), "小");
        em.put(Size.valueOf("L"), "L");
        em.put(Size.valueOf("M"), "中");
        em.put(Size.valueOf("S"), "s");
        System.out.println(em);
    }

    enum Size {
        S, M, L, XL;
    }


    @Test
    public void enumsetTest() {
        EnumSet<Size> enumSet = EnumSet.noneOf(Size.class);
        enumSet.add(Size.S);
        System.out.println(enumSet);
    }

    @Test
    public void priorityqueueTest() {
        //默认从小到大
        Queue<Integer> pq1 = new PriorityQueue<>();
        //给一个从大到小的比较器
        //Queue<Integer> pq2 = new PriorityQueue<>(11，Collections.reverseOrder());
        Queue<Integer> pq2 = new PriorityQueue<>(11, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        Queue<Integer> pq3 = new PriorityQueue<Integer>(new LinkedHashSet<>(Arrays.asList(new Integer[]{20, 14, 12, 24, 5, 9})));
        pq1.add(7);
        pq1.offer(10);
        pq1.addAll(Arrays.asList(new Integer[]{14, 2, 9}));
        System.out.println(pq1);
        while (pq1.peek() != null) {
            System.out.print(pq1.poll() + " ");
        }
        System.out.println();
        pq2.add(7);
        pq2.offer(10);
        pq2.addAll(Arrays.asList(new Integer[]{14, 2, 9}));
        System.out.println(pq2);
        while (pq2.peek() != null) {
            System.out.print(pq2.poll() + " ");
        }
        System.out.println();
        System.out.println(pq3);
        System.out.println("------");
        Integer[] integers = new Integer[pq3.size()];
        //integers = pq3.toArray(new Integer[0]);
        //integers = pq3.toArray(integers);
        for (Object ele : pq3.toArray()) {
            System.out.print(ele + " ");
        }
    }

    @Test
    public void b() {
        System.out.println(Integer.compare(1, 2));
        Integer m = 1;
        Comparable<Integer> a = m;
        System.out.println(a.compareTo(3));
    }

    @Test
    public void fileTest() {
        File a = new File("a");
        Scanner sc = new Scanner(System.in);
        if (sc.hasNext()) {
            String in = sc.next();
            System.out.println(in);
        }
        sc.close();
    }

    @Test
    public void xmlTest() throws JsonProcessingException {
        Animal dog = new Animal("dog");
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String str = mapper.writeValueAsString(dog);
        System.out.println(str);
    }

    @Test
    public void complexJsonTest() throws IOException {
        ComplexStudent student = new ComplexStudent("张三", 18);
        Map<String, Double> scoresMap = new HashMap<>();
        scoresMap.put("语文", 80d);
        scoresMap.put("数学", 90d);
        scoresMap.put("英语", 90d);
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setPhone("18084081552");
        contactInfo.setAddress("贵州省盘州市");
        contactInfo.setEmail("2650364543@qq.com");
        student.setScores(scoresMap);
        student.setContactInfo(contactInfo);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String stu = mapper.writeValueAsString(student);
        //System.out.println(stu);

        ComplexStudent complexStudent = mapper.readValue(stu, ComplexStudent.class);
        System.out.println(complexStudent.toString());

        mapper.writeValue(System.out, student);
    }

    @Test
    public void xunHuanYinYongTest() throws IOException {
        Parent parent = new Parent();
        parent.setName("老马");
        Children children = new Children();
        children.setName("小马");
        children.a = new int[]{1, 2, 3, 4};
        parent.setChildren(children);
        children.setParent(parent);
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String str = mapper.writeValueAsString(children);
        System.out.println(str);
        Children children1 = mapper.readValue(str, Children.class);
        System.out.println(children1.toString());
    }

    @Test
    public void thredTest() throws InterruptedException {
        Counter counter = new Counter();
        Thread thread = new CounterThread(counter);
        thread.start();


    }

    @Test
    public void staticSyncTest() throws ClassNotFoundException {
        Class class1 = StaticSyncDemo.class;
        Class class2 = new MyClassLoader("D:\\IDEA\\projects\\Test\\src\\learn\\concurrent\\").loadClass("StaticSyncDemo");
        System.out.println(class1.equals(class2));
        Thread thread1 = new StaticSyncThread(class1);
        Thread thread2 = new StaticSyncThread(class2);
        thread1.start();
        thread2.start();
    }


    @Test
    public void syncCollectionTest() throws InterruptedException {

        Map<Integer, Integer> map = new HashMap<>();
        EnhanceMap enhanceMap = new EnhanceMap(map);
        Thread thread_0 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Integer i=1;i<100;++i){
                    System.out.println(Thread.currentThread().getName() + "  " + i + ":" + enhanceMap.put(i, i));
                }
            }
        });
        Thread thread_1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Integer i=1,j=-1;i<100;++i,--j){
                    System.out.println(Thread.currentThread().getName()+"  "+i+":"+enhanceMap.putIfAbsent(i,j));
                }
            }
        });
        //两个线程同时执行，结果不可预测。
        thread_0.start();
        thread_1.start();
        thread_0.join();
        thread_1.join();
        System.out.println(map.toString());
    }

    @Test
    public void syncCollectionTest2() throws InterruptedException {
        Map<Integer, Integer> map = new HashMap<>();
        EnhanceMap enhanceMap1 = new EnhanceMap(map);
        EnhanceMap enhanceMap2 = new EnhanceMap(map);
        System.out.println(enhanceMap1.getMap()==enhanceMap2.getMap());
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<100; ++i){
                    enhanceMap1.putIfAbsent(i,i);
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<100;++i){
                    enhanceMap2.putIfAbsent(i,100+i);
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        //结果不可预测
        System.out.println(map.toString());
    }

    @Test
    public void putReturnValueHashMap() {
        Map<String, String> map = new HashMap<>();
        String put1 = map.put("a", "value1");
        String s1 = map.get("a");
        String put2 = map.put("a", "value2");
        String s2 = map.get("a");
        System.out.println("put1：" + put1 + " s1:" + s1 + " put2:" + put2 + " s2:" + s2);
    }

    @Test
    public  void iteratorThreadTest() throws InterruptedException {
        final List<String> list = Collections.synchronizedList(new ArrayList<String>());
        Thread modifyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;++i){
                    list.add("item"+i);
                    try {
                        //Thread.sleep((long) (Math.random()*10));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread iteratorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    synchronized (list){
                        for (String str : list){
                            System.out.println(str);
                        }
                    }
                }
            }
        });

        modifyThread.start();
        iteratorThread.start();
        modifyThread.join();

    }


    @Test
    public void sleepTest() throws InterruptedException {
        //让当前线程睡眠多少毫秒
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName());
    }

    public <V> V get(Object obj){
        return (V) obj;
    }

    @Test
    public void anyReturnType(){
        String str = get("1");
        System.out.println(str);
    }

    @Test
    public void atomicCASTest(){
        AtomicInteger counter = new AtomicInteger(0);
        System.out.println(counter.compareAndSet(1, 1));
        System.out.println(counter);
    }

    @Test
    public void copyOnWriteArrayListTset() throws InterruptedException {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(1,2,3,4,5));
        Thread thread_0 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (Integer ele : list){
                    System.out.print(ele+" ");
                }
            }
        });

        Thread thread_1 = new Thread(){
            @Override
            public void run() {
                for (int i=6; i<10; ++i) {
                    list.add(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread_0.start();
        thread_1.start();
        thread_1.join();
        thread_0.join();
    }

    static Map<String,String> map = new HashMap<>();

    public static class AddThrea extends Thread {
        int start;
        public AddThrea(int start){
            this.start = start;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            for (int i=start;i<10000;i+=2){
                map.put(Integer.toString(i),Integer.toBinaryString(i));
            }
        }
    }

    @Test
    public void  hashMaploopTest() throws InterruptedException {
        Thread t1 = new AddThrea(0);
        Thread t2 = new AddThrea(1);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(map.size());
    }

    @Test
    public void unSafeHashMapTest(){

        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0; i<100; ++i){
            Thread thread = new Thread(){
                Random ran = new Random();
                @Override
                public void run() {
                    for (int i=0; i<100; ++i){
                        map.put(ran.nextInt(),1);
                    }
                }
            };
            thread.start();
        }
        //Exception: java.util.HashMap$Node cannot be cast to java.util.HashMap$TreeNode
    }


    @Test
    public void adapterTest(){
        List<String> list = Collections.singletonList("a");
        for (String s : list){
            System.out.println(s);
        }
        //list.add("b");//java.lang.UnsupportedOperationException
        Map<String,Boolean> map = new HashMap<>();
        Set<String> setFromMap = Collections.newSetFromMap(map);
        setFromMap.add("1");
        for (String s : setFromMap){
            System.out.println(s);
        }
    }

    @Test
    public void timeTaskTest() throws ClassNotFoundException {
        Timer timer = new Timer();
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss-SSS");
        long a = 1650512585426L;
        System.out.println(sdt.format(a));
        System.out.println(Class.forName("java.lang.Comparable").getCanonicalName());
    }

    @Test
    public void fieldTest() throws IllegalAccessException {
        List<String> list = Arrays.asList(new String[]{"a", "b"});
        Class<? extends List> cls = list.getClass();
        for (Field f : cls.getDeclaredFields()) {
            f.setAccessible(true);
            System.out.println(f.getName() + ":" + f.get(list));
        }
    }


}
