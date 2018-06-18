package Interview_Question;

/**
 * 百度编程题
 * 度度熊回家
 * 一个数轴上共有N个点，第一个点的坐标是度度熊现在位置，第N-1个点是度度熊的家。现在他需要依次的从0号坐标走到N-1号坐标。
 * 但是除了0号坐标和N-1号坐标，他可以在其余的N-2个坐标中选出一个点，并直接将这个点忽略掉，问度度熊回家至少走多少距离？
 */

import java.util.Scanner;

/**
 * 输入描述
 * 输入一个正整数N, N <= 50。
 * 接下来N个整数表示坐标，正数表示X轴的正方向，负数表示X轴的负方向。绝对值小于等于100
 *
 * 输出描述
 * 输出一个整数表示度度熊最少需要走的距离。
 *
 * 输入例子
 * 4
 * 1 4 -1 3
 *
 * 输出例子
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
            // 计算从第一个坐标点，到当前坐标点的路径距离
            minLength += Math.abs(instance[i]-instance[Math.max(i-1,0)]);
        }

        // 需要忽略的坐标点
        int surplus = 0;
        for (int i=1; i<num-1; i++){
            // 如果当前点到中间点再到后一点距离之和大于当前点直接到后一点的距离，就是多走的路
            int d = Math.abs(instance[i - 1] - instance[i])
                    + Math.abs(instance[i] - instance[i + 1])
                    - Math.abs(instance[i - 1] - instance[i + 1]);
            // 找到这些多走的路中最大的一个距离
            surplus = Math.max(surplus,d);
        }

        // 删除掉这个距离，就是度度熊回家至少走多少距离
        System.out.println(minLength - surplus);

    }
}
