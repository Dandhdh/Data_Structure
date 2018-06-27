package Interview_Question;

/**
 * 牛客网模拟笔试题
 * 牛牛数星星
 * 一闪一闪亮晶晶，满天都是小星星，牛牛晚上闲来无聊，便躺在床上数星星。
 * 牛牛把星星图看成一个平面，左上角为原点(坐标为(1, 1))。
 * 现在有n颗星星，他给每颗星星都标上坐标(xi，yi)，表示这颗星星在第x行，第y列。
 * 现在，牛牛想问你m个问题，给你两个点的坐标(a1, b1)(a2，b2)，表示一个矩形的左上角的点坐标和右下角的点坐标，
 * 请问在这个矩形内有多少颗星星（边界上的点也算是矩形内）。
 */

import java.util.Scanner;

/**
 * 输入描述:
 * 第一行输入一个数字n(1≤n≤100000)，表示星星的颗数。
 * 接下来的n行，每行输入两个数xi和yi(1≤xi，yi≤1000），表示星星的位置。
 * 然后输入一个数字m(1≤m≤100000), 表示牛牛询问问题的个数。
 * 接下来m行，每行输入四个数字a1，b1，a2，b2(1≤a1＜a2≤1000), (1≤b1＜b2≤1000）
 * 题目保证两颗星星不会存在于同一个位置。
 *
 *
 * 输出描述:
 * 输出一共包含m行，每行表示与之对应的每个问题的答案。
 *
 * 输入例子1:
 * 4
 * 1 1
 * 2 2
 * 3 3
 * 1 3
 * 4
 * 1 1 2 2
 * 1 1 3 3
 * 2 2 3 3
 * 1 2 2 3
 *
 * 输出例子1:
 * 2
 * 4
 * 2
 * 2
 */
public class CountStars {

    /**
     * 运行超时:您的程序未能在规定时间内运行结束，请检查是否循环有错或算法复杂度过大。
     * case通过率为60.00%
     */
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int[] xs = new int[n];
//        int[] ys = new int[n];
//        for (int i=0; i<n; i++){
//            xs[i] = sc.nextInt();
//            ys[i] = sc.nextInt();
//        }
//        int question = sc.nextInt();
//        while (sc.hasNext()){
//            for (int i=0; i<question; i++){
//                int a1 = sc.nextInt();
//                int b1 = sc.nextInt();
//                int a2 = sc.nextInt();
//                int b2 = sc.nextInt();
//
//                int count = 0;
//                for (int j=0; j<n; j++){
//                    if(xs[j]>=a1 && ys[j]>=b1){
//                        if (xs[j]<=a2 && ys[j]<=b2){
//                            count++;
//                        }
//                    }
//                }
//                if (i==0){
//                    System.out.println();
//                }
//                System.out.println(count);
//            }
//        }
//    }

    /**
     * 运行超时:您的程序未能在规定时间内运行结束，请检查是否循环有错或算法复杂度过大。
     * case通过率为70.00%
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] data = new int[1001][1001];
        for (int i = 0; i < n; i++) {
            data[in.nextInt()][in.nextInt()] = 1;
        }
        //累加data，data[i][j]表示(i,j)和(1,1)构成的矩形阵列中的星星总数
        for (int i = 1; i < data.length; i++) {
            for (int j = 1; j < data.length; j++) {
                data[i][j] += data[i][j - 1] + data[i - 1][j] - data[i - 1][j - 1];
            }
        }

        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int a1 = in.nextInt();
            int b1 = in.nextInt();
            int a2 = in.nextInt();
            int b2 = in.nextInt();
            //分割出来所需的矩形阵列
            int sum = data[a2][b2] - data[a2][b1 - 1] - data[a1 - 1][b2] + data[a1 - 1][b1 - 1];
            System.out.println(sum);
        }
        in.close();
    }
}
