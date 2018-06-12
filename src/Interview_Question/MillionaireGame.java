package Interview_Question;

/**
 * 大富翁游戏：
 * 玩家根据骰子的点数决定走的步数，即骰子点数为1时可以走一步，点数为2时可以走两步，点数为n时可以走n步。
 * 求玩家走到第n步（n<=骰子最大点数且是方法的唯一入参）时，总共有多少种投骰子的方法。
 */

import java.util.Scanner;

/**
 * 输入描述:
 * 输入包括一个整数n,(1 ≤ n ≤ 6)
 *
 * 输出描述:
 * 输出一个整数,表示投骰子的方法
 *
 * 输入例子1:
 * 6
 *
 * 输出例子1:
 * 32
 */
public class MillionaireGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        int total = solution(num);
        System.out.println(total);
    }

    public static int solution(int num){
        if (num==0){
            return 0;
        }
        int[] dp = new int[num+1];
        dp[0]=1;
        dp[1]=1;
        // dp[n] = dp[0]+dp[1]+...dp[n-1]
        for(int i = 2;i<=num;i++){
            dp[i] = 0;
            for(int j = 0;j < i;j++) {
                dp[i] += dp[j];
            }
        }
        return dp[num];
    }

}
