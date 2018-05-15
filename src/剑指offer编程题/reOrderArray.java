package 剑指offer编程题;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变
 *
 * 思路：每次只和前面一个数交换位置。或者利用辅助数组
 */
public class reOrderArray {

    public static void reOrderArray(int [] array) {
        if(array == null)
            return ;

        for(int i = 1; i < array.length; i++){
            int temp = array[i];
            int j = i - 1;
            if(array[i] % 2 != 0){
                while(j >= 0){
                    if(array[j] % 2 != 0){
                        break;
                    }
                    if(array[j]%2 == 0){
                        int t = array[j+1];
                        array[j+1] = array[j];
                        array[j] = t;
                        j--;
                    }
                }
            }
            array[j+1] = temp;
        }
    }
}
