package Interview_Question;

/**
 * �ٶȱ����
 * �ü�����ֽ
 * �ȶ�����һ������ֽ������ֽ����һЩ����ĵ㣬ÿ���㶼��������ϣ�
 * �������񿴳�һ��������ƽ���������ߵ�����ϵ�Ļ���ÿ���������һ������x��y����ʾ��
 * �ȶ��ܱ������������߻�һ�������Σ�ʹ���е��������ε��ڲ����߽߱硣
 * Ȼ�����������μ��������ʼ��������ε���С����Ƕ���
 */

import java.util.Scanner;

/**
 * ��������:
 * ��һ��һ����n(2��n��1000)��ʾ������������ÿ��һ������xi,yi(��1e9<=xi,yi<=1e9)��ʾ�����ϵĵ�
 *
 * �������:
 * һ�������С���
 *
 * ��������
 * 2
 * 0 0
 * 0 3
 *
 * �������
 * 9
 */
public class GridPaper {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int left = Integer.MAX_VALUE ;
        int right = Integer.MIN_VALUE;
        int top = Integer.MIN_VALUE;
        int bottom = Integer.MAX_VALUE;

        for (int i=0; i<n; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            if(x<left){
                left = x;
            }else if(x>right){
                right = x;
            }

            if (y<bottom){
                bottom = y;
            }else if(y>top){
                top = y;
            }
        }

        int length = right-left;
        int high = top-bottom;

        int area = length>high?length*length:high*high;
        System.out.println(area);

    }
}
