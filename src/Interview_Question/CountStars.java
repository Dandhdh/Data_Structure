package Interview_Question;

/**
 * ţ����ģ�������
 * ţţ������
 * һ��һ�������������춼��С���ǣ�ţţ�����������ģ������ڴ��������ǡ�
 * ţţ������ͼ����һ��ƽ�棬���Ͻ�Ϊԭ��(����Ϊ(1, 1))��
 * ������n�����ǣ�����ÿ�����Ƕ���������(xi��yi)����ʾ��������ڵ�x�У���y�С�
 * ���ڣ�ţţ������m�����⣬���������������(a1, b1)(a2��b2)����ʾһ�����ε����Ͻǵĵ���������½ǵĵ����꣬
 * ����������������ж��ٿ����ǣ��߽��ϵĵ�Ҳ���Ǿ����ڣ���
 */

import java.util.Scanner;

/**
 * ��������:
 * ��һ������һ������n(1��n��100000)����ʾ���ǵĿ�����
 * ��������n�У�ÿ������������xi��yi(1��xi��yi��1000������ʾ���ǵ�λ�á�
 * Ȼ������һ������m(1��m��100000), ��ʾţţѯ������ĸ�����
 * ������m�У�ÿ�������ĸ�����a1��b1��a2��b2(1��a1��a2��1000), (1��b1��b2��1000��
 * ��Ŀ��֤�������ǲ��������ͬһ��λ�á�
 *
 *
 * �������:
 * ���һ������m�У�ÿ�б�ʾ��֮��Ӧ��ÿ������Ĵ𰸡�
 *
 * ��������1:
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
 * �������1:
 * 2
 * 4
 * 2
 * 2
 */
public class CountStars {

    /**
     * ���г�ʱ:���ĳ���δ���ڹ涨ʱ�������н����������Ƿ�ѭ���д���㷨���Ӷȹ���
     * caseͨ����Ϊ60.00%
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
     * ���г�ʱ:���ĳ���δ���ڹ涨ʱ�������н����������Ƿ�ѭ���д���㷨���Ӷȹ���
     * caseͨ����Ϊ70.00%
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] data = new int[1001][1001];
        for (int i = 0; i < n; i++) {
            data[in.nextInt()][in.nextInt()] = 1;
        }
        //�ۼ�data��data[i][j]��ʾ(i,j)��(1,1)���ɵľ��������е���������
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
            //�ָ��������ľ�������
            int sum = data[a2][b2] - data[a2][b1 - 1] - data[a1 - 1][b2] + data[a1 - 1][b1 - 1];
            System.out.println(sum);
        }
        in.close();
    }
}
