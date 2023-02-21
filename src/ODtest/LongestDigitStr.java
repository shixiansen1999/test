package ODtest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author bx
 * @create 2023-01-09 15:04
 * 在字符串中找出最长连续的数字字符串
 */
public class LongestDigitStr {
    @Test
    public void getLongestDigitStr(){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.next();
            char[] chars = s.toCharArray();
            StringBuffer buffer = new StringBuffer();
            System.out.println(buffer.toString().equals(""));
            ArrayList<String> list = new ArrayList<>();
            int max = 0;
            //获取数字串集合
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] <= '9' && chars[i] >= '0') {
                    buffer.append(chars[i]);
                }else {
                    list.add(buffer.toString());
                    buffer = new StringBuffer();
                }
            }
            list.add(buffer.toString());
            System.out.println(list.toString());
            for (String str : list) {
                max = Math.max(max, str.length());
            }
            buffer = new StringBuffer();
            buffer.append("最长的数字字串为");
            for (String s1 : list) {
                if (max == s1.length()) {
                    buffer.append(s1+"，");
                }
            }
            buffer.append("长度为" + max);
            System.out.println(buffer.toString());
        }

    }
}
