package 剑指offer编程题;

/**
 * 现在要求输入一个整数n，请你输出斐波那契数列的第n项。n<=39
 *
 * 思路：
 * 递归的效率低，使用循环方式。
 */

/**
 * 斐波那契数列指的是这样一个数列 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144。。。。
 * 这个数列从第3项开始，每一项都等于前两项之和。
 * 即 f(n)=f(n-1)+f(n-2)
 */
public class Fibonacci {

    //递归实现
    //效率极低，空间开销大
    public static long fibonacci_ByRecursion(int n){
        if(n==2 || n==1){
            return 1;
        }
        return fibonacci_ByRecursion(n-1)+fibonacci_ByRecursion(n-2);

    }

    //循环实现
    public static long fibonacci(int n) {
        long result=0;
        long preOne=1;
        long preTwo=0;
        if(n==0) {
            return preTwo;
        }
        if(n==1) {
            return preOne;
        }
        for (int i = 2; i <= n; i++) {
            result = preOne+preTwo;
            preTwo = preOne;
            preOne = result;
        }
        return result;
    }

    public static void main(String[] args) {
        long methodOneBign = System.currentTimeMillis();
        System.out.println(fibonacci_ByRecursion(10));
        long methodOneEnd = System.currentTimeMillis();
        System.out.println("递归方法--耗时："+(methodOneEnd-methodOneBign));
        long methodTwoBign = System.currentTimeMillis();
        System.out.println(fibonacci(100));
        long methodTwoEnd = System.currentTimeMillis();
        System.out.println("循环方法--耗时："+(methodTwoEnd-methodTwoBign));


    }

}
