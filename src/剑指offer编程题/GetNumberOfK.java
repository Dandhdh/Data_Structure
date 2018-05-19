package 剑指offer编程题;

import java.util.Arrays;

/**
 * 统计一个数字在排序数组中出现的次数。
 *
 * 思路：利用二分查找+递归思想，进行寻找。
 * 当目标值与中间值相等时进行判断
 */
public class GetNumberOfK {

    public static int GetNumberOfK(int[] array, int k){

        int result =0;
        int mid = array.length/2;

        if(array==null || array.length==0){
            return 0;
        }
        if(array.length==1){
            if(array[0]==k){
                return 1;
            }else {
                return 0;
            }
        }

        if(k<array[mid]){
            result += GetNumberOfK(Arrays.copyOfRange(array,0,mid),k);
        }else if(k>array[mid]){
            result += GetNumberOfK(Arrays.copyOfRange(array,mid+1,array.length),k);
        }else {
            // 当目标值与中间值相等时进行判断，分别向两边比较
            for(int i=mid; i<array.length; i++){
                if(array[i]==k){
                    result++;
                }else {
                    break;
                }
            }
            for (int i=mid-1; i>=0; i--){
                if (array[i]==k){
                    result++;
                }else {
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,2,3,3,3,4,4,4,5};
        System.out.println(GetNumberOfK(arr,3));
    }
}
