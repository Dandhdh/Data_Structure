package Algorithm.GreedyAlgorithm;

/**
 * 问题描述：
 *
 * 背包问题
 * 给定N个物品和一个容量为C的背包,物品i的重量为Wi，其价值为Vi，背包问题是如何选择装入背包的物品，
 * 使得装入背包中物品的总价值最大。注意在背包问题中，可以将某种物品的一部分装入背包中，但是不可以重复装入。
 */

/**
 * 解题思路：
 * 三种贪心思想：
 *
 * 　　选择价值最大的物品
 * 　　选择重量最轻的物品
 * 　　选择单位重量价值最大的物品
 *
 * 毫无疑问，我们当然选择第三种咯。先把性价比最高的全部装入，最后不足全部装入的部分装入。
 */
public class greedy_knapSack {

    public static int greedy_knapSack(int[] w,int[] v,int n,int c)
    {
        //  假设物品已按单位重量降序排列
        double[] x = new double[10];
        int maxValue =0;
        int i;
        for(i=0;w[i]<c;i++)
        {
            x[i]=1; //将物品 i 装入背包
            maxValue+=v[i];
            c=c-w[i]; // 背包剩余数量
        }
        x[i]=(double)c/w[i];    //物品i装入一部分
        maxValue+=x[i]*v[i];
        return maxValue;    //返回背包获得的价值
    }

}
