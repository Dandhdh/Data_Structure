package ��ָoffer�����;

/**
 * ��1������n��1���ֵĴ���
 *
 * ˼·��
 * ����λ������Ϊ0����λ�Ͽ��ܳ���1�Ĵ����ɸ���λ������
 * ����λ������Ϊ1����λ�Ͽ��ܳ���1�Ĵ��������ܸ���λӰ�컹�ܵ�λӰ�죻
 * ����λ�����ִ���1�����λ�ϳ���1��������ɸ���λ����
 */
public class CountOneWithNumbers {

    public static long CountOne(long n){

        // ��¼1�ĸ���
        long count = 0;
        // ��ǰλ
        long i = 1;
        long current = 0,after = 0,before = 0;

        while((n / i) != 0) {
            before = n / (i * 10); // ��λ
            current = (n / i) % 10; // ��ǰλ
            after = n - (n / i) * i;  // ��λ

            if (current == 0)
                //���Ϊ0,����1�Ĵ����ɸ�λ����,���ڸ�λ���� * ��ǰλ��
                count = count + before * i;
            else if(current == 1)
                //���Ϊ1,����1�Ĵ����ɸ�λ�͵�λ����,��λ*��ǰλ+��λ+1
                count = count + before * i + after + 1;
            else if (current > 1)
                // �������1,����1�Ĵ����ɸ�λ����,����λ����+1��* ��ǰλ��
                count = count + (before + 1) * i;
            //ǰ��һλ
            i = i * 10;
        }
        return count;

    }

    public static void main(String[] args) {
        System.out.println(CountOne(321));
    }

}
