package Interview_Question;

/**
 * 百度笔试编程题：
 * 有趣的排序
 * 度度熊有一个N个数的数组，他想将数组从小到大 排好序，但是萌萌的度度熊只会下面这个操作：
 * 任取数组中的一个数然后将它放置在数组的最后一个位置。
 * 问最少操作多少次可以使得数组从小到大有序？
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 输入描述:
 * 首先输入一个正整数N，接下来的一行输入N个整数。(N <= 50, 每个数的绝对值小于等于1000)
 *
 *
 * 输出描述:
 * 输出一个整数表示最少的操作次数。
 *
 * 输入例子1:
 * 4
 * 19 7 8 25
 *
 * 输出例子1:
 * 2
 */

/**
 * 解题思路
 * 将数列排序号，并用indexMap存储排序前的数字的index，
 * 再排序后数组相邻的数字两两比较排序前的index，
 * 如果，大数排序前index较小，则需要移动，并设置移动标记isMove
 * （即使不满足，上一个条件，但是比自身小的前一个数，如果标记移动过，则自身也需要移动）
 */
public class FunnySort {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        // 存储排序前的index
        Map<Integer,Integer> indexMap = new HashMap<>();
        for (int i=0; i<n; i++){
            int v = sc.nextInt();
            nums[i] = v;
            indexMap.put(v,i);
        }
        Arrays.sort(nums);

        // 需要移动的次数
        int count = 0;

        // 标记 已排序数组中的前一个数字是否需要移动
        boolean isMove = false;
        for (int i=0; i<n-1; i++){
            if (isMove){
                count++;
                continue;
            }
            if (indexMap.get(nums[i])>indexMap.get(nums[i+1])){
                count++;
                isMove = true;
            }
        }

        System.out.println(count);
    }
}
