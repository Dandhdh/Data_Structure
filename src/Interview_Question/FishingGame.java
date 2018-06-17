package Interview_Question;

/**
 * 百度编程题
 * 钓鱼比赛
 * ss请cc来家里钓鱼，鱼塘可划分为n*m的格子，每个格子有不同的概率钓上鱼，
 * cc一直在坐标(x,y)的格子钓鱼，而ss每分钟随机钓一个格子。
 * 问t分钟后他们谁至少钓到一条鱼的概率大？为多少？
 */

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * 输入描述:
 * 第一行五个整数n,m,x,y,t(1≤n,m,t≤1000,1≤x≤n,1≤y≤m);
 * 接下来为一个n＊m的矩阵，每行m个一位小数，共n行，第i行第j个数代表坐标为(i,j)的格子钓到鱼的概率为p(0≤p≤1)
 * <p>
 * 输出描述:
 * 输出两行。第一行为概率大的人的名字(cc/ss/equal),
 * 第二行为这个概率(保留2位小数)
 * <p>
 * 输入例子
 * 2 2 1 1 1
 * 0.2 0.1
 * 0.1 0.4
 * <p>
 * 输出例子
 * equal
 * 0.20
 */
public class FishingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int x = sc.nextInt();
        int y = sc.nextInt();

        int time = sc.nextInt();

        double[][] pond = new double[n][m];
        double proSum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                double pro = sc.nextDouble();
                pond[i][j] = pro;
                proSum += pro;
            }
        }
        //保留两位小数
        DecimalFormat df = new DecimalFormat("0.00");

        double pro1 = 1 - pond[x - 1][y - 1];
        pro1 = 1 - Math.pow(pro1,time);
        double pro2 = 1 - proSum / (n * m);
        pro2 = 1 - Math.pow(pro2,time);

        if (pro1-pro2>=0.01) {
            System.out.println("cc");
            System.out.println(df.format(pro2));
        } else if (pro2-pro1>=0.01) {
            System.out.println("ss");
            System.out.println(df.format(pro1));
        }else {
            System.out.println("equal");
            System.out.println(df.format(pro1));
        }
    }
}
