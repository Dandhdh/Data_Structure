package Interview_Question;

/**
 * �ٶȱ��Ա���⣺
 * ��Ȥ������
 * �ȶ�����һ��N���������飬���뽫�����С���� �ź��򣬵������ȵĶȶ���ֻ���������������
 * ��ȡ�����е�һ����Ȼ������������������һ��λ�á�
 * �����ٲ������ٴο���ʹ�������С��������
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ��������:
 * ��������һ��������N����������һ������N��������(N <= 50, ÿ�����ľ���ֵС�ڵ���1000)
 *
 *
 * �������:
 * ���һ��������ʾ���ٵĲ���������
 *
 * ��������1:
 * 4
 * 19 7 8 25
 *
 * �������1:
 * 2
 */

/**
 * ����˼·
 * ����������ţ�����indexMap�洢����ǰ�����ֵ�index��
 * ��������������ڵ����������Ƚ�����ǰ��index��
 * �������������ǰindex��С������Ҫ�ƶ����������ƶ����isMove
 * ����ʹ�����㣬��һ�����������Ǳ�����С��ǰһ�������������ƶ�����������Ҳ��Ҫ�ƶ���
 */
public class FunnySort {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        // �洢����ǰ��index
        Map<Integer,Integer> indexMap = new HashMap<>();
        for (int i=0; i<n; i++){
            int v = sc.nextInt();
            nums[i] = v;
            indexMap.put(v,i);
        }
        Arrays.sort(nums);

        // ��Ҫ�ƶ��Ĵ���
        int count = 0;

        // ��� �����������е�ǰһ�������Ƿ���Ҫ�ƶ�
        boolean isMove = false;
        for (int i=0; i<n-1; i++){
            if (isMove){
                count++;
                continue;
            }
            if (indexMap.get(nums[i])>indexMap.get(nums[i+1])){
                count++;
                isMove = true;
            }
        }

        System.out.println(count);
    }
}
