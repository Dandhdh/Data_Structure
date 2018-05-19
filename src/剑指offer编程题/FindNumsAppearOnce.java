package 剑指offer编程题;

/**
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 * (可以理解为：寻找出现次数是奇数的数字)
 *
 * 思路：
 * 两个相同的数异或后为0，将所有数异或后得到一个数，
 * 然后求得1在该数最右边出现的index，然后判断每个数右移index后是不是1。
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
