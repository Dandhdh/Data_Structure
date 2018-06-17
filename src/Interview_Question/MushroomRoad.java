package Interview_Question;

/**
 * 百度编程题
 * 蘑菇阵
 * 现在有两个好友A和B，住在一片长有蘑菇的由n*m个方格组成的草地，A在(1,1),B在(n,m)。
 * 现在A想要拜访B，由于她只想去B的家，所以每次她只会走(i,j+1)或(i+1,j)这样的路线，
 * 在草地上有k个蘑菇种在格子里(多个蘑菇可能在同一方格),
 * 问：A如果每一步随机选择的话(若她在边界上，则只有一种选择)，那么她不碰到蘑菇走到B的家的概率是多少？
 */

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * 输入描述:
 * 第一行N，M，K(1 ≤ N,M ≤ 20, k ≤ 100),N,M为草地大小，
 * 接下来K行，每行两个整数x，y，代表(x,y)处有一个蘑菇。
 *
 * 输出描述:
 * 输出一行，代表所求概率(保留到2位小数)
 *
 * 输入例子1:
 * 2 2 1
 * 2 1
 *
 * 输出例子1:
 * 0.50
 */

/**
 * 解题思路：（动态规划法）
 * 0.50
 * 思路：由于每个格子除边界只能向两个方向出度，那么对于每个格子除边界也有两个地方的入度；
 * 我们把问题分解，对于当前位置的方法数是他入度方法数的*0.5的和，
 * 这里特别注意边界，由于他只有一个方向出度，所以概率不是乘以0.5而是乘以1；
 * （对了，有蘑菇的地方标记下，p=0）
 * 即，每一个方格存着从（1，1）走到此处，不碰到蘑菇的概率，且  p[i][j]+=p[i-1][j]*0.5+p[i][j-1]*0.5; (因为入度为2)
 */
public class MushroomRoad {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        // 有蘑菇处为1，其他处为0
        int[][] map = new int[n+1][m+1];
        for (int i=0; i<k; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            map[x][y] = 1;
        }

        if ((map[1][1]+map[n][m])>=1){
            System.out.println("0.00");
            return;
        }

        // 存放概率
        double[][] p = new double[n+1][m+1];
        p[1][1] = 1;


        for (int i=1; i<=n; i++){
            for (int j=1; j<=m; j++){
                if (i==1 && j==1){
                    continue;
                }
                // 此处有蘑菇
                if (map[i][j]==1){
                    p[i][j] = 0.00;
                }else { //无蘑菇
                    //在边界处
                    if (i==n) {
                        p[i][j] += p[i][j-1]*0.5;
                    }
                    if (j==m) {
                        p[i][j] += p[i-1][j]*0.5;
                    }
                    p[i][j]+=p[i-1][j]*0.5+p[i][j-1]*0.5;
                }

            }
        }

        System.out.println(new DecimalFormat("0.00").format(p[n][m]));
    }
}
