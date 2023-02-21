package review;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.*;

/**
 * @author bx
 * @create 2022-10-31 21:10
 */
public class Review implements Serializable {


    @Test
    public void switchTest() {
        System.out.println("请输入你的等级：");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String next = sc.nextLine().toUpperCase();
            switch (next) {
                case "S":
                    System.out.println("您好，尊贵的超级会员");
                    break;
                case "A":
                    System.out.println("你好，尊贵的A+会员");
                    break;
                case "B":
                    System.out.println("你好，普通用户");
                    break;
                case "C":
                    System.out.println("你好，特殊用户");
                    break;
                default:
                    System.out.println("您不是我们的用户，请成为我们的会员");
            }
        }
    }


    @Test
    public void whileTest() {
        int i = 1;
        while (i < 10) {
            System.out.println("第" + i + "此");
            i++;
        }
    }

    @Test
    public void doWhileTest() {
        int i = 1;
        do {
            System.out.println("第" + i + "次");
            i++;
        } while (i < 10);
    }

    @Test
    public void breakTest() {
        int i, j = 0;
        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                if (j == 5) {
                    break;
                }
            }
        }
        System.out.println(i + ":" + j);

    }

    @Test
    public void continueTest() {
        int i, j = 0;
        for (i = 0; i < 9; i++) {
            if (i == 5) {
                continue;
            }
            j++;
        }
        System.out.println(j);
    }


    public static int sum(int... args) {
        int sum = 0;
        for (int x : args) {
            sum += x;
        }
        return sum;
    }

    /**
     * 可变参数只能有一个，且是参数列表中的最后一个
     */
    public static int sumMoney(int price, int... args) {
        int sum = 0;
        for (int x : args) {
            sum += price * x;
        }
        return sum;
    }

    @Test
    public void varParameterTest() {
        System.out.println("总和：" + sum(1, 2, 3, 3, 4, 5, 2));
        System.out.println("总价：" + sumMoney(5, 10, 10, 10));
    }


    public static String getBinaryStrFromByte(byte b) {
        String result = "";
        byte a = b;
        ;
        for (int i = 0; i < 8; i++) {
            byte c = a;
            a = (byte) (a >> 1);//每移一位如同将10进制数除以2并去掉余数。
            a = (byte) (a << 1);
            if (a == c) {
                result = "0" + result;
            } else {
                result = "1" + result;
            }
            a = (byte) (a >> 1);
        }
        return result;
    }

    public static byte bit2byte(String bString) {
        byte result = 0;
        for (int i = bString.length() - 1, j = 0; i >= 0; i--, j++) {
            result += (Byte.parseByte(bString.charAt(i) + "") * Math.pow(2, j));
        }
        return result;
    }

    @Test
    public void bitOperationTest() {
        int a = 3 << 5;
        int b = 16 >>> 3;
        int c = -16 >> 3;
    }

    //全局属性
    private int a = 1;
    public int b = 2;
    public final ArrayList<Integer> list = new ArrayList<>();

    class B extends Review {

        public final String bt = "A";

        public B() {
        }

    }

    @Test
    public void extendsTest() {

        final Review r1 = new Review();
        final Review r2 = new Review();
        B b1 = new B();
        System.out.println(b1.bt);
        r1.list.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        System.out.println(r1.list.toString());

    }

    public enum Size {
        S(1), M(2), L(3), XL(4), XLL(5), XLLL(6);
        private int id;

        private Size(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }
    }

    @Test
    public void enumTest() {
        Size[] values = Size.values();
        for (Size x : values) {
            System.out.println(x + ":" + x.getId());
        }
        System.out.println("请输入你的码数：");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int i = sc.nextInt();
            switch (i) {
                case 1:
                    System.out.println("你的衣服为：" + Size.S + "码");
                    break;
                case 2:
                    System.out.println("你的衣服为：" + Size.M + "码");
                    break;
                case 3:
                    System.out.println("你的衣服为：" + Size.L + "码");
                    break;
                case 4:
                    System.out.println("你的衣服为：" + Size.XL + "码");
                    break;
                case 5:
                    System.out.println("你的衣服为：" + Size.XLL + "码");
                    break;
                case 6:
                    System.out.println("你的衣服为：" + Size.XLLL + "码");
                    break;
                default:
                    System.out.println("请输入有效的数字");
            }
        }
    }

    @Test
    public void exectionTest() {

        try {
            int[] arr = new int[10];
            arr[11] = 1;
        } catch (Exception e) {

            e.printStackTrace();
            for (StackTraceElement element : e.getStackTrace()) {
                System.out.println(element);
            }
        }
    }

    @Test
    public void integerTest() {
        Integer integer = Integer.valueOf(3);
        int i = integer.intValue();
        System.out.println(integer.getClass());
        System.out.println(i);
    }

    @Test
    public void characterTest() {
        char ch;
        Character a = Character.valueOf('A');
        ch = a.charValue();
        System.out.println(a.equals(ch));
        System.out.println(a.getClass());
        System.out.println(ch);
    }

    @Test
    public void otherTypeTest() {
        System.out.println(Byte.parseByte("127"));
        System.out.println(Short.parseShort("180"));
        System.out.println(Boolean.parseBoolean("abc"));
        System.out.println(Float.parseFloat("11113.010100101001011111111111111111111111"));
        System.out.println(Double.parseDouble("3.010100101001011111111111111111111111"));
    }

    @Test
    public void stringTest() throws UnsupportedEncodingException {

        String str = new String();

        str = "123456";

        String str1 = "abc";
        String str2 = "ABc";
        String str3 = ",1,2,3,4,5,6,8,9";
        String str4 = "      qw  er       ";

        /*System.out.println(str.isEmpty());
        System.out.println(str.length());
        System.out.println(str.substring(0,3));
        System.out.println(str.indexOf('4'));
        System.out.println(str.indexOf("45",2));
        System.out.println(str.lastIndexOf('5',4));
        System.out.println(str.contains("124"));
        System.out.println(str.startsWith("23",1));
        System.out.println(str.endsWith("56"));*/

        /*System.out.println(str1.equals("abc"));
        System.out.println(str1.equalsIgnoreCase(str2));
        System.out.println(str1.toUpperCase());
        System.out.println(str2.toLowerCase());
        System.out.println(str.concat("789"));
        System.out.println(str.charAt(0));
        System.out.println((int)'a'-(int)'1');
        System.out.println(str1.compareToIgnoreCase(str));*/

        /*String[] split = str3.split(",");
        StringBuilder sb = new StringBuilder();
        System.out.println(split.length);
        System.out.println(split[0].equals(""));
        for (String s : split){
            sb.append(s);
            System.out.println(s);
        }
        System.out.println(sb);*/

        System.out.println(str4.length());
        System.out.println(str4.trim());
        System.out.println(str4.toCharArray());
        for (byte i : str4.getBytes("utf-8")) {
            System.out.println(i);
        }
    }

    @Test
    public void arraysTest() {
        Integer[] arr = {1, 2, 8, 3, 6, 2, 4, 7, 0, 9, 4};
        int[] arr1 = new int[10];
        arr1[0] = 2;
        arr1[6] = 9;
        arr1[9] = 1;
        arr1[5] = 10;
        Arrays.sort(arr1);
        Arrays.fill(arr1, 0, 5, 1);
        Arrays.sort(arr, (o1, o2) -> o1 - o2);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.binarySearch(arr, 8));
        System.out.println(Arrays.toString(Arrays.copyOf(arr, 3)));
    }

    @Test
    public void dateTest() {
        Date date = new Date();
        System.out.println(date.getTime());
        System.out.println("月：" + (date.getMonth() + 1) + "日：" + date.getDate() + "星期几：" + date.getDay() +
                "时间：" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
    }


    @Test
    public void calendarTest() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR_OF_DAY, 10);
        instance.roll(Calendar.DAY_OF_MONTH, 31);
        System.out.println(instance.getTimeInMillis());
        System.out.println(instance.getTime());
    }

    @Test
    public void dateFormatTest() throws ParseException {
        String date = "2022年11月3日 下午05时02分28秒";
        DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        System.out.println(dateTimeInstance.format(Calendar.getInstance().getTime()));
        Date parse = dateTimeInstance.parse(date);
        System.out.println(parse);
    }

    @Test
    public void simpleDateFormatTest() throws ParseException {
        String date = "17:09:54 2022/11/03 星期四 下午";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("HH:mm:ss yyyy/MM/dd E a");
        System.out.println(simpleDateFormat.format(new Date()));
        System.out.println(simpleDateFormat.parse(date));
    }

    @Test
    public void randomTest() {
        double random = Math.random();
        int result = (int) (random * 10);
        System.out.println(result);
        Random random1 = new Random();
        random1.setSeed(1L);
        byte[] bytes = new byte[10];
        System.out.println(random1.nextInt(100));
        System.out.println(random1.nextBoolean());
        random1.nextBytes(bytes);
        System.out.println(Arrays.toString(bytes));
        System.out.println(random1.nextLong());
        System.out.println(random1.nextFloat());
        System.out.println(random1.nextDouble());
    }

    @Test
    public void arrayListTest() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("小明");
        arrayList.add("小王");
        arrayList.add(1, "小李");
        arrayList.addAll(3, Arrays.asList("小东", "小西"));
        System.out.println(arrayList.remove("小西"));
        System.out.println(arrayList.indexOf("小李"));
        System.out.println("第一为同学是：" + arrayList.get(0));
        Object[] result = arrayList.toArray();
        System.out.println(Arrays.toString(result));
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }
        System.out.println(arrayList.size());
    }

    @Test
    public void linkedListTest() {
        LinkedList<String> list = new LinkedList<>();
        list.add("zhangsan");
        list.addAll(Arrays.asList("lisi", "wangwu", "zhaoliu", "shunqi"));
        list.addFirst("xiaoer");
        list.addLast("xiaoba");

        System.out.println(list);
        System.out.println(list.indexOf("wangwu"));
        list.remove();
        list.push("一一");
        System.out.println("头部元素:" + list.element() + list.peek());
        list.pop();
        System.out.println(".........");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }
    }


    @Test
    public void priorityQueueTest() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2 ? -1 : o1 < 02 ? 1 : 0;
            }
        });
        priorityQueue.add(10);
        priorityQueue.add(1);
        priorityQueue.add(9);
        priorityQueue.add(20);
        priorityQueue.offer(30);
        System.out.println(priorityQueue);
        priorityQueue.remove(1);
        System.out.println(priorityQueue.peek());
        priorityQueue.clear();
        System.out.println(priorityQueue);

    }

    @Test
    public void arrayDeque() {
        ArrayDeque<Integer> deque = new ArrayDeque<>(7);
        for (int i = 0; i < 16; i++) {
            deque.addLast(i);
        }
        deque.add(16);
        deque.add(17);
        System.out.println(deque);
    }

    @Test
    public void hashMapTest() {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(1, 2);
        map.put(2, 20);
        map.remove(1);
        map.replace(2, 20, 4);
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println(map.containsKey(3));
        System.out.println(map.containsValue(20));
    }


    @Test
    public void hashSetTest() {
        HashSet<Integer> set = new HashSet<>();
        set.addAll(Arrays.asList(1, 1, 2, 2, 3, 4, 4, 5, 6, 6));
        set.remove(3);
        System.out.println(set.contains(1));
        for (Integer i : set) {
            System.out.println(i);
        }
    }

    @Test
    public void treeMap() {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1, "小王");
        treeMap.put(2, "小李");
        treeMap.put(3, "小明");
        treeMap.put(4, "小张");
        treeMap.remove(4);
        System.out.println(treeMap.lastEntry());
        System.out.println(treeMap.firstEntry());
    }

    @Test
    public void treeSet() {
        TreeSet<String> treeSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        treeSet.add("A");
        treeSet.add("B");
        treeSet.addAll(Arrays.asList("a", "b", "C", "C", "c"));
        System.out.println(treeSet);
        System.out.println(treeSet.subSet("Z", "A"));
        System.out.println(treeSet.headSet("B", true));
        System.out.println(treeSet.tailSet("a", true));
    }

    @Test
    public void linkedHashMap() {
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>(8, 0.75F, true);
        linkedHashMap.put(1, "xiaoli");
        linkedHashMap.put(2, "xiaoming");
        linkedHashMap.put(3, "xiaozhang");
        linkedHashMap.put(4, "xiaozhao");
        linkedHashMap.get(1);
        System.out.println(linkedHashMap);
    }

    @Test
    public void linkedHashSet() {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.addAll(Arrays.asList("a", "A", "S", "m", "s", "A", "F"));
        System.out.println(linkedHashSet);
    }

    public enum SizeInfo {
        S("small"), M("medium"), L("len");
        private String info;

        private SizeInfo(String info) {
            this.info = info;
        }
    }

    @Test
    public void enumMap() {
        EnumMap<SizeInfo, Integer> enumMap = new EnumMap<>(SizeInfo.class);
        enumMap.put(SizeInfo.M, 100);
        System.out.println(SizeInfo.M.ordinal());
        System.out.println(enumMap);
    }

    @Test
    public void enumSet() {
        EnumSet<SizeInfo> enumSet = EnumSet.noneOf(SizeInfo.class);
        enumSet.add(SizeInfo.S);
        enumSet.add(SizeInfo.M);
        System.out.println(enumSet);
    }


    /**
     * Arraylist            add remove get
     * LinkedList           add,offer remove,poll element,peek addFirst addLast  push pop peek
     * <p>
     * HashSet              add remove
     * LinkedHashSet       add remove
     * TreeSet              add remove
     * EnumSet(抽象类)       add remove
     * <p>
     * PriorityQueue        add,offer remove,poll element,peek
     * <p>
     * ArrayDeque           add,offer remove,poll element,peek addFirst addLast  push pop peek
     * <p>
     * HashMap              put remove get
     * LinkedHashMap       put remove get
     * TreeMap              put remove get
     * EnumMap              put remove get
     */

    @Test
    public void collectionsTest() {
        List<Integer> list = Collections.synchronizedList(Arrays.asList(1, 9, 3, 6, 7, 5, 11, 9, 9));
        Collections.sort(list);
        System.out.println(Collections.max(list));
        Collections.frequency(list, 9);
        System.out.println(Collections.binarySearch(list, 5));
        Collections.swap(list, 0, 8);
        System.out.println(list);
        HashMap<Integer, Boolean> m = new HashMap<>();
        Set<Integer> set = Collections.newSetFromMap(m);
        System.out.println(set);
        Map<Integer, String> xiaowang = Collections.singletonMap(1, "xiaowang");
        System.out.println(xiaowang);
    }


    @Test
    public void directoryTest() throws IOException {
        File file = new File("D:\\IDEA\\projects\\Test\\testfile\\myfile");
        System.out.println(file.mkdirs());
        File file1 = new File("D:\\IDEA\\projects\\Test\\testfile");
        for (File s : file1.listFiles((dir, name) -> name.equals("myfile") ? false : true)) {
            System.out.println(s);
        }

    }

    @Test
    public void fileTest() throws IOException {
        File file1 = new File("testfile\\myfile\\my.txt");
        file1.createNewFile();
        //System.out.println(file1.renameTo(new File("myfile.txt")));
        //System.out.println(file1.delete());
        System.out.println(file1.getName());
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getCanonicalPath());
        System.out.println(file1.getCanonicalFile().equals(file1));
    }


    public void findAllSrcAbsolutePath(File file) throws Exception {
        if (file.isFile()){
            throw new Exception("目标文件必须是一个目录！");
        }
        ArrayList<File> src = new ArrayList<>();
        List<File> allSrcFile = findAllSrcFile(file, src);
        allSrcFile.forEach(t -> {
            try {
                System.out.println(t.getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public List<File> findAllSrcFile(File file, List<File> list){
        for (File f:file.listFiles()){
            if (f.isFile() && f.getName().endsWith(".txt")){
                list.add(f);
            }else {
                findAllSrcFile(f,list);
            }
        }
        return list;
    }

    /**
     * 在指定文件夹加下找到符合要求的所有文件
     * @throws Exception
     */
    @Test
    public void findAllTxt() throws Exception {
        findAllSrcAbsolutePath(new File("D:\\IDEA\\projects\\Test\\testfile"));
    }


    @Test
    public void fileInputStreamTest() throws IOException {
        File file = new File("testfile\\myfile\\my.txt");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        inputStream.read(bytes);
        System.out.println(Arrays.toString(bytes));
        inputStream.close();
    }

    @Test
    public void fileOutputStreamTest() throws Exception {
        File file = new File("testfile\\myfile\\my.txt");
        FileInputStream inputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream("testfile\\myfile\\to.txt", true);
        byte[] bytes = new byte[1024];
        inputStream.read(bytes);
        outputStream.write(bytes);
        outputStream.write(Integer.valueOf('\n'));
        outputStream.flush();
        outputStream.close();
    }

    @Test
    public void byteArrayOutputStreamTest() throws IOException {
        byte[] bytes = {99, 111, 109, 101, 32, 111, 110, 32, 97, 110, 100, 32, 119, 97, 121, 32, 65, 66, 67};
        byte[] from = new byte[bytes.length];

        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        inputStream.read(from);
        outputStream.write(from);
        for (byte b1 : outputStream.toByteArray()) {
            if (b1 != 0) {
                System.out.println(b1);
            }
        }
        System.out.println(outputStream.toString());
        System.out.println(outputStream.size());
        inputStream.close();
        outputStream.close();
    }

    @Test
    public void dataOutputStreamTest() throws Exception {
        FileInputStream is = new FileInputStream("testfile\\myfile\\my.txt");
        FileOutputStream os = new FileOutputStream("testfile\\myfile\\to.txt");
        DataInputStream inputStream = new DataInputStream(is);
        DataOutputStream outputStream = new DataOutputStream(os);
        outputStream.writeBytes(inputStream.readLine());
        is.close();
        os.close();
        inputStream.close();
        outputStream.close();
    }

    @Test
    public void bufferedOutputStreamTest() throws Exception {
        FileInputStream is = new FileInputStream("testfile\\myfile\\my.txt");
        FileOutputStream os = new FileOutputStream("testfile\\myfile\\to.txt");

        BufferedInputStream inputStream = new BufferedInputStream(is);
        BufferedOutputStream outputStream = new BufferedOutputStream(os);
        byte[] bytes = new byte[1024];
        inputStream.read(bytes);
        outputStream.write(bytes);
        outputStream.flush();

        is.close();
        os.close();
        inputStream.close();
        outputStream.close();
    }

    @Test
    public void sparseArrayTest() {
        int[][] chessArr = new int[11][11];
        chessArr[3][3] = 1;
        chessArr[4][4] = 2;
        chessArr[2][3] = 1;
        chessArr[2][2] = 2;
        int size = 0;
        for (int[] aByte : chessArr) {
            for (int b1 : aByte) {
                if (b1 != 0) {
                    size++;
                }
            }
        }
        int sparseArray[][] = new int[size + 1][3];
        sparseArray[0][0] = chessArr.length;
        sparseArray[0][1] = chessArr[0].length;
        sparseArray[0][2] = size;
        int index = 0;
        int[][] newArr = new int[0][];
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    index++;
                    sparseArray[index][0] = i;
                    sparseArray[index][1] = j;
                    sparseArray[index][2] = chessArr[i][j];
                }
            }
        }
        try {
            File file = new File("testfile\\myfile\\sparseArray.txt");
            FileOutputStream out = new FileOutputStream(file);
            DataOutputStream os = new DataOutputStream(out);
            for (int[] ints : sparseArray) {
                for (int i : ints) {
                    String str = i + "\n";
                    os.writeBytes(str);
                }
            }

            FileInputStream in = new FileInputStream(file);
            DataInputStream is = new DataInputStream(in);
            int len = Integer.parseInt(is.readLine());
            int alen = Integer.parseInt(is.readLine());
            int asize = Integer.parseInt(is.readLine());
            newArr = new int[len][alen];
            for (int i = 0; i < asize; i++) {
                newArr[Integer.parseInt(is.readLine())][Integer.parseInt(is.readLine())] = Integer.parseInt(is.readLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int[] ints : newArr) {
            System.out.println(Arrays.toString(ints));
        }


    }

    /**
     * 字符流
     *
     * @throws Exception
     */

    @Test
    public void inputStreamReaderAndWriterTest() throws Exception {
        FileInputStream in = new FileInputStream("testfile\\myfile\\my.txt");
        FileOutputStream out = new FileOutputStream("testfile\\myfile\\to.txt");
        InputStreamReader reader = new InputStreamReader(in, "utf-8");
        OutputStreamWriter writer = new OutputStreamWriter(out, "utf-8");

        char[] chars = new char[64];
        reader.read(chars);
        writer.write(chars);
        reader.close();
        writer.flush();
        writer.close();

        for (char c : chars) {
            System.out.print(c);
        }
    }


    @Test
    public void fileReaderAndWriterTest() throws Exception {

        FileReader reader = new FileReader("testfile\\myfile\\my.txt");
        FileWriter writer = new FileWriter("testfile\\myfile\\to.txt", true);
        char[] chars = new char[64];
        if (reader.ready()) {
            reader.read(chars);
            writer.write(chars);
            writer.write("\n");
        } else {
            System.out.println("reader还没有准备就绪");
        }
        reader.close();
        writer.flush();
        writer.close();
    }

    @Test
    public void stringReaderAndWriterTest() throws IOException {
        String from = "Hi,xiaogaigai";
        char[] chars = new char[20];
        StringReader reader = new StringReader(from);
        StringWriter writer = new StringWriter();
        reader.read(chars);
        writer.write("myname is bx");
        writer.append("\nyou are so breautiful");
        System.out.println(Arrays.toString(chars));
        System.out.println(writer.getBuffer());

        reader.close();
        writer.close();
    }

    @Test
    public void charArrayReaderAndWriterTest() throws IOException {
        char[] chars = {'a', 'b', 'c', 'd'};
        char[] dest = new char[10];
        CharArrayReader reader = new CharArrayReader(chars);
        CharArrayWriter writer = new CharArrayWriter();
        reader.read(dest);
        writer.write(dest);
        writer.append("xyz");
        String toStr = writer.toString();
        System.out.println(dest);
        System.out.println(toStr);
    }

    @Test
    public void bufferedReaderAndWriterTest() throws Exception {
        FileInputStream fis = new FileInputStream("testfile\\myfile\\my.txt");
        FileOutputStream fos = new FileOutputStream("testfile\\myfile\\to.txt");
        InputStreamReader in = new InputStreamReader(fis, "utf-8");
        OutputStreamWriter out = new OutputStreamWriter(fos, "utf-8");
        BufferedReader reader = new BufferedReader(in);
        BufferedWriter writer = new BufferedWriter(out);
        char[] chars = new char[20];
        String s = reader.readLine();
        while (s != null) {
            System.out.println(s);
            writer.write(s);
            writer.newLine();
            s = reader.readLine();
        }
        writer.flush();

        reader.close();
        writer.close();
    }

    @Test
    public void printWriterTest() throws IOException {
        FileWriter fileWriter = new FileWriter("testfile\\myfile\\my.txt", true);
        PrintWriter writer = new PrintWriter(fileWriter, true);
        writer.println("\n我想认识一下你");
        writer.append("you are so breautiful");
        writer.flush();
        writer.close();
    }

    @Test
    public void scannerTest() {
        System.out.println("请输入你的兴趣爱好(两个)：");
        Scanner scanner = new Scanner(System.in, "utf-8");
        int i = 0;
        while (i < 2) {
            i++;
            String s = scanner.nextLine();
            System.out.println("第" + i + "个" + ":" + s);
        }
        scanner.close();
    }

    /**
     * 字节流 基类(抽象类)：InputStream OutputStream
     * 1.FileInputStream FileOutputStream
     * 2.ByteArrayInputStream ByteArrayOutputStream
     * 3.DataInputStream DataOutputStream
     * 4.BufferedInputStream BufferedOutputStream
     * <p>
     * 字符流 基类（抽象类）：Reader Writer
     * 1.InputStreamReader InputStreamWriter
     * 2.FileReader FileWriter
     * 3.StringReader StringWriter
     * 4.CharArrayReader CharArrayWriter
     * 5.BufferedReader BufferedWriter
     * 6.PrintWriter
     * 7.Scanner
     */


    @Test
    public void propertitesTest() throws Exception {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("testfile\\myfile\\myjson.txt");
        InputStreamReader in = new InputStreamReader(fis, "utf-8");
        BufferedReader reader = new BufferedReader(in);
        properties.load(reader);
        System.out.println(properties.getProperty("name"));
        properties.setProperty("phone", "10001");
        properties.put("sex", "man");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("testfile\\myfile\\myjson.txt"),
                "utf-8"));

        properties.store(writer, "add data\nupdate");
        writer.flush();
        properties.list(System.out);
        reader.close();
        writer.close();
    }


    @Test
    public void csvTest() {

    }

    @Test
    public void excelTest() {

    }

    @Test
    public void htmlTest() {

    }

    @Test
    public void gzipTest() throws Exception {
        GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream("testfile\\my.zip"));
        String str = "shanghai";
        out.write(str.getBytes("utf-8"));
        out.close();

        GZIPInputStream in = new GZIPInputStream(new FileInputStream("testfile\\my.zip"));
        byte[] bytes = new byte[10];
        in.read(bytes);
        System.out.println(new String(bytes));
    }


    public static void addFileToZipOut(File file, ZipOutputStream out, String rootPath) throws IOException {
        String relativePath = file.getCanonicalPath().substring(rootPath.length());
        if (file.isFile()) {
            out.putNextEntry(new ZipEntry(relativePath));
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            try {
                copyStream(in, out);
            } finally {
                in.close();
            }
        } else {
            out.putNextEntry(new ZipEntry(relativePath + "/"));
            for (File f : file.listFiles()) {
                addFileToZipOut(f, out, rootPath);
            }
        }
    }

    public static void copyStream(InputStream in, OutputStream out) throws IOException {
        int res = 0;
        while ((res = in.read()) != -1) {
            out.write(res);
        }
    }

    @Test
    public void zipTest() throws Exception {
        File inFile = new File("D:\\IDEA\\projects\\Test\\testfile");
        File zipFile = new File("D:\\IDEA\\projects\\Test\\my.zip");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
        inFile = inFile.getCanonicalFile();
        String rootPath = inFile.getParent();
        //System.out.println(rootPath);
        if (!rootPath.endsWith(File.separator)) {
            rootPath += File.separator;
        }
        try {
            addFileToZipOut(inFile, out, rootPath);
        } finally {
            out.close();
        }
    }

    @Test
    public void unZip() throws Exception {
        File zipFile = new File("D:\\IDEA\\projects\\Test\\my.zip");
        //File parseFile = new File("D:\\IDEA\\projects\\Test\\unzip");
        File parseFile = new File("C:\\Users\\sxiansen\\Desktop\\zip");
        System.out.println(parseFile.setReadable(true));
        if (parseFile.isFile()) {
            throw new Exception("解压路径不能是文件，必须是一个目录");
        }
        parseFile = parseFile.getCanonicalFile();
        String parsePath = parseFile.getCanonicalPath();
        if (!parsePath.endsWith(File.separator)) {
            parsePath += File.separator;
        }
        ZipInputStream in = null;
        try {
            in = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));
            ZipEntry nextEntry = in.getNextEntry();
            while (nextEntry != null) {
                parseZip(nextEntry, in, parsePath);
                nextEntry = in.getNextEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }

    }

    public static void parseZip(ZipEntry entry, ZipInputStream in, String parsePath) throws IOException {
        if (!entry.isDirectory()) {
            File parent = (new File(parsePath + entry.getName())).getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            OutputStream out = new BufferedOutputStream(new FileOutputStream(parsePath + entry.getName()));
            try {
                copyStream(in, out);
            } finally {
                out.close();
            }
        } else {
            new File(parsePath + entry.getName()).mkdirs();
        }
    }


    @Test
    public void randomAccessFileTest() throws Exception {
        File file = new File("testfile\\myfile\\my.txt");
        RandomAccessFile raf = new RandomAccessFile(file, "rwd");
        /*System.out.println(raf.getFilePointer());
        raf.seek(1);
        System.out.println(raf.readLine());
        raf.seek(raf.length()-3);
        raf.write(97);*/
        byte[] bytes = new byte[100];
        /*raf.read(bytes);
        System.out.println(Arrays.toString(bytes));
        raf.setLength(100L);*/
        //raf.seek(90);
        try {
            raf.readFully(bytes);
        } catch (Exception e) {
            throw new Exception("没有读满数组");
        }
        System.out.println(raf.getFilePointer());
        System.out.println(raf.length());
    }

    @Test
    public void memoryMappedFileTest() throws Exception {
        //获取内存映射文件对象有两种方式：
        //第一种
        /*FileInputStream in = new FileInputStream("testfile\\myfile\\my.txt");
        FileOutputStream out = new FileOutputStream("testfile\\myfile\\my.txt");
        FileChannel channel1 = in.getChannel();
        FileChannel channel2 = out.getChannel();*/

        //第二种
        RandomAccessFile randomAccessFile = new RandomAccessFile("testfile\\myfile\\my.txt", "rwd");
        FileChannel channel3 = randomAccessFile.getChannel();
        System.out.println(randomAccessFile.length());
        MappedByteBuffer map = channel3.map(FileChannel.MapMode.READ_WRITE, 0L, 64);
        map = map.load();
        System.out.println(map.position());
        System.out.println(randomAccessFile.length());
        byte[] bytes = new byte[(int) randomAccessFile.length()];
        System.out.println(map.get(bytes, 0, bytes.length));
        System.out.println(Arrays.toString(bytes));
        System.out.println(map.position());
        map.position(20);
        map.put("\nyou are so breautiful".getBytes());
        map.force();
        channel3.close();
    }

    @Test
    public void objectOutputStreamTest() throws Exception {
        PersonInfo personInfo = new PersonInfo("wangxiaobo", "9527", "火星", "10000");
        ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream("testfile\\myfile\\person.txt", true)));
        out.writeObject(personInfo);
        out.flush();
        out.close();
    }

    @Test
    public void objectInputStreamTest() throws Exception {
        ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream("testfile\\myfile\\person.txt")));
        System.out.println(in.readObject());
        in.close();
    }

    @Test
    public void objectMapperTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        PersonInfo zhansan = new PersonInfo("zhangsan", "9999", "北京市朝阳区派出所", "10001");
        /*mapper.writeValue(new File("testfile\\myfile\\person.txt"), zhansan);
        mapper.writeValue(new BufferedOutputStream(new FileOutputStream("testfile\\\\myfile\\\\person.txt",true)),
                zhansan);*/
        PersonInfo readValue = mapper.readValue(new File("testfile\\myfile\\person.txt"), PersonInfo.class);
        System.out.println(readValue);
        MappingIterator<PersonInfo> readValue1 = mapper.readValues(new JsonFactory().
                createParser(new File("testfile\\myfile\\person.txt")), PersonInfo.class);
        while (readValue1.hasNextValue()) {
            System.out.println(readValue1.nextValue());
        }

    }

    /**
     * Properties:使用load方法加载一个字节流或字符流，然后进行读写property
     * <p>
     * GZIPInputStream,GZIPOutputStream:压缩单个文件
     * <p>
     * ZipInputStream,ZipOutputStream:可以压缩多个文件
     * <p>
     * RandomAccessFile:可以随机读写文件，可以指定对文件的访问模式（r,rw,rws,rwd），可以同时读写，内部有一个保存当前读写位置的指针
     * <p>
     * 内存映射文件：由FileInputStream或FileOutputStream或RandomAccessFile调用getChannel获取得到内存映射文件对象（FileChannnel），
     * 该对象调用map方法可以创建内存中的映射字节数组（返回MapppedByteBuffer对象，该对象中有一个保存当前读写位置的属性），
     * 可以同时读写。
     * <p>
     * 以上都不能读写一个对象：
     * java标准序列化：对象必须实现Serializable接口，表示这个类的属性可以被序列化或反序列化，
     * 使用ObjectInputStream（readObject）和ObjectOutputStream（writeObject）将java对象转换成字节流。
     * 不能同时读写。
     * 自定义序列化内容：使用transient关键字修饰属性（static成员属性同样不会被序列化），或者自己实现readObject和WriteObject方法。
     * <p>
     * 使用Jackson来对java对象进行序列化：可以同时读写
     * 序列化为json格式：使用ObjectMapper类
     * 序列化为xml格式：使用XmlMapper类
     */


    class OneThread extends Thread {

        @Override
        public void run() {
            long begin = System.currentTimeMillis();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < 100000; i++) {
                //Thread.yield();
                list.add(i);
            }
            long end = System.currentTimeMillis();
            System.out.println(end - begin);
            System.out.println("this is oneThread");
        }
    }

    class TwoThread implements Runnable {
        @Override
        public void run() {
            try {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = 0; i < 100000; i++) {
                    list.add(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void threadTest() throws InterruptedException {
        OneThread t1 = new OneThread();
        Thread t2 = new Thread(new TwoThread());
        t1.start();
        t2.start();
        System.out.println(t1.getPriority());
        System.out.println(t1.getPriority());
        t1.join();
        t2.join();
    }

    class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                synchronized (this) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("ThreadA:" + i);
            }
        }
    }

    @Test
    public void waitAndNotifyTest() throws InterruptedException {
        ThreadA threadA = new ThreadA();
        threadA.start();
        Thread.sleep(2000);
        synchronized (threadA) {
            threadA.notifyAll();
        }
    }

    class TaskQueue {
        private Queue<Integer> queue;
        private int limit;

        public TaskQueue() {
        }

        public TaskQueue(Queue queue, int limit) {
            this.queue = queue;
            this.limit = limit;
        }

        public synchronized void addTask(int taskId) throws InterruptedException {
            while (queue.size() == limit) {
                wait();
                ;
            }
            queue.add(taskId);
            //System.out.println("加："+queue.size());
            notifyAll();
        }

        public synchronized int takeTask() throws InterruptedException {
            while (queue.isEmpty()) {
                wait();
            }
            Integer poll = queue.poll();
            //System.out.println("取："+queue.size());
            notifyAll();
            return poll;
        }

        public Queue getQueue() {
            return queue;
        }

        public void setQueue(Queue queue) {
            this.queue = queue;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }
    }

    class TaskProducer extends Thread {

        private TaskQueue queue;

        public TaskProducer(TaskQueue queue) {
            this.queue = queue;
        }

        public void produce() throws InterruptedException {
            for (int i = 0; i < 20; i++) {
                queue.addTask(i);
                System.out.println("添加task：" + i);
            }
        }

        @Override
        public void run() {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class TaskConsumer extends Thread {

        private TaskQueue queue;

        public TaskConsumer(TaskQueue queue) {
            this.queue = queue;
        }

        public void consume() throws InterruptedException {
            for (int i = 0; i < 20; i++) {
                queue.takeTask();
                System.out.println("取走task：" + i);
                Thread.sleep((long) Math.random() * 100);
            }
        }

        @Override
        public void run() {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void producerAndConsumerTest() throws InterruptedException {
        TaskQueue queue = new TaskQueue(new PriorityQueue(), 10);
        TaskProducer producer = new TaskProducer(queue);
        TaskConsumer consumer = new TaskConsumer(queue);
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

    /**
     * 线程：
     * Thread类表示线程：创建线程有两种方式
     * 1.继承Thread，重写run方法
     * 2.通过Thread构造方法，传递一个Runnable类型的匿名对象
     * <p>
     * 多线程的问题：
     * 1.竞态条件，多个线程访问同一个对象时，会发生意外的结果
     * 2.内存可见性
     * <p>
     * 解决：
     * 1.synchronized
     * 2.显示锁
     * 3.原子变量（CAS，volatile）
     * <p>
     * 线程的协作机制：
     * 1.wait
     * 2.notify|notifyAll
     * 3.interrupt
     */


    @Test
    public void atomicIntegerTest() {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(10);
        atomicInteger.set(20);
        atomicInteger.set(10);
        atomicInteger.compareAndSet(10, 20);
        //atomicInteger.addAndGet(10);
        //atomicInteger.getAndAdd(10);
        System.out.println(atomicInteger);
    }

    @Test
    public void atomicStampedReferenceTest() {
        int reference = 0;
        int stamp = 0;
        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(reference, stamp);
        stampedReference.set(10, ++stamp);
        stampedReference.compareAndSet
                (stampedReference.getReference(), 20, stampedReference.getStamp(), ++stamp);
        System.out.println(stampedReference.getReference() + ":" + stampedReference.getStamp());
    }

    class MyQueue<E> {
        private Queue<E> queue = null;
        private int limit;
        private Lock lock = new ReentrantLock();
        private Condition notFull = lock.newCondition();
        private Condition notEmpty = lock.newCondition();

        public MyQueue(int limit) {
            this.queue = new ArrayDeque<>();
            this.limit = limit;
        }

        public void put(E e) throws InterruptedException {
            if (lock.tryLock()) {
                try {
                    while (queue.size() == limit) {
                        notFull.await();
                    }
                    queue.add(e);
                    notEmpty.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }

        public E take() throws InterruptedException {
            lock.lockInterruptibly();
            try {
                while (queue.isEmpty()) {
                    notEmpty.await();
                }
                E e = queue.poll();
                notFull.signalAll();
                return e;
            } finally {
                lock.unlock();
            }
        }
    }


    @Test
    public void reentrantLockTest() throws InterruptedException {
        MyQueue<Integer> myQueue = new MyQueue<>(10);
        Thread t1 = new Thread(new Runnable() {
            private MyQueue<Integer> queue = myQueue;

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 20; i++) {
                        queue.put(i);
                        System.out.println("put：" + i);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            private MyQueue<Integer> queue = myQueue;

            @Override
            public void run() {
                try {

                    for (int i = 0; i < 20; i++) {
                        queue.take();
                        System.out.println("take：" + i);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    @Test
    public void copyOnWriteArrayListTest() {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("A");
        list.add(1, "code");
        list.addIfAbsent("A");
        list.addIfAbsent("utf-8");
        list.remove("A");
        System.out.println(list.get(1));
        System.out.println(list.contains("utf-8"));
        System.out.println(list);
    }

    @Test
    public void copyOnWriteArraySetTest() {
        CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();
        set.add(1);
        set.add(2);
        set.add(10);
        set.add(10);
        set.add(3);
        set.add(3);
        set.removeIf((t) -> t > 9);
        set.forEach((t) -> System.out.println(t));
        System.out.println(set);
    }

    @Test
    public void conCurrentHashMapTest() {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "wangxiaobo");
        map.putIfAbsent(1, "zhansan");
        map.put(2, "shuidi");
        map.getOrDefault(3, "null");
        map.forEach(new BiConsumer<Integer, String>() {
            @Override
            public void accept(Integer integer, String s) {
                if (s.contains("wang") || s.startsWith("shui")) {
                    System.out.println(integer + ":" + s);
                }
            }
        });
        System.out.println(map);
        ConcurrentHashMap<Integer, Boolean> hashMap = new ConcurrentHashMap<>();
        Set<Integer> set = Collections.newSetFromMap(hashMap);
        set.add(1);
        set.add(100);
        System.out.println(set);
    }

    @Test
    public void concurrentSkipListMapTest() {
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();
        map.put(1, "A");
        System.out.println(map);
    }

    @Test
    public void concurrentSkipListSetTest() {
        ConcurrentSkipListSet<Integer> set = new ConcurrentSkipListSet<>();
        set.add(10);
        set.add(20);
        set.add(10);
        System.out.println(set);
    }

    static class DelayTask implements Delayed {

        long maxWait;

        public DelayTask(long maxWait) {
            this.maxWait = maxWait;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.toMillis(maxWait);
        }

        @Override
        public int compareTo(Delayed o) {
            return compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }

        public int compare(long x, long y) {
            return (x < y) ? -1 : ((x == y) ? 0 : 1);
        }

        @Override
        public String toString() {
            return "DelayTask{" +
                    "maxWait=" + maxWait +
                    '}';
        }
    }

    @Test
    public void concurrentQueueTest() throws InterruptedException {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.add(1);
        System.out.println(queue);
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(32);
        blockingQueue.offer(1, 1000, TimeUnit.MILLISECONDS);
        System.out.println(blockingQueue);

        DelayQueue<DelayTask> delayQueue = new DelayQueue<>();
        DelayTask task1 = new DelayTask(10);
        DelayTask task2 = new DelayTask(20);
        DelayTask task3 = new DelayTask(30);
        DelayTask task4 = new DelayTask(40);
        List<DelayTask> delayTasks = Arrays.asList(task1, task2, task3, task4);
        delayQueue.addAll(delayTasks);
        System.out.println(delayQueue.poll());
        System.out.println(delayQueue);

        PriorityBlockingQueue<Integer> pbq = new PriorityBlockingQueue<>();
    }


    /**
     * 并发包的基石：CAS+volatile
     * 并发包：concurrent/
     * atomic:原子变量包
     * locks：显示锁包
     *
     * 原子变量底层：volatile+CAS
     *
     * 显示锁：
     * Lock接口定义了lock(),unlock(),tryLock(),newCondition()...方法
     * Condition接口表示协作条件
     * Condition的方法：await signal signalAll...
     *      ReentrantLock：volatile+CAS:每个锁内部有一个volatile修饰的state变量，利用CAS判读是否锁定，0没有，1锁定。
     *          如果是同一个线程，则状态数+1
     *      ReentrantReadWriteLock：
     *      显示锁的协作机制Condition：ReentrantLock通过newCondition函数获取条件。
     *
     * 解决并发的几种方法：
     * 1.synchronized
     * 2.显示锁：ReentrantLock,ReentrantReadWriteLock
     * 3.循环CAS+volatile+版本控制(AtomicStampedReference)
     * 4.写时复制
     *
     * 并发包中的并发容器类：
     * 1.CopyOnWriteArrayList,CopyOnWriteArraySet
     * 2.ConcurrentHashMap
     * 3.ConcurrentSkipListMap,ConcurrentSkipListSet
     * 4.并发队列：
     *      无锁非阻塞队列(使用循环CAS)：ConcurrentLinkedQueue,ConcurrentLinkedDeque
     *      阻塞队列（显示锁）：ArrayBlockingQueue,LinkedBlockingQueue,LinkedBlockingDeque,PriorityBlockingQueue,SynchronousQueue
     *      其他队列:LinkedTransferQueue
     *      延时队列：DelayQueue
     */

    @Test
    public void asyncTest() throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> 1+1;
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(callable);
        System.out.println(future.get());
    }

    @Test
    public void threadPoolExecutorTest() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 20, 3,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(20));
        System.out.println(threadPoolExecutor.prestartAllCoreThreads());
        Future<Integer> future = threadPoolExecutor.submit(() -> 1 + 1);
        System.out.println(future.get());


    }

    /**
     * 异步任务接口：Runnable，Callable
     * 异步任务执行器接口：Executor，ExecutorService
     * 异步任务执行返回的接口：Future（主要实现类FutureTask）
     *
     *  线程池执行器：ThreadPoolExecutor
     *  执行器工具类：Executors
     *      三个创建预先定义好的线程池
     *      1.newSingleThreadExecutor()
     *      2.newFixedTheadPool(int threadSize)
     *      3.newCachedThreadPool()
     */


    @Test
    public void timerTest() throws InterruptedException {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("我是一个定时任务");
            }
        };
        //timerTask.cancel();
        timer.schedule(timerTask, 3000);
        Thread.sleep(5000);
    }

    @Test
    public void scheduledThreadPoolExecutorTest() throws InterruptedException, ExecutionException {
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(5);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("我是一个定时任务");
            }
        };
        ScheduledFuture<?> scheduledFuture = threadPoolExecutor.schedule(timerTask,2, TimeUnit.SECONDS);
        //getDelay方法返回当前时间到下一次执行时间的时间差
        System.out.println(scheduledFuture.getDelay(TimeUnit.SECONDS));
        System.out.println(scheduledFuture.get());
        //System.out.println(scheduledFuture.cancel(false));

        Thread.sleep(5000);
    }

    /**
     * 计时器及定时任务
     * 定时任务抽象类TimerTask，实现了Runnable接口
     *
     * 计时器：
     * 1.Timer:内部只会创建一个线程来执行任务队列中的队列，处理的时TimerTask类型的任务
     *      队列是一个优先级队列，按照定时任务的下一次执行时间取出最小的定时任务来执行。
     *      任何任务发生异常，整个队列都会clear，全部执行失败。
     *      调度定时任务：
     *          schedule( ... )
     *          scheduleAtFixedRate
     *
     * 2.ScheduledThreadPoolExecutor：继承了ThreadPoolExecutor，还实现了ScheduledExecutorService(继承自ExecutorService)。
     *      内部可以创建多个线程，任务队列同样是一个优先级队列
     *      调度任务（处理的任务类型是Runnable）：
     *          schedule
     *          scheduleAtFixedRate
     *          scheduleWithFixedDelay
     *      返回值类型：ScheduledFuture（继承了Future和Delayed接口）
     * Executors同样也有静态方法可以返回已经定义好的ScheduledThreadPoolExecutor
     *      newSingleThreadScheduledExecutor
     *      newScheduledThreadPool
     */

    @Test
    public void semephoreTest(){
        Semaphore sem = new Semaphore(10);
        if (!sem.tryAcquire()){
            System.out.println("获取许可失败");
            sem.release();
        }else{
            System.out.println("已获取到许可认证！");
        }
    }


    class worker extends Thread {
        CountDownLatch latch;

        public worker(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep((int) (Math.random() * 1000));
                if (Math.random() < 0.02)
                    throw new RuntimeException("bad luck");
            } catch (InterruptedException e) {
            } finally {
                latch.countDown();
            }
        }
    }




    @Test
    public void countDownLatchTest() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(100);
        worker[] workers = new worker[100];
        for (worker worker : workers) {
            worker = new worker(latch);
            worker.start();
        }
        latch.await();
        System.out.println("master collect results");
    }

    static ThreadLocal local = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return new PersonInfo();
        }
    };

    @Test
    public void threadLocalTest(){
        ThreadLocalRandom current = ThreadLocalRandom.current();
        System.out.printf("%.3f", current.nextDouble());

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {

                System.out.println(local.get());
            }
        });
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(local.get());
            }
        });
        t1.start();
        t2.start();
    }

    /**
     * Semaphore
     * CountDownLatch
     * CyclicBarrier
     * ThreadLocal
     */




    @Test
    public void fieldTest() throws ClassNotFoundException, IllegalAccessException {
        Review b1 = new B();
        System.out.println(b1.getClass());
        System.out.println(B.class);
        System.out.println(Class.forName("review.Review$B"));
        Class<? extends Review> bClass = b1.getClass();
        System.out.println("----Class名称----");
        System.out.println(bClass.getName());
        System.out.println(bClass.getCanonicalName());
        System.out.println(bClass.getSimpleName());
        System.out.println(bClass.getPackage());
        System.out.println("----字段---");
        Field[] fields = bClass.getFields();
        System.out.println(Arrays.toString(fields));
        Field[] declaredFields = bClass.getDeclaredFields();
        System.out.println(Arrays.toString(declaredFields));
        System.out.println(declaredFields[0].getModifiers());
        System.out.println(declaredFields[0].getType());
        System.out.println(declaredFields[0].getGenericType());
    }

    @Test
    public void methodTest() throws NoSuchMethodException {
        Review b1 = new B();
        Class<? extends Review> b1Class = b1.getClass();
        Method[] methods = b1Class.getMethods();
        Method[] declaredMethods = b1Class.getDeclaredMethods();
        System.out.println(Arrays.toString(methods));
        System.out.println(Arrays.toString(declaredMethods));
        Class<Comparator> comparatorClass = Comparator.class;
        Class<HashMap> hashMapClass = HashMap.class;
        Method get = hashMapClass.getDeclaredMethod("get", Object.class);
        System.out.println(get.getReturnType());
        System.out.println(get.getGenericReturnType());
    }

    @Test
    public void constructorTest() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<HashMap> hashMapClass = HashMap.class;
        Constructor<?>[] constructors = hashMapClass.getConstructors();
        System.out.println(constructors.length);
        Constructor<?>[] constructors1 = Review.class.getConstructors();
        Object o = constructors1[0].newInstance();
        Review review = Review.class.cast(o);
        System.out.println(review);
        HashMap map = hashMapClass.newInstance();
        System.out.println(hashMapClass.isInstance(map));
        map.put(1,1);
        map.put("Str", "str");
        System.out.println(map);

        System.out.println(Object.class.isAssignableFrom(Integer.class));
        System.out.println(Integer.class.isAssignableFrom(Object.class));

        HashSet<Integer> set = new HashSet<>();
        System.out.println(Arrays.toString(set.getClass().getTypeParameters()));
    }

    @Test
    public void zhujieTest() throws NoSuchMethodException {

        Method zhujieTest = Review.class.getMethod("zhujieTest");
        Test test = zhujieTest.getAnnotation(Test.class);
        System.out.println(test.timeout());
        System.out.println(Arrays.toString(Test.class.getMethods()));
    }


    class MyCall implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            return 1+1;
        }
    }

    class MyHander implements InvocationHandler {
        MyCall myCall;

        public MyHander(MyCall myCall) {
            this.myCall = myCall;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("增强前：");
            System.out.println(method.invoke(myCall));
            System.out.println("增强后：");
            return null;
        }
    }

    @Test
    public void proxyTest() throws Exception {
        Callable porxy = (Callable) Proxy.newProxyInstance
                (MyCall.class.getClassLoader(), new Class[]{Callable.class}, new MyHander(new MyCall()));
        porxy.call();
    }

    static class MyRun {

        public MyRun() {
        }

        public void run(){
            System.out.println("我是被增强的方法");
        }
    }

    class MyCallBack implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("增强前：");
            methodProxy.invokeSuper(o, objects);
            System.out.println("增强后：");
            return null;
        }
    }

    @Test
    public void cglibTest(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyRun.class);
        enhancer.setCallback(new MyCallBack());
        MyRun run = (MyRun) enhancer.create();
        run.run();
    }

    @Test
    public void classLoaderTest(){
        System.out.println(ClassLoader.getSystemClassLoader());
    }


    /**
     * 反射：在运行时动态的获取Class信息
     * 获取方式：
     *  1.(Object类定义了一个getClass方法)实例对象.getClass(),数组变量名.getClass()
     *  2.基本类型.class,类名.class，接口.class，数组类型.class
     *  3.Class中的静态方法：forName
     *
     * 通过Class对象可以获取类定义的一切信息，Filed字段，Method方法，Constructor构造器，Annotation注解等
     *
     * 注解：定义注解使用@interface关键字
     * 注解表示给程序添加一些信息，使用时方式：@注解名(属性=值)
     * 注解中的属性定义：数据类型 属性名();
     *
     * 动态代理：给被代理类增强功能
     * 两种方式：1.jdk动态代理，代理的是接口，实际被代理的类是该接口的实现类
     *          使用Proxy类，调用newInstance方法创建代理类并返回代理类实例对象，
     *          该方法需要传递一个类加载器，一个接口数组，一个InvocationHander类型的变量作为参数。
     *
     *          2.cglib动态代理，可以代理接口和类，
     *          创建的代理类它会继承重写父类的所有public非final方法改为调用传递的CallBack中的方法
     *          使用Enhancer类来实现：
     *          Enhancer en = new Enhancer();
     *          en.setSuperCalss(Class superClass);设置代理类
     *          en.setCallBack(CallBack call);设置具体进行增强的类
     *          en.create();创建并返回代理实例对象
     *
     * 类加载器ClassLoader(抽象类)，不同的类加载器加载得到的类不同。
     * Class中有一个方法可以获取加载它的类加载器：getClassLoader()
     * 自定义类加载器：继承ClassLoader，重写findClass方法
     *
     */


    @Test
    public void zhengzeTest(){
//        Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5\\w]+$");//只能是汉字和单词字符
//        Pattern pattern = Pattern.compile("^[A-Za-z0-9]{6,18}$");//验证用户密码，长度在6~18 之间，只能包含英文和数字。
//        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*?])[\\w!@#$%^&*?]{6,18}$");//验证用户密码强度，最少6位，至少包括1个大写字母、1个小写字母、1个数字和1个特殊字符。
        Pattern pattern = Pattern.compile("\\d+?[A-z]");
        Matcher a = pattern.matcher("123a");
        System.out.println(a.matches());
        System.out.println("\u4e00");

    }

    /**
     * 正则表达式：
     * \n:换行符
     * \t:制表符tab
     * \r:回车符
     * .:表示除换行符之外的任意单个字符
     * [ab]:字符组，表示内部中有的任意一个字符，[a-z][0-9][A-Z]，[]中的^表示非且只能放在最前面
     * \d:相当于[0-9],表示任意一个数字
     * \w:相当于[0-9A-Za-z_],任意一个单词字符
     * \s:匹配一个空白字符
     * \D:等于[^0-9]
     * \W:[^A-Za-z_0-9]
     * \S:与\s相反
     *
     * 量词：
     * +：表示前面的表达式出现一次或多次
     * *：表示前面的表达式出现0次或多次
     * ？：表示前面的表达式出现0次或1次
     * {m,n}：表示前面的表达式出现至少m次，最多n次，n如果不限定可以不写具体数字，n和m相同{}中可以只写m
     * 在量词后面再加一个？表示懒惰量词，尽可能少匹配
     *
     * ():表示分组，内部使用|表示或，匹配其中一个表达式，([0-9]|[a-z])+
     *
     * 限定边界：
     * ^:表示以后面的表达式开始
     * $:表示以前面的表达式结束，会自动忽略换行符
     * \b:表示以完整的单词开始或结束，不能包含
     * \B:与上相反
     * \A:与^相同
     * \Z:与$相同
     * \z:表示与前面的表达式结束，包含换行符
     * (?=表达式)：要求右边的字符串匹配指定的表达式
     * (?!表达式): 要求右边的字符串不匹配指定的表达式
     * (?<=表达式): 要求左边的字符串匹配指定的表达式
     * (?<!表达式): 要求左边的字符串不匹配指定的表达式
     *
     * 匹配模式：
     * (?i):忽略大小写
     * (?m):多行模式，一行一行匹配，^匹配行开始，$匹配行结束
     * (?s):单行模式,.匹配任意单个字符
     *
     * 中文字符的unicode编码范围：[\u4e00-\u9fff]
     */


    @Test
    public void lambdaTest(){

        Callable<Integer> callable = ()->{
            int res = 1+1;
            return res;
        };

    }

    @Test
    public void java8TimeTest(){
        Instant now = Instant.now();
        System.out.println(now);//时间不准，相差8个小时

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss:SSS E a");
        System.out.println(dateTimeFormatter.format(dateTime));

    }

    @Test
    public void mytest() {
        String a = "abxccc";
        TreeMap<Integer, String> map = new TreeMap<>();
        System.out.println(map.size());
        System.out.println(a.substring(0, 2));
    }
}
