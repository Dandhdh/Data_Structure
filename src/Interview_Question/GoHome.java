package Interview_Question;

/**
 * �ٶȱ����
 * �ȶ��ܻؼ�
 * һ�������Ϲ���N���㣬��һ����������Ƕȶ�������λ�ã���N-1�����Ƕȶ��ܵļҡ���������Ҫ���εĴ�0�������ߵ�N-1�����ꡣ
 * ���ǳ���0�������N-1�����꣬�������������N-2��������ѡ��һ���㣬��ֱ�ӽ��������Ե����ʶȶ��ܻؼ������߶��پ��룿
 */

import java.util.Scanner;

/**
 * ��������
 * ����һ��������N, N <= 50��
 * ������N��������ʾ���꣬������ʾX��������򣬸�����ʾX��ĸ����򡣾���ֵС�ڵ���100
 *
 * �������
 * ���һ��������ʾ�ȶ���������Ҫ�ߵľ��롣
 *
 * ��������
 * 4
 * 1 4 -1 3
 *
 * �������
 * 4
 *
 */
public class GoHome {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] instance = new int[num];

        int minLength = 0;
        for (int i=0; i<num; i++){
            instance[i] = sc.nextInt();
            // ����ӵ�һ������㣬����ǰ������·������
            minLength += Math.abs(instance[i]-instance[Math.max(i-1,0)]);
        }

        // ��Ҫ���Ե������
        int surplus = 0;
        for (int i=1; i<num-1; i++){
            // �����ǰ�㵽�м���ٵ���һ�����֮�ʹ��ڵ�ǰ��ֱ�ӵ���һ��ľ��룬���Ƕ��ߵ�·
            int d = Math.abs(instance[i - 1] - instance[i])
                    + Math.abs(instance[i] - instance[i + 1])
                    - Math.abs(instance[i - 1] - instance[i + 1]);
            // �ҵ���Щ���ߵ�·������һ������
            surplus = Math.max(surplus,d);
        }

        // ɾ����������룬���Ƕȶ��ܻؼ������߶��پ���
        System.out.println(minLength - surplus);

    }
}
