package ��ָoffer�����;

/**
 * ����һ��������������������Ʊ�ʾ��1�ĸ��������и����ò����ʾ��
 *
 * ˼·��a&(a-1)�Ľ���Ὣa���ұߵ�1��Ϊ0��ֱ��a = 0���������Ƚ�a&1 != 0��Ȼ������1λ�������ܼ��㸺����ֵ��
 */
public class NumberOfOne {

    public static int NumberOfOne(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = (n-1) & n;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(NumberOfOne(3));
    }
}
