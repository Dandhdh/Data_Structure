package Interview_Question;

/**
 * 或与加
 * 给定 x, k ，求满足 x + y = x | y 的第 k 小的正整数 y 。 | 是二进制的或(or)运算，
 * 例如 3 | 5 = 7。
 * 比如当 x=5，k=1时返回 2，因为5+1=6 不等于 5|1=5，而 5+2=7 等于 5 | 2 = 7。
 */

import java.util.Scanner;

/**
 * 输入描述：
 * 每组测试用例仅包含一组数据，每组数据为两个正整数 x , k。 满足 0 < x , k ≤ 2,000,000,000。
 *
 * 输出描述：
 * 输出一个数y
 *
 * 输入例子：
 * 5 1
 *
 * 输出例子：
 * 2
 */
public class Or_Add {

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int x = sc.nextInt();
//        int k = sc.nextInt();
//
//        int n = 0;
//        int result = 0;
//
//        for (int i = 1; i <= 2000000000; i++) {
//            if ((x + i) == (x | i)) {
//                n += 1;
//            }
//            if (n == k) {
//                result = i;
//                break;
//            }
//        }
//        System.out.println(result);
//    }
    //上面是穷尽法，会显示超时

    /**
     * 解题思路：
     * x+y 是把x和y每一个二进制位进行相加，1+1就进位
     * x|y 则是每个二进制位置对应的进行或运算（有1得1）
     *
     * 这个题目中，x的值是固定的，也就是x的二进制数中1的位置和数目是固定的，
     * 所以进行或运算的时候，结果中对应的位上都应该是1。
     *
     * 总结：
     * 所求y的二进制数应该是用k来依次填充x二进制数中的0,而x二进制数中的1则应该改为0。
     */
    public static void main(String[]args)
    {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNextInt())
        {
            int x=sc.nextInt();
            int k=sc.nextInt();

            StringBuilder sb=new StringBuilder();
            // x值的二进制
            String xB = Integer.toString(x, 2);
            // k值的二进制
            String kB = Integer.toString(k,2);

            int lengthK=kB.length();
            int index=lengthK-1;
            int lengthX= xB.length();

            for (int i = lengthX-1; i >=0 ; i--)
            {
                char c= xB.charAt(i);
                if (index>=0)
                {
                    if (c=='1'){
                        sb.append('0');
                    } else {
                        sb.append(kB.charAt(index--));
                    }
                }
                else break;
            }
            for (int i = index; i >=0 ; i--)
            {
                sb.append(kB.charAt(i));
            }
            sb.reverse();
            System.out.println(Long.parseLong(sb.toString(),2));
        }
        sc.close();
    }
}
