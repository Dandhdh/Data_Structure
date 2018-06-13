package Interview_Question;

/**
 * 腾讯笔试题：
 * 贪吃的小Q（吃巧克力）
 * 小Q的父母要出差N天，走之前给小Q留下了M块巧克力。小Q决定每天吃的巧克力数量不少于前一天吃的一半，
 * 但是他又不想在父母回来之前的某一天没有巧克力吃，请问他第一天最多能吃多少块巧克力
 */
/**
 * 输入描述:
 * 每个输入包含一个测试用例。
 * 每个测试用例的第一行包含两个正整数，表示父母出差的天数N(N<=50000)和巧克力的数量M(N<=M<=100000)。
 *
 * 输出描述:
 * 输出一个数表示小Q第一天最多能吃多少块巧克力。
 *
 * 输入例子
 * 3 7
 *
 * 输出例子
 * 4
 */

//思路：第一天吃得尽量多，以后的日子吃的尽量少（仅为前一天的二分之一）
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class EatChocolate {

    public static Set set = new HashSet();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            long days = sc.nextLong();
            long value = sc.nextLong();

            // 通过二分法来，测试第一天该吃多少合适
            // mid 表示第一天最多吃多少块巧克力
            long left = 0;
            long right = value;
            while (left < right) {
                long mid = (right - left) / 2 + left;
                int temp = isValid(mid, days, value);
                if (temp < 0) {
                    right = mid - 1;
                } else if (temp > 0) {
                    left = mid + 1;
                } else {
                    break;
                }
            }
            long res = (right - left) / 2 + left;
            if (isValid(res, days, value) < 0) {
                System.out.println(res - 1);
            } else {
                System.out.println(res);
            }
        }
        sc.close();
    }
    public static int isValid(long start, long days, long value) {
        if (set.contains(start)) {
            return -1;
        }
        // temp表示已经吃掉的巧克力块数
        long temp = 0;
        for (int i = 0; i < days; i++) {
            temp += start;
            if (temp > value) {
                // 将已经不符合的结果，保存在set中，避免下次重复运算
                set.add(start);
                return -1;
            }
            // 第二天吃的块数
            start = (long) Math.ceil((double) (start / 2.0));
        }
        // temp=1：有剩余
        // temp=0：刚好吃完
        return temp < value ? 1 : 0;
    }
}