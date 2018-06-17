package Interview_Question;

/**
 * 百度编程题
 * 转移罪犯
 * C市现在要转移一批罪犯到D市，C市有n名罪犯，按照入狱时间有顺序
 * ，另外每个罪犯有一个罪行值，值越大罪越重。现在为了方便管理，市长决定转移入狱时间连续的c名犯人，
 * 同时要求转移犯人的罪行值之和不超过t，问有多少种选择的方式？
 */

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

/**
 * 输入描述:
 * 第一行数据三个整数:n，t，c(1≤n≤2e5,0≤t≤1e9,1≤c≤n)，
 * 第二行按入狱时间给出每个犯人的罪行值ai(0≤ai≤1e9)
 * <p>
 * 输出描述:
 * 一行输出答案
 * <p>
 * 输入例子
 * 3 100 2
 * 1 2 3
 * <p>
 * 输出例子
 * 2
 */

/**
 * 思路：
 * 其实这题可以用动态规划的思想解决，
 * 首先计算前c个罪行值之和，然后每移动一个单位，则减去第一个元素，再加上新增加的元素，中间的元素都复用了。
 * 这样的话复杂度就变为线性的了。
 */

public class TransferCriminals {
    // 在n个中选择c个的问题,组合问题，递归，找出所有的组合，符合条件的count++
    public int transport(int n, int t, int c, int[] a) {
        int count = 0;
        int sum = 0;
        if (a == null || n <= 0 || t < 0 || c <= 0) {
            return 0;
        }
        // 建立起始滑动窗口
        for (int i = 0; i < c; i++) {
            sum += a[i];
        }
        if (sum <= t)
            count++;
        for (int i = c; i < n; i++) {
            // 滑动：往后加一个往前减一个
            sum = sum + a[i] - a[i - c];
            if (sum <= t)
                count++;
        }
        return count;

    }

    public static void main(String[] args) {
        // 第一行数据三个整数:n，t，c(1≤n≤2e5,0≤t≤1e9,1≤c≤n)，
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();// 共n名罪犯
            int t = sc.nextInt();// 最大罪行值之和
            int c = sc.nextInt();// 转移c名犯人
            // 第二行按入狱时间给出每个犯人的罪行值ai(0≤ai≤1e9)
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = sc.nextInt();
            TransferCriminals convict = new TransferCriminals();
            System.out.println(convict.transport(n, t, c, a));
        }
    }

}