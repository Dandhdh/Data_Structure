package Algorithm.GreedyAlgorithm;

/**
 * 问题描述：
 *
 *假设有一个需要使某一资源的n个活动组成的集合S={a1，a2，a3...an}。
 * 该资源一次只能被一个活动占用，每个活动ai有一个开始时间Si和结束时间Fi，且0<=Si<Fi<∞。
 * 一旦被选择后，活动ai就占据半开时间区间[Si,Fi)。
 * 如果区间[Si，Fi)与 [Sj，Fj)互不重叠，称活动ai与aj是兼容的。
 *
 * 活动选择问题就是要选择出一个由互相兼容的问题组成的最大集合。
 */

/**
 * 解题思路：
 *
 * 对于任意非空子问题Sij，设am是Sij中具有最早结束时间的活动：
 * 　　　　fm=min{fk:ak∈Sij}
 *
 * 那么：
 * 1.活动am在Sij的某最大兼容活动子集中被使用。
 * 2.子问题Sim为空，所以选择am使子问题Smj为唯一可能非空的子问题
 * 　　
 * 在解决子问题时，选择am是一个可被合法调度、具有最早结束时间的活动。
 * 从直觉上来看，这种活动选择方法是一种贪婪技术，他给后面剩下的待调度任务留下了尽可能多的机会。
 * 也就是说，此处的贪心选择使得剩下的、未调度的时间最大化。
 */
public class greedy_activity_selector {

    public static void greedy_activity_selector(int[] s,int[] f,boolean[] b)
    {
        int n = s.length-1;
        b[1]=true;
        int j=1;
        for(int i =2;i<=n;i++)
        {
            if(s[i]>f[j])
            {
                b[i]=true;
                j=i;
            }else
                b[i]=false;
        }
        for(int i=1;i<b.length;i++)
            System.out.println(b[i]);
    }

}
