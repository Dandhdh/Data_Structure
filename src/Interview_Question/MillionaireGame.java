package Interview_Question;

/**
 * ������Ϸ��
 * ��Ҹ������ӵĵ��������ߵĲ����������ӵ���Ϊ1ʱ������һ��������Ϊ2ʱ����������������Ϊnʱ������n����
 * ������ߵ���n����n<=�������������Ƿ�����Ψһ��Σ�ʱ���ܹ��ж�����Ͷ���ӵķ�����
 */

import java.util.Scanner;

/**
 * ��������:
 * �������һ������n,(1 �� n �� 6)
 *
 * �������:
 * ���һ������,��ʾͶ���ӵķ���
 *
 * ��������1:
 * 6
 *
 * �������1:
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
