package Interview_Question;

/**
 * 百度笔试编程题：
 * 0-1 排序
 * 给定一个01串（仅由‘ 0’或‘1’组成的字符串），现在想把这个数字串排序成“非递减”有序序列，
 * 请问至少需要多少次交换（任意两个位置交换）？
 */

import java.util.Scanner;

/**
 * 输入描述:
 * 输入数据第一行是一个正整数T(T<=100),表示有T组测试数据；
 * 接下来的T行，每行给出01串。
 * 数据保证——
 * 50%的字符串长度在[1,100 ]
 * 95%的字符串长度在[1,10000]
 * 100%的字符串长度在[1,1000000]
 *
 *
 * 输出描述:
 * 对于每组测试数据，请输出排成“非递减有序序列”的最小交换次数。
 * 每组输出占一行。
 *
 * 输入例子1:
 * 3
 * 01
 * 10
 * 110
 *
 * 输出例子1:
 * 0
 * 1
 * 1
 */

/**
 * 解题思路：
 * 需要统计两个数据
 * （1）每串01字符串中，0的个数，设为n
 * （2）第n位数据之前，有多少个0
 */
public class OneZeroSort {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for (int i=0; i<n; i++){
            String str = sc.nextLine();
            char[] chars = str.toCharArray();

            // 零的个数
            int zeroNum = 0;
            // 每一位前，0的个数
            int[] zero = new int[chars.length+1];
            zero[0] = 0;
            for (int j=0; j<chars.length; j++){
                if (chars[j]=='0'){
                    zeroNum++;
                    zero[j+1] = zero[j]+1;
                }else {
                    zero[j+1] = zero[j];
                }
            }
            System.out.println(zeroNum-zero[zeroNum]);
        }
    }
}
