package ��ָoffer�����;

/**
 * ����һ���������飬ʵ��һ�����������������������ֵ�˳��ʹ�����е�����λ�������ǰ�벿�֣�
 * ���е�ż��λ��λ������ĺ�벿�֣�����֤������������ż����ż��֮������λ�ò���
 *
 * ˼·��ÿ��ֻ��ǰ��һ��������λ�á��������ø�������
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
