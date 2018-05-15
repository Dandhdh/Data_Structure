package 剑指offer编程题;

/**
 * 斐波那契数列
 * 在各个类型编程题的运用
 */
public class FibonacciDemo {

    /**
     * 情景：
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级
     */

    /**
     * 该问题实质是斐波那契数列求和，递推公式为 f(n)=f(n-1)+f(n-2);
     *
     *  可以考虑，小青蛙每一步跳跃只有两种选择：
     *  一是再跳一级阶梯到达第 i 级阶梯，此时小青蛙处于第 i-1 级阶梯；
     *  或者再跳两级阶梯到达第 i 级阶梯，此时小青蛙处于第 i-2 级阶梯。
     *
     * 于是，i 级阶梯的跳法总和依赖于前 i-1 级阶梯的跳法总数f(i-1)和前 i-2 级阶梯的跳法总数f(i-2)。
     * 因为只有两种可能性，所以，f(i)=f(i-1)+f(i-2);
     *
     * 依次类推，可以递归求出n级阶梯跳法之和。
     */
    public int JumpFloor(int n){
        if(n<=0){
            return -1;
        }
        if(n==1||n==2){
            return n;
        }
        else{
            return JumpFloor(n-1)+JumpFloor(n-2);
        }
    }

    /**
     * 情景：
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     *
     * 思路：
     * 2^(n-1)
     */
    public int JumpFloor2(int target) {
        return (int) Math.pow(2,target-1);
    }

    /**
     * 情景：
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     *
     * 思路：斐波那契数列思想
     */
    public static int CoverRect(int n)
    {
        int num1 = 1, num2 = 1, num3 = 0, i = 0;
        if (n <= 1)
        {
            return num1;
        }
        for (i = 1; i < n; i++)
        {
            num3 = num1 + num2;
            num1 = num2;
            num2 = num3;
        }
        return num3;
    }

    public static void main(String[] args) {
        System.out.println(CoverRect(3));
    }
}
