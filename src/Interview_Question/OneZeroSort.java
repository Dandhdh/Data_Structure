package Interview_Question;

/**
 * �ٶȱ��Ա���⣺
 * 0-1 ����
 * ����һ��01�������ɡ� 0����1����ɵ��ַ��������������������ִ�����ɡ��ǵݼ����������У�
 * ����������Ҫ���ٴν�������������λ�ý�������
 */

import java.util.Scanner;

/**
 * ��������:
 * �������ݵ�һ����һ��������T(T<=100),��ʾ��T��������ݣ�
 * ��������T�У�ÿ�и���01����
 * ���ݱ�֤����
 * 50%���ַ���������[1,100 ]
 * 95%���ַ���������[1,10000]
 * 100%���ַ���������[1,1000000]
 *
 *
 * �������:
 * ����ÿ��������ݣ�������ųɡ��ǵݼ��������С�����С����������
 * ÿ�����ռһ�С�
 *
 * ��������1:
 * 3
 * 01
 * 10
 * 110
 *
 * �������1:
 * 0
 * 1
 * 1
 */

/**
 * ����˼·��
 * ��Ҫͳ����������
 * ��1��ÿ��01�ַ����У�0�ĸ�������Ϊn
 * ��2����nλ����֮ǰ���ж��ٸ�0
 */
public class OneZeroSort {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for (int i=0; i<n; i++){
            String str = sc.nextLine();
            char[] chars = str.toCharArray();

            // ��ĸ���
            int zeroNum = 0;
            // ÿһλǰ��0�ĸ���
            int[] zero = new int[chars.length+1];
            zero[0] = 0;
            for (int j=0; j<chars.length; j++){
                if (chars[j]=='0'){
                    zeroNum++;
                    zero[j+1] = zero[j]+1;
                }else {
                    zero[j+1] = zero[j];
                }
            }
            System.out.println(zeroNum-zero[zeroNum]);
        }
    }
}
