package ��ָoffer�����;

/**
 * ��1+2+3+��+n��Ҫ����ʹ�ó˳�����for��while��if��else��switch��case�ȹؼ��ּ������ж���䣨A?B:C����
 *
 * ˼·�����õݹ飨����ֵ����ΪBoolean��
 */
public class Add {

    public static int Sum_Solution(int n) {
        int sum = n;
        boolean result = (n > 0) && ((sum += Sum_Solution(n-1)) > 0);
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(Sum_Solution(10));
    }

}
