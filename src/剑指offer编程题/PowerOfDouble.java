package ��ָoffer�����;

/**
 * ����һ��double���͵ĸ�����base��int���͵�����exponent��
 * ��base��exponent�η�������ʹ�ÿ⺯��������Ҫ���Ǵ�������
 *
 */
public class PowerOfDouble {

    public static double Power(double base, int exponent) {
        double res = 0;

        //������==�Ƚ������������Ƿ���ȣ���Ϊ������������ֵ�Ķ��������
        if (equal(base,0)) {
            return 0;
        }
        if (exponent == 0) {
            return 1.0;
        }
        if (exponent > 0) {
            res = mutiply(base,exponent);
        }else {
            res = mutiply(1/base,-exponent);
        }
        return res;
    }

    public static double mutiply(double base, int e) {
        double sum = 1;
        for (int i = 0; i < e; i++) {
            sum = sum * base;
        }
        return sum;
    }

    //������==�Ƚ������������Ƿ���ȣ���Ϊ������������ֵ�Ķ��������
    public static boolean equal(double a, double b) {
        if (a - b < 0.000001 && a - b > -0.000001) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Power(0.0000001,8));
    }
}
