package 剑指offer编程题;

/**
 * 求1+2+3+…+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 * 思路：巧用递归（返回值类型为Boolean）
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
