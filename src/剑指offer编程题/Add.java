package 剑指offer编程题;

public class Add {

    /**
     * 求1+2+3+…+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     *
     * 思路：巧用递归（返回值类型为Boolean）
     */
    public static int Sum_Solution(int n) {
        int sum = n;
        boolean result = (n > 0) && ((sum += Sum_Solution(n-1)) > 0);
        return sum;
    }

    /**
     * 写一个函数，求两个整数之和，
     * 要求在函数体内不得使用+、-、*、/四则运算符号。
     *
     * 思路：利用位运算
     */
    public static int add(int num1,int num2){
        while (num2!=0){
            System.out.println("------");
            //计算出个位(即二进制的各个位置值相加（不计进位）)
            int temp = num1 ^ num2;
            // 计算计算各个位置上的进位值
            num2 = (num1 & num2) << 1;
            num1 = temp;

            // 当num2==0，即所有进位值均为0是，这是num1即为结果
        }
        return num1;
    }

    public static void main(String[] args) {
//        System.out.println(Sum_Solution(10));
        System.out.println(add(224,102));
        System.out.println(224^102);
    }



}
