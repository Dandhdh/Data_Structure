package ��ָoffer�����;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ����n���������ҳ�������С��K����
 *
 * ˼·���Ƚ�ǰK�����������飬���ж�������֮�����������С������е���
 */
public class GetLeastNumbers {

    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || k <= 0 || k > input.length) {
            return list;
        }
        int[] kArray = Arrays.copyOfRange(input,0,k);

        // ���������
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
            // iΪ�±�Ϊi��ֵ��������Ҷ�ӽڵ��ֵ�Ƚ�
            maxHeap(input,i);
        }
    }

    public static void maxHeap(int[] array,int i) {
        // �ڵ�i����Ҷ��
        int left=2*i+1;
        // ��Ҷ��
        int right=left+1;
        int largest=0;

        // �����ڵ����Ҷ�ӽڵ�Ƚϣ�largest�洢ֵ�ϴ�Ľڵ��index
        if(left < array.length && array[left] > array[i])
            largest=left;
        else
            largest=i;

        // ͨ��һ����õĽϴ�ֵ����Ҷ�ӵ�ֵ�Ƚ�
        if(right < array.length && array[right] > array[largest])
            largest = right;

        // ���largest����ԭ���ĸ��ڵ��index���ͽ���
        if(largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            // ���largest�����ѱ��滻�ɽ�Сֵ������һ��Ҷ�ӽڵ㣬��Ҫ���¼����Ƚ�
            maxHeap(array, largest);
        }
    }

    public static void main(String[] args) {
        int[] array = {2,3,1,7,6,5,4,10,0};
        ArrayList<Integer> list = GetLeastNumbers_Solution(array,5);
        System.out.println(list.toString());
    }

}