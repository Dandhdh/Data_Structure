package 剑指offer编程题;

/**
 * 求连续子数组（包含负数）的最大和
 *
 * 思路：若和小于0，则将最大和置为当前值，否则计算最大和。
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
