package ��ָoffer�����;

public class Add {

    /**
     * ��1+2+3+��+n��Ҫ����ʹ�ó˳�����for��while��if��else��switch��case�ȹؼ��ּ������ж���䣨A?B:C����
     *
     * ˼·�����õݹ飨����ֵ����ΪBoolean��
     */
    public static int Sum_Solution(int n) {
        int sum = n;
        boolean result = (n > 0) && ((sum += Sum_Solution(n-1)) > 0);
        return sum;
    }

    /**
     * дһ������������������֮�ͣ�
     * Ҫ���ں������ڲ���ʹ��+��-��*��/����������š�
     *
     * ˼·������λ����
     */
    public static int add(int num1,int num2){
        while (num2!=0){
            System.out.println("------");
            //�������λ(�������Ƶĸ���λ��ֵ��ӣ����ƽ�λ��)
            int temp = num1 ^ num2;
            // ����������λ���ϵĽ�λֵ
            num2 = (num1 & num2) << 1;
            num1 = temp;

            // ��num2==0�������н�λֵ��Ϊ0�ǣ�����num1��Ϊ���
        }
        return num1;
    }

    public static void main(String[] args) {
//        System.out.println(Sum_Solution(10));
        System.out.println(add(224,102));
        System.out.println(224^102);
    }



}
