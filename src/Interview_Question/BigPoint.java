package Interview_Question;

/**
 * 今日头条笔试编程题
 * “最大”的点：
 *
 * P为给定的二维平面整数点集。
 * 定义 P 中某点x，如果x满足 P 中任意点都不在 x 的右上方区域内（横纵坐标都大于x），则称其为“最大的”。
 * 求出所有“最大的”点的集合。（所有点的横坐标和纵坐标都不重复, 坐标轴范围在[0, 1e9) 内）
 *
 */

import java.util.*;

/**
 * 输入描述:
 * 第一行输入点集的个数 N， 接下来 N 行，每行两个数字代表点的 X 轴和 Y 轴。
 * 对于 50%的数据,  1 <= N <= 10000;
 * 对于 100%的数据, 1 <= N <= 500000;
 *
 *
 * 输出描述:
 * 输出“最大的” 点集合， 按照 X 轴从小到大的方式输出，每行两个数字分别代表点的 X 轴和 Y轴。
 *
 * 输入例子1:
 * 5
 * 1 2
 * 5 3
 * 4 6
 * 7 5
 * 9 0
 *
 * 输出例子1:
 * 4 6
 * 7 5
 * 9 0
 */

/**
 * 思路：容易看出，当一个点满足x轴或y轴任意一个坐标比以存在的点的x轴或y轴的坐标大，即为以存在的点的“最大”。
 * 因此可以考虑先将点按x轴做坐标排序，然后对y轴维护一个单调非增的栈 。栈中的点即为”最大的”
 *
 * 注意：此方法时间复杂过大
 * 运行超时:您的程序未能在规定时间内运行结束，请检查是否循环有错或算法复杂度过大。
 * case通过率为60.00%
 */
public class BigPoint {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pointNum = sc.nextInt();
        Point[] pointSet = new Point[pointNum];
        for (int i=0; i<pointNum; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            Point point = new Point(x,y);
            pointSet[i] = point;
        }

        // 构造比较器（x降序，y升序）
//        Arrays.sort(pointSet, new Comparator<Point>() {
//            @Override
//            public int compare(Point o1, Point o2) {
//                if(o1.x>o2.x){
//                    return -1;
//                }
//                return 1;
//            }
//        });
        Arrays.sort(pointSet);

        int maxHigh = pointSet[0].y;
        LinkedList<Point> linked = new LinkedList<>();
        linked.addLast(pointSet[0]);
        for (int i=1; i<pointNum; i++){
            if (pointSet[i].y>maxHigh){
                linked.addFirst(pointSet[i]);
                maxHigh = pointSet[i].y;
            }
        }

        while (!linked.isEmpty()){
            Point point = linked.removeFirst();
            System.out.println(point.x+" "+point.y);
        }
    }

    static class Point implements Comparable<Point>{
        int x;
        int y;
        public Point(int x,int y){
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }

        // 使用实现Comparable接口的比较方法更慢，只通过50%的案例（相比于Arrays.sort(pointSet, new Comparator<Point>{});）
        // x降序
        @Override
        public int compareTo(Point o){
            return o.x-x;
        }
    }
}
