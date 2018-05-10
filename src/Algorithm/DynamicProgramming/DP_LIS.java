package Algorithm.DynamicProgramming;

/**
 * ����������
 * ����������У�Longest Increasing Subsequence��
 *
 * ��������ΪN������A������A������������������У���һ����������
 * ���������A{5��6��7��1��2��8}����A��LISΪ{5��6��7��8}������Ϊ4.
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * ����˼·��
 * ��Ϊ������Ҫ���ǵ����ģ������ص��������е���ʼ�ַ��ͽ�β�ַ���������ǿ������ý�β�ַ���
 * �뵽����A[0]��β��������������ж೤����A[1]��β��������������ж೤��������A[n-1]��β��������������ж೤��
 *
 * ����̬�滮solution��
 * �������ǿ���ʹ��һ������Ŀռ�������ǰ���Ѿ���õ�����������У�Ȼ��ÿ�θ��µ�ǰ�ļ��ɡ�
 * Ҳ���������ݻ��ɣ��Ѿ�����õ���b[0,1,2,����,i-1]����μ���õ�b[i]�أ�
 * ��Ȼ�����ai>=aj������Խ�ai�ŵ�b[j]�ĺ��棬�õ���b[j]�����������С�
 * �Ӷ���b[i] = max{b[j]}+1    s.t. A[i] > A[j] && 0 <= j < i.
 *
 * ���Լ���b[i]�Ĺ����ǣ�����b[i]֮ǰ������λ��j���ҳ������ϵʽ������b[j].
 * �õ�b[0...n-1]֮�󣬱������е�b[i]�ҵ����ֵ����Ϊ�����������С� �ܵ�ʱ�临�Ӷ�ΪO(N2).
 */
public class DP_LIS {

    //�������������еĳ���
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
     * �������������е���������
     * ����ͨ����¼ǰ���ķ�ʽ���Ӹ�λ���ҵ���ǰ���������ҵ�ǰ����ǰ��
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
