package ��ָoffer�����;

/**
 * һ�����������������������֮�⣬���������ֶ����������Ρ���д�����ҳ�������ֻ����һ�ε����֡�
 * (�������Ϊ��Ѱ�ҳ��ִ���������������)
 *
 * ˼·��
 * ������ͬ��������Ϊ0��������������õ�һ������
 * Ȼ�����1�ڸ������ұ߳��ֵ�index��Ȼ���ж�ÿ��������index���ǲ���1��
 */
public class FindNumsAppearOnce {

    public static int FindNumsAppearOnce(int[] array){
        if(array==null){
            return Integer.MAX_VALUE;
        }

        int number = array[0];
        for(int i=1; i<array.length; i++){
            number^=array[i];
        }
        return number;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1,4,4,3};
        System.out.println(FindNumsAppearOnce(nums));
    }

}
