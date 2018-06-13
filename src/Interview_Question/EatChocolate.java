package Interview_Question;

/**
 * ��Ѷ�����⣺
 * ̰�Ե�СQ�����ɿ�����
 * СQ�ĸ�ĸҪ����N�죬��֮ǰ��СQ������M���ɿ�����СQ����ÿ��Ե��ɿ�������������ǰһ��Ե�һ�룬
 * �������ֲ����ڸ�ĸ����֮ǰ��ĳһ��û���ɿ����ԣ���������һ������ܳԶ��ٿ��ɿ���
 */
/**
 * ��������:
 * ÿ���������һ������������
 * ÿ�����������ĵ�һ�а�����������������ʾ��ĸ���������N(N<=50000)���ɿ���������M(N<=M<=100000)��
 *
 * �������:
 * ���һ������ʾСQ��һ������ܳԶ��ٿ��ɿ�����
 *
 * ��������
 * 3 7
 *
 * �������
 * 4
 */

//˼·����һ��Եþ����࣬�Ժ�����ӳԵľ����٣���Ϊǰһ��Ķ���֮һ��
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class EatChocolate {

    public static Set set = new HashSet();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            long days = sc.nextLong();
            long value = sc.nextLong();

            // ͨ�����ַ��������Ե�һ��óԶ��ٺ���
            // mid ��ʾ��һ�����Զ��ٿ��ɿ���
            long left = 0;
            long right = value;
            while (left < right) {
                long mid = (right - left) / 2 + left;
                int temp = isValid(mid, days, value);
                if (temp < 0) {
                    right = mid - 1;
                } else if (temp > 0) {
                    left = mid + 1;
                } else {
                    break;
                }
            }
            long res = (right - left) / 2 + left;
            if (isValid(res, days, value) < 0) {
                System.out.println(res - 1);
            } else {
                System.out.println(res);
            }
        }
        sc.close();
    }
    public static int isValid(long start, long days, long value) {
        if (set.contains(start)) {
            return -1;
        }
        // temp��ʾ�Ѿ��Ե����ɿ�������
        long temp = 0;
        for (int i = 0; i < days; i++) {
            temp += start;
            if (temp > value) {
                // ���Ѿ������ϵĽ����������set�У������´��ظ�����
                set.add(start);
                return -1;
            }
            // �ڶ���ԵĿ���
            start = (long) Math.ceil((double) (start / 2.0));
        }
        // temp=1����ʣ��
        // temp=0���պó���
        return temp < value ? 1 : 0;
    }
}