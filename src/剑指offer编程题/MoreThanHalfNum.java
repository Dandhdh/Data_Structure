package ��ָoffer�����;

/**
 * ��������һ�����ֳ��ֵĴ����������鳤�ȵ�һ�룬���ҳ��������
 *
 * ˼·�����״γ��ֵ���count+1����֮��������бȽϣ������+1������1��������У���Ƿ񳬹����ȵ�һ��
 */
public class MoreThanHalfNum {

    public static int MoreThanHalfNum_Solution(int [] array) {

        int maxCount = array[0];
        int number = array[0];
        int count = 1;

        for (int i = 1; i < array.length; i++) {
            if (number != array[i]) {
                if (count == 0) {
                    number = array[i];
                    count = 1;
                }else {
                    count--;
                }
            }else {
                count++;
            }

            if (count == 1) {
                maxCount = number;
            }
        }

        // ��֤
        int num = 0;
        for (int j = 0; j < array.length; j++) {
            if (array[j] == maxCount) {
                num++;
            }
        }

        if (num * 2 > array.length) {
            return maxCount;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] arr = {4,4,4,4,4,1,4,3,2,4,3,4,3,3,3};
        System.out.println(MoreThanHalfNum_Solution(arr));
    }
}
