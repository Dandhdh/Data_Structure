package Interview_Question;

/**
 * 百度编程题
 * 寻找三角形
 * 三维空间中有N个点，每个点可能是三种颜色的其中之一，三种颜色分别是红绿蓝，分别用'R', 'G', 'B'表示。
 * 现在要找出三个点，并组成一个三角形，使得这个三角形的面积最大。
 * 但是三角形必须满足：三个点的颜色要么全部相同，要么全部不同。
 */

import java.util.Scanner;

/**
 * 输入描述
 * 首先输入一个正整数N三维坐标系内的点的个数.(N <= 50)
 * 接下来N行，每一行输入 c x y z，c为'R', 'G', 'B' 的其中一个。x，y，z是该点的坐标。(坐标均是0到999之间的整数)
 *
 * 输出描述
 * 输出一个数表示最大的三角形面积，保留5位小数。
 *
 * 输入例子
 * 5
 * R 0 0 0
 * R 0 4 0
 * R 0 0 3
 * G 92 14 7
 * G 12 16 8
 *
 * 输出例子
 * 6.00000
 *
 */

public class FindTriangle {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //点的个数
        int n = sc.nextInt();
        sc.nextLine();

        double temp = 0.0;
        double area = Double.MIN_VALUE;

        // 储存相应点的颜色标记
        char[] colors = new char[n];
        // x轴
        int[] x = new int[n];
        // y轴
        int[] y = new int[n];
        // z轴
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
                    //判断颜色是否全部相等或全部不等
                    if (colors[i] == colors[j] && colors[j] == colors[k] ||
                            colors[i] != colors[j] && colors[j] != colors[k] && colors[i] != colors[k]) {
                        // 求三条边的长度
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
                        //海伦公式求空间三角形面积
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