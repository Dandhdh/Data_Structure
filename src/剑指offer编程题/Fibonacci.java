package ��ָoffer�����;

/**
 * ����Ҫ������һ������n���������쳲��������еĵ�n�n<=39
 *
 * ˼·��
 * �ݹ��Ч�ʵͣ�ʹ��ѭ����ʽ��
 */

/**
 * 쳲���������ָ��������һ������ 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144��������
 * ������дӵ�3�ʼ��ÿһ�����ǰ����֮�͡�
 * �� f(n)=f(n-1)+f(n-2)
 */
public class Fibonacci {

    //�ݹ�ʵ��
    //Ч�ʼ��ͣ��ռ俪����
    public static long fibonacci_ByRecursion(int n){
        if(n==2 || n==1){
            return 1;
        }
        return fibonacci_ByRecursion(n-1)+fibonacci_ByRecursion(n-2);

    }

    //ѭ��ʵ��
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
        System.out.println("�ݹ鷽��--��ʱ��"+(methodOneEnd-methodOneBign));
        long methodTwoBign = System.currentTimeMillis();
        System.out.println(fibonacci(100));
        long methodTwoEnd = System.currentTimeMillis();
        System.out.println("ѭ������--��ʱ��"+(methodTwoEnd-methodTwoBign));


    }

}
