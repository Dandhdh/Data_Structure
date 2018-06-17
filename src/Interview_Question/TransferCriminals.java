package Interview_Question;

/**
 * �ٶȱ����
 * ת���ﷸ
 * C������Ҫת��һ���ﷸ��D�У�C����n���ﷸ����������ʱ����˳��
 * ������ÿ���ﷸ��һ������ֵ��ֵԽ����Խ�ء�����Ϊ�˷�������г�����ת������ʱ��������c�����ˣ�
 * ͬʱҪ��ת�Ʒ��˵�����ֵ֮�Ͳ�����t�����ж�����ѡ��ķ�ʽ��
 */

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

/**
 * ��������:
 * ��һ��������������:n��t��c(1��n��2e5,0��t��1e9,1��c��n)��
 * �ڶ��а�����ʱ�����ÿ�����˵�����ֵai(0��ai��1e9)
 * <p>
 * �������:
 * һ�������
 * <p>
 * ��������
 * 3 100 2
 * 1 2 3
 * <p>
 * �������
 * 2
 */

/**
 * ˼·��
 * ��ʵ��������ö�̬�滮��˼������
 * ���ȼ���ǰc������ֵ֮�ͣ�Ȼ��ÿ�ƶ�һ����λ�����ȥ��һ��Ԫ�أ��ټ��������ӵ�Ԫ�أ��м��Ԫ�ض������ˡ�
 * �����Ļ����ӶȾͱ�Ϊ���Ե��ˡ�
 */

public class TransferCriminals {
    // ��n����ѡ��c��������,������⣬�ݹ飬�ҳ����е���ϣ�����������count++
    public int transport(int n, int t, int c, int[] a) {
        int count = 0;
        int sum = 0;
        if (a == null || n <= 0 || t < 0 || c <= 0) {
            return 0;
        }
        // ������ʼ��������
        for (int i = 0; i < c; i++) {
            sum += a[i];
        }
        if (sum <= t)
            count++;
        for (int i = c; i < n; i++) {
            // �����������һ����ǰ��һ��
            sum = sum + a[i] - a[i - c];
            if (sum <= t)
                count++;
        }
        return count;

    }

    public static void main(String[] args) {
        // ��һ��������������:n��t��c(1��n��2e5,0��t��1e9,1��c��n)��
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();// ��n���ﷸ
            int t = sc.nextInt();// �������ֵ֮��
            int c = sc.nextInt();// ת��c������
            // �ڶ��а�����ʱ�����ÿ�����˵�����ֵai(0��ai��1e9)
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = sc.nextInt();
            TransferCriminals convict = new TransferCriminals();
            System.out.println(convict.transport(n, t, c, a));
        }
    }

}