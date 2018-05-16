package 剑指offer编程题;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 输入n个整数，找出其中最小的K个数
 *
 * 思路：先将前K个数放入数组，进行堆排序，若之后的数比它还小，则进行调整
 */
public class GetLeastNumbers {

    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || k <= 0 || k > input.length) {
            return list;
        }
        int[] kArray = Arrays.copyOfRange(input,0,k);

        // 创建大根堆
        buildHeap(kArray);

        for(int i = k; i < input.length; i++) {
            if(input[i] < kArray[0]) {
                kArray[0] = input[i];
                maxHeap(kArray, 0);
            }
        }

        for (int i = kArray.length - 1; i >= 0; i--) {
            list.add(kArray[i]);
        }

        return list;
    }

    public static void buildHeap(int[] input) {
        for (int i = input.length/2 - 1; i >= 0; i--) {
            // i为下标为i的值跟其左右叶子节点的值比较
            maxHeap(input,i);
        }
    }

    public static void maxHeap(int[] array,int i) {
        // 节点i的左叶子
        int left=2*i+1;
        // 右叶子
        int right=left+1;
        int largest=0;

        // 即父节点跟左叶子节点比较，largest存储值较大的节点的index
        if(left < array.length && array[left] > array[i])
            largest=left;
        else
            largest=i;

        // 通上一部获得的较大值跟右叶子的值比较
        if(right < array.length && array[right] > array[largest])
            largest = right;

        // 如果largest不是原来的父节点的index，就交换
        if(largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            // 这个largest代表已被替换成较小值的其中一个叶子节点，需要往下继续比较
            maxHeap(array, largest);
        }
    }

    public static void main(String[] args) {
        int[] array = {2,3,1,7,6,5,4,10,0};
        ArrayList<Integer> list = GetLeastNumbers_Solution(array,5);
        System.out.println(list.toString());
    }

}