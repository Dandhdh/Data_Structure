package Algorithm.GreedyAlgorithm;

/**
 * 问题描述：
 *
 * 割绳子
 * 给你一个长度为n的绳子，请把绳子剪成m段（m，n都是整数，且都大于1）
 * 每段绳子的长度即为K[0],K[1],K[2]...K[m]。请问K[0]*k[1]..*k[m]可能的最大乘积是多少？
 */

/**
 * 解决思路：
 * 如果我们按照如下的策略剪绳子,则得到的各段绳子的长度的乘积将最大：
 *
 * 当n>=5,我们尽可能地剪长度为3的绳子;当剩下的绳子长度为4时,把绳子剪为长度为2的绳子.
 *
 * 贪心算法的核心是通过局部最优解来得到全局最优解,对于分割问题来说,要使乘积最大,该问题的贪心思想是尽可能去剪为长度为3的绳子!
 */
public class greedy_cut_rope {

    //迭代法
    public static int greedy_cut_rope_1(int n)
    {
        if(n<2)
            return 0;
        if(n==2)
            return 1;
        if(n==3)
            return 2;
        //尽可能多地去减长度为3的绳子段
        int timesOf3 = n/3;
        //当绳子最后剩下的长度为4的时候，不能再去剪去长度为3的绳子段
        if(n-timesOf3*3==1)
            timesOf3-=1;
        int timesOf2 =(n-timesOf3*3)/2;
        return (int) (Math.pow(3,timesOf3)*Math.pow(2,timesOf2));
    }

    //递归法
    public static int greedy_cut_rope(int n)
    {
        if(n==2)
            return 2;
        if(n==3)
            return 3;
        if(n<2)
            return 1;
        //int timesOf3 = n/3;
        if(n==4)
            return 4;
        return 3*greedy_cut_rope(n-3);
    }

    public static void main(String[] args) {
        System.out.println(greedy_cut_rope(12));
        System.out.println(greedy_cut_rope_1(12));
    }

}
