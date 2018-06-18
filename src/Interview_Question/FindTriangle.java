package Interview_Question;

/**
 * �ٶȱ����
 * Ѱ��������
 * ��ά�ռ�����N���㣬ÿ���������������ɫ������֮һ��������ɫ�ֱ��Ǻ��������ֱ���'R', 'G', 'B'��ʾ��
 * ����Ҫ�ҳ������㣬�����һ�������Σ�ʹ����������ε�������
 * ���������α������㣺���������ɫҪôȫ����ͬ��Ҫôȫ����ͬ��
 */

import java.util.Scanner;

/**
 * ��������
 * ��������һ��������N��ά����ϵ�ڵĵ�ĸ���.(N <= 50)
 * ������N�У�ÿһ������ c x y z��cΪ'R', 'G', 'B' ������һ����x��y��z�Ǹõ�����ꡣ(�������0��999֮�������)
 *
 * �������
 * ���һ������ʾ�������������������5λС����
 *
 * ��������
 * 5
 * R 0 0 0
 * R 0 4 0
 * R 0 0 3
 * G 92 14 7
 * G 12 16 8
 *
 * �������
 * 6.00000
 *
 */

public class FindTriangle {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //��ĸ���
        int n = sc.nextInt();
        sc.nextLine();

        double temp = 0.0;
        double area = Double.MIN_VALUE;

        // ������Ӧ�����ɫ���
        char[] colors = new char[n];
        // x��
        int[] x = new int[n];
        // y��
        int[] y = new int[n];
        // z��
        int[] z = new int[n];

        for (int i = 0; i < n; i++) {
            String[] nums = sc.nextLine().split(" ");
            colors[i] = nums[0].charAt(0);
            x[i] = Integer.parseInt(nums[1]);
            y[i] = Integer.parseInt(nums[2]);
            z[i] = Integer.parseInt(nums[3]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    //�ж���ɫ�Ƿ�ȫ����Ȼ�ȫ������
                    if (colors[i] == colors[j] && colors[j] == colors[k] ||
                            colors[i] != colors[j] && colors[j] != colors[k] && colors[i] != colors[k]) {
                        // �������ߵĳ���
                        double a = Math.pow(Math.pow(Math.abs(x[i] - x[j]), 2) +
                                Math.pow(Math.abs(y[i] - y[j]), 2) +
                                Math.pow(Math.abs(z[i] - z[j]), 2), 0.5);
                        double b = Math.pow(Math.pow(Math.abs(x[i] - x[k]), 2) +
                                Math.pow(Math.abs(y[i] - y[k]), 2) +
                                Math.pow(Math.abs(z[i] - z[k]), 2), 0.5);
                        double c = Math.pow(Math.pow(Math.abs(x[k] - x[j]), 2) +
                                Math.pow(Math.abs(y[k] - y[j]), 2) +
                                Math.pow(Math.abs(z[k] - z[j]), 2), 0.5);

                        double p = (a + b + c) / 2;
                        //���׹�ʽ��ռ����������
                        temp = Math.pow(p * (p - a) * (p - b) * (p - c), 0.5);
                        if (area < temp) {
                            area = temp;
                        }
                    }
                }
            }
        }
        System.out.printf("%.5f", area);
    }
}