package Sort;

/**
 * 二分插入排序
 *
 * 思想
 * 1、二分插入排序的基本思想和插入排序一致；都是将某个元素插入到已经有序的序列的正确的位置；
 * 2、和直接插入排序的最大区别是，元素A[i]的位置的方法不一样；
 *    直接插入排序是从A[i-1]往前一个个比较，从而找到正确的位置；
 *    而二分插入排序，利用前i-1个元素已经是有序的特点结合二分查找的特点，、
 *    找到正确的位置，从而将A[i]插入，并保持新的序列依旧有序；
 */
public class Sort_BinaryInsertSort {

    private static void binaryInsertSort(int[] array) {
        int low, high, mid;
        int tmp, j;
        for (int i = 1; i < array.length; i++) {
            tmp = array[i];
            low = 0;
            high = i - 1;
            while (low <= high) {
                mid = low + (high - low) / 2;
                if (array[mid] > tmp)
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            for (j = i - 1; j > high; j--) {
                array[j + 1] = array[j];
            }
            array[high + 1] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,56,12,34,5,7};
        for (int a : arr){
            System.out.print(a +" ");
        }
        System.out.println();
        binaryInsertSort(arr);
        for (int a : arr){
            System.out.print(a +" ");
        }
    }

}
