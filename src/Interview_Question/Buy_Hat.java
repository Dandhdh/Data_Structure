package Interview_Question;

/**
 * �ٶȱ����
 * ��ñ��
 * �ȶ�����ȥ�̳���һ��ñ�ӣ��̳�����N��ñ�ӣ���Щñ�ӵļ۸������ͬ��
 * �ȶ�������һ���۸�������˵�ñ�ӣ��ʵ������˵�ñ�Ӽ۸��Ƕ��٣�
 *
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * ����������
 * ��������һ��������N��N <= 50��������������N������ʾÿ��ñ�ӵļ۸񣨼۸��������������С�ڵ���1000��
 *
 * �������
 * ������ڵ������˵�ñ�ӣ����������۸��Ƕ��٣��������-1
 *
 * ����������
 * 10
 * 10 10 10 10 20 20 30 30 40 40
 *
 * ���������
 * 30
 */
public class Buy_Hat {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int price[] =new int[n];
        for (int i=0; i<n; i++){
            price[i] = sc.nextInt();
        }

        //��������
        Arrays.sort(price);

        //����ڼ����˵�ñ��
        int flag=1;

        //ѭ��
        for(int i=0;i<n-1;i++){
            if(price[i]<price[i+1]){
                flag++;
            }
            //�ҳ��������˵����
            if(flag==3){
                System.out.println(price[i+1]);
                break;
            }
        }
        if(flag!=3){
            System.out.println(-1);
        }
    }
}
