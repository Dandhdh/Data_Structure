package Interview_Question;

/**
 * ��Ѷ������
 *
 * ţţ������������һ��ֽ����Ϸ�������Ϸһ����n��ֽ��, ��i��ֽ����д������ai��
 * ţţ��������������, ţţ�ȳ�, ÿ�γ������ǿ��Դ�ֽ�ƶ�������ѡ��һ�ų��, ֱ��ֽ�Ʊ����ꡣ
 * ���ǵĵ÷ֵ������ǳ鵽��ֽ�������ܺ͡�
 * ���ڼ���ţţ�����򶼲������Ų���, ����������Ϸ������ţţ�÷ּ�ȥ����÷ֵ��ڶ��١�
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * ��������:
 * ����������С�
 * ��һ�а���һ��������n(1 <= n <= 105),��ʾֽ�Ƶ�������
 * �ڶ��а���n��������ai(1 <= ai <= 109),��ʾÿ��ֽ���ϵ����֡�
 *
 * �������:
 * ���һ������, ��ʾ��Ϸ������ţţ�÷ּ�ȥ����÷ֵ��ڶ��١�
 *
 * ��������
 * 3
 * 2 7 4
 *
 * �������
 * 5
 *
 */
public class CardGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        sc.nextLine();
        long[] nums = new long[num];
        for (int i=0; i<num; i++){
            nums[i] = sc.nextInt();
        }

        Arrays.sort(nums);

        long sum = 0;
        for (int i=nums.length-1; i>=0; i-=2){
            if (i==0){
                sum+=nums[0];
                break;
            }
            sum = sum+ nums[i]-nums[i-1];
        }
        System.out.println(sum);
    }
}
