package 剑指offer编程题;

/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。
 * 求base的exponent次方。不得使用库函数，不需要考虑大数问题
 *
 */
public class PowerOfDouble {

    public static double Power(double base, int exponent) {
        double res = 0;

        //不能用==比较两个浮点数是否相等，因为有误差。考虑输入值的多种情况。
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

    //不能用==比较两个浮点数是否相等，因为有误差。考虑输入值的多种情况。
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
