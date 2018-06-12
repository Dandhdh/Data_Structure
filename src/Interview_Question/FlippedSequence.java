package Interview_Question;

import java.util.Scanner;

/**
 * 腾讯笔试题
 * 翻转数列：
 *
 * 小Q定义了一种数列称为翻转数列:
 * 给定整数n和m, 满足n能被2m整除。对于一串连续递增整数数列1, 2, 3, 4..., 每隔m个符号翻转一次, 最初符号为'-';。
 * 例如n = 8, m = 2, 数列就是: -1, -2, +3, +4, -5, -6, +7, +8.
 * 而 n = 4, m = 1, 数列就是: -1, +2, -3, + 4.
 * 小Q现在希望你能帮他算算前n项和为多少
 */

/**
 * 输入描述:
 * 输入包括两个整数n和m(2 <= n <= 109, 1 <= m), 并且满足n能被2m整除。
 *
 * 输出描述:
 * 输出一个整数, 表示前n项和。
 *
 * 输入例子
 * 8 2
 *
 * 输出例子
 * 8
 * （ps：-1, -2, +3, +4, -5, -6, +7, +8 相加）
 */

//注意：由于题目要求的n的范围是最大是10的9次方，所以用int来表示就会出现溢出的情况。
//     所以我们需要一个更大的数据类型，这时就需要使用long来作为该程序的数据类型。
public class FlippedSequence {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        long m = sc.nextInt();

        long multi = n/(2*m);

        System.out.println(multi*m*m);

    }
}
