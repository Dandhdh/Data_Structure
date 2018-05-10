package Algorithm.DynamicProgramming;

/**
 * 问题描述：
 * 最长递增子序列（Longest Increasing Subsequence）
 *
 * 给定长度为N的数组A，计算A的最长单调递增的子序列（不一定连续）。
 * 如给定数组A{5，6，7，1，2，8}，则A的LIS为{5，6，7，8}，长度为4.
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * 解题思路：
 * 因为子序列要求是递增的，所以重点是子序列的起始字符和结尾字符，因此我们可以利用结尾字符。
 * 想到：以A[0]结尾的最长递增子序列有多长？以A[1]结尾的最长递增子序列有多长？……以A[n-1]结尾的最长递增子序列有多长？
 *
 * （动态规划solution）
 * 所以我们可以使用一个额外的空间来保存前面已经算得的最长递增子序列，然后每次更新当前的即可。
 * 也就是问题演化成：已经计算得到了b[0,1,2,……,i-1]，如何计算得到b[i]呢？
 * 显然，如果ai>=aj，则可以将ai放到b[j]的后面，得到比b[j]更长的子序列。
 * 从而：b[i] = max{b[j]}+1    s.t. A[i] > A[j] && 0 <= j < i.
 *
 * 所以计算b[i]的过程是，遍历b[i]之前的所有位置j，找出满足关系式的最大的b[j].
 * 得到b[0...n-1]之后，遍历所有的b[i]找到最大值，即为最大递增子序列。 总的时间复杂度为O(N2).
 */
public class DP_LIS {

    //求出最长递增子序列的长度
    public static int LIS(int[] A) {
        if(A == null || A.length == 0)
            return 0;
        int[] b = new int[A.length];
        b[0] = 1;
        int result = 1;
        for(int i=1; i<A.length; i++) {
            int max = 0;
            for(int j=0; j<i; j++) {
                if(A[j] < A[i] && b[j] > max)
                    max = b[j];
            }
            b[i] = max + 1;
            result = Math.max(result, b[i]);
        }
//        for(int i=0; i<b.length; i++){
//            System.out.print(b[i]+" ");
//        }
        return result;
    }

    /**
     * 求出最长递增子序列的所有序列
     * 可以通过记录前驱的方式，从该位置找到其前驱，进而找到前驱的前驱
     */
    public static ArrayList<Integer> LISDetail(int[] A) {
        if(A == null || A.length == 0)
            return null;
        int[] b = new int[A.length];
        int[] b1 = new int[A.length];
        b[0] = 1;
        b1[0] = -1;
        int result = 1;
        int index = 0;
        for(int i=1; i<A.length; i++) {
            int max = 0;
            boolean flag = false;
            for(int j=0; j<i; j++) {
                if(A[j] < A[i] && b[j] > max) {
                    flag = true;
                    max = b[j];
                    b1[i] = j;
                }
            }
            if(flag == false) b1[i] = -1;
            b[i] = max + 1;
            if(result < b[i]) {
                result = b[i];
                index = i;
            }
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        //res.add(A[index]);
        for(;index >=0; ) {
            res.add(A[index]);
            index = b1[index];
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        int[] a = {1,10,2,4,67,115,117,18,23,33,1,36};
        System.out.println();
        System.out.println(LISDetail(a));
    }
}
