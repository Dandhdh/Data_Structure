package ��ָoffer�����;

/**
 * �����������飨����������������
 *
 * ˼·������С��0����������Ϊ��ǰֵ������������͡�
 */
public class FindGreatestSumOfSubArray {

    public static int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0)
            return 0;
        int cur = array[0];
        int greast = array[0];

        for (int i = 1; i < array.length; i++) {
            if (cur < 0) {
                cur = array[i];
            }else {
                cur += array[i];
            }

            if (cur > greast) {
                greast = cur;
            }
        }
        return greast;
    }

    public static void main(String[] args) {
        int[] arr = {3,-2,1,2,3,4,-1,-2,7,-4};
        System.out.println(FindGreatestSumOfSubArray(arr));
    }

}
