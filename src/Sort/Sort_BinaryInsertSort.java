package Sort;

/**
 * ���ֲ�������
 *
 * ˼��
 * 1�����ֲ�������Ļ���˼��Ͳ�������һ�£����ǽ�ĳ��Ԫ�ز��뵽�Ѿ���������е���ȷ��λ�ã�
 * 2����ֱ�Ӳ����������������ǣ�Ԫ��A[i]��λ�õķ�����һ����
 *    ֱ�Ӳ��������Ǵ�A[i-1]��ǰһ�����Ƚϣ��Ӷ��ҵ���ȷ��λ�ã�
 *    �����ֲ�����������ǰi-1��Ԫ���Ѿ���������ص��϶��ֲ��ҵ��ص㣬��
 *    �ҵ���ȷ��λ�ã��Ӷ���A[i]���룬�������µ�������������
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
