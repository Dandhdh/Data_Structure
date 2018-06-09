package Interview_Question;

/**
 * �����
 * ���� x, k �������� x + y = x | y �ĵ� k С�������� y �� | �Ƕ����ƵĻ�(or)���㣬
 * ���� 3 | 5 = 7��
 * ���統 x=5��k=1ʱ���� 2����Ϊ5+1=6 ������ 5|1=5���� 5+2=7 ���� 5 | 2 = 7��
 */

import java.util.Scanner;

/**
 * ����������
 * ÿ���������������һ�����ݣ�ÿ������Ϊ���������� x , k�� ���� 0 < x , k �� 2,000,000,000��
 *
 * ���������
 * ���һ����y
 *
 * �������ӣ�
 * 5 1
 *
 * ������ӣ�
 * 2
 */
public class Or_Add {

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int x = sc.nextInt();
//        int k = sc.nextInt();
//
//        int n = 0;
//        int result = 0;
//
//        for (int i = 1; i <= 2000000000; i++) {
//            if ((x + i) == (x | i)) {
//                n += 1;
//            }
//            if (n == k) {
//                result = i;
//                break;
//            }
//        }
//        System.out.println(result);
//    }
    //���������������ʾ��ʱ

    /**
     * ����˼·��
     * x+y �ǰ�x��yÿһ��������λ������ӣ�1+1�ͽ�λ
     * x|y ����ÿ��������λ�ö�Ӧ�Ľ��л����㣨��1��1��
     *
     * �����Ŀ�У�x��ֵ�ǹ̶��ģ�Ҳ����x�Ķ���������1��λ�ú���Ŀ�ǹ̶��ģ�
     * ���Խ��л������ʱ�򣬽���ж�Ӧ��λ�϶�Ӧ����1��
     *
     * �ܽ᣺
     * ����y�Ķ�������Ӧ������k���������x���������е�0,��x���������е�1��Ӧ�ø�Ϊ0��
     */
    public static void main(String[]args)
    {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNextInt())
        {
            int x=sc.nextInt();
            int k=sc.nextInt();

            StringBuilder sb=new StringBuilder();
            // xֵ�Ķ�����
            String xB = Integer.toString(x, 2);
            // kֵ�Ķ�����
            String kB = Integer.toString(k,2);

            int lengthK=kB.length();
            int index=lengthK-1;
            int lengthX= xB.length();

            for (int i = lengthX-1; i >=0 ; i--)
            {
                char c= xB.charAt(i);
                if (index>=0)
                {
                    if (c=='1'){
                        sb.append('0');
                    } else {
                        sb.append(kB.charAt(index--));
                    }
                }
                else break;
            }
            for (int i = index; i >=0 ; i--)
            {
                sb.append(kB.charAt(i));
            }
            sb.reverse();
            System.out.println(Long.parseLong(sb.toString(),2));
        }
        sc.close();
    }
}
