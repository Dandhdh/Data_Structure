package ��ָoffer�����;

/**
 * ��ӡ1������nλ��
 *
 * ˼·�����Ǵ������⣬ʹ���ַ����������ʾ��
 */
public class printToMaxOfNDigits {

    //��������
    public static void printToMaxOfNDigits(int n) {
        int[] array=new int[n];
        if(n <= 0)
            return;
        printArray(array, 0);
    }

    private static void printArray(int[] array,int n) {
        for(int i = 0; i < 10; i++) {
            if(n != array.length) {
                array[n] = i;
                printArray(array, n+1);
            } else {
                boolean isFirstNo0 = false;
                for(int j = 0; j < array.length; j++) {
                    if(array[j] != 0) {
                        System.out.print(array[j]);
                        if(!isFirstNo0)
                            isFirstNo0 = true;
                    } else {
                        if(isFirstNo0)
                            System.out.print(array[j]);
                    }
                }
                System.out.println();
                return ;
            }
        }
    }

    //Ч�ʵ�
    public static void printToMax(int n){
        double max = Math.pow(10,n)-1;
        for(int i=1; i<=max; i++){
            System.out.println(i);
        }
    }

    public static void main(String[] args) {

        long methodOneBign = System.currentTimeMillis();
        printToMaxOfNDigits(5);
        long methodOneEnd = System.currentTimeMillis();
        long methodTwoBign = System.currentTimeMillis();
        printToMax(5);
        long methodTwoEnd = System.currentTimeMillis();
        System.out.println("����һ��ʱ��"+(methodOneEnd-methodOneBign));
        System.out.println("��������ʱ��"+(methodTwoEnd-methodTwoBign));
    }

}
