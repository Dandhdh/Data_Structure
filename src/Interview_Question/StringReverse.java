package Interview_Question;

/**
 * 利用递归反转字符串
 */
public class StringReverse {

    public static String reverse(String str) {
        if ((null == str) || (str.length()  <= 1)) {
            return str;
        }
        return reverse(str.substring(1)) + str.charAt(0);
    }

    public static void main(String[] args) {
        String str = "abcdefg";
        System.out.println(reverse(str));
    }

}