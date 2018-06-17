package Interview_Question;

/**
 * �ٶȱ����
 * �������
 * ss��cc��������㣬�����ɻ���Ϊn*m�ĸ��ӣ�ÿ�������в�ͬ�ĸ��ʵ����㣬
 * ccһֱ������(x,y)�ĸ��ӵ��㣬��ssÿ���������һ�����ӡ�
 * ��t���Ӻ�����˭���ٵ���һ����ĸ��ʴ�Ϊ���٣�
 */

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * ��������:
 * ��һ���������n,m,x,y,t(1��n,m,t��1000,1��x��n,1��y��m);
 * ������Ϊһ��n��m�ľ���ÿ��m��һλС������n�У���i�е�j������������Ϊ(i,j)�ĸ��ӵ�����ĸ���Ϊp(0��p��1)
 * <p>
 * �������:
 * ������С���һ��Ϊ���ʴ���˵�����(cc/ss/equal),
 * �ڶ���Ϊ�������(����2λС��)
 * <p>
 * ��������
 * 2 2 1 1 1
 * 0.2 0.1
 * 0.1 0.4
 * <p>
 * �������
 * equal
 * 0.20
 */
public class FishingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int x = sc.nextInt();
        int y = sc.nextInt();

        int time = sc.nextInt();

        double[][] pond = new double[n][m];
        double proSum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                double pro = sc.nextDouble();
                pond[i][j] = pro;
                proSum += pro;
            }
        }
        //������λС��
        DecimalFormat df = new DecimalFormat("0.00");

        double pro1 = 1 - pond[x - 1][y - 1];
        pro1 = 1 - Math.pow(pro1,time);
        double pro2 = 1 - proSum / (n * m);
        pro2 = 1 - Math.pow(pro2,time);

        if (pro1-pro2>=0.01) {
            System.out.println("cc");
            System.out.println(df.format(pro2));
        } else if (pro2-pro1>=0.01) {
            System.out.println("ss");
            System.out.println(df.format(pro1));
        }else {
            System.out.println("equal");
            System.out.println(df.format(pro1));
        }
    }
}
