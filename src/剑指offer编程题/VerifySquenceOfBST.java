package ��ָoffer�����;

import java.util.Arrays;

/**
 * �ж��ǲ��Ǻ������
 *
 * ����һ���������飬�жϸ������ǲ���    ĳ����������  �ĺ�������Ľ����
 * ����������Yes,�������No���������������������������ֶ�������ͬ��
 *
 * ˼·�����ҵ��������Ŀ�ʼλ�ã�Ȼ��ֱ�������������ݹ鴦��
 */
public class VerifySquenceOfBST {

    public static boolean isPostOrder(int[] sequence){
        if( sequence==null || sequence.length==0){
            return false;
        }

        int rstart = 0;
        int length = sequence.length;

        // �����sequence[length-1]С��Ԫ�ظ������������������Ľڵ������
        for (int i = 0; i < length - 1; i++) {
            if (sequence[i] < sequence[length - 1])
                rstart++;
        }

        if (rstart == 0) {
            //������Ϊ��
            isPostOrder(Arrays.copyOfRange(sequence,0,length-1));
        }else {
            //ʣ�µ�����[rstart,length-2]Ϊ��������Ԫ�ظ���
            for (int i = rstart; i < length - 1; i++) {
                // ��������������Ԫ�رȸ��ڵ��ֵС����ڣ��򷵻�false
                if (sequence[i] <= sequence[length - 1]) {
                    return false;
                }
            }
            // �ݹ��жϸ��ڵ����������
            isPostOrder(Arrays.copyOfRange(sequence,0,rstart));
            isPostOrder(Arrays.copyOfRange(sequence,rstart,length - 1));
        }
        return true;
    }

    public static void main(String[] args) {
        int[] srr = {3,5,4,7,9,8,6};
        System.out.println(isPostOrder(srr));
    }

}
