package Interview_Question;

/**
 * 腾讯笔试题
 *
 * 牛牛和羊羊正在玩一个纸牌游戏。这个游戏一共有n张纸牌, 第i张纸牌上写着数字ai。
 * 牛牛和羊羊轮流抽牌, 牛牛先抽, 每次抽牌他们可以从纸牌堆中任意选择一张抽出, 直到纸牌被抽完。
 * 他们的得分等于他们抽到的纸牌数字总和。
 * 现在假设牛牛和羊羊都采用最优策略, 请你计算出游戏结束后牛牛得分减去羊羊得分等于多少。
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * 输入描述:
 * 输入包括两行。
 * 第一行包括一个正整数n(1 <= n <= 105),表示纸牌的数量。
 * 第二行包括n个正整数ai(1 <= ai <= 109),表示每张纸牌上的数字。
 *
 * 输出描述:
 * 输出一个整数, 表示游戏结束后牛牛得分减去羊羊得分等于多少。
 *
 * 输入例子
 * 3
 * 2 7 4
 *
 * 输出例子
 * 5
 *
 */
public class CardGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        sc.nextLine();
        long[] nums = new long[num];
        for (int i=0; i<num; i++){
            nums[i] = sc.nextInt();
        }

        Arrays.sort(nums);

        long sum = 0;
        for (int i=nums.length-1; i>=0; i-=2){
            if (i==0){
                sum+=nums[0];
                break;
            }
            sum = sum+ nums[i]-nums[i-1];
        }
        System.out.println(sum);
    }
}
