package Interview_Question;

/**
 * ����ͷ�����Ա����
 * ����󡱵ĵ㣺
 *
 * PΪ�����Ķ�άƽ�������㼯��
 * ���� P ��ĳ��x�����x���� P ������㶼���� x �����Ϸ������ڣ��������궼����x���������Ϊ�����ġ���
 * ������С����ġ���ļ��ϡ������е�ĺ�����������궼���ظ�, �����᷶Χ��[0, 1e9) �ڣ�
 *
 */

import java.util.*;

/**
 * ��������:
 * ��һ������㼯�ĸ��� N�� ������ N �У�ÿ���������ִ����� X ��� Y �ᡣ
 * ���� 50%������,  1 <= N <= 10000;
 * ���� 100%������, 1 <= N <= 500000;
 *
 *
 * �������:
 * ��������ġ� �㼯�ϣ� ���� X ���С����ķ�ʽ�����ÿ���������ֱַ������ X ��� Y�ᡣ
 *
 * ��������1:
 * 5
 * 1 2
 * 5 3
 * 4 6
 * 7 5
 * 9 0
 *
 * �������1:
 * 4 6
 * 7 5
 * 9 0
 */

/**
 * ˼·�����׿�������һ��������x���y������һ��������Դ��ڵĵ��x���y�������󣬼�Ϊ�Դ��ڵĵ�ġ���󡱡�
 * ��˿��Կ����Ƚ��㰴x������������Ȼ���y��ά��һ������������ջ ��ջ�еĵ㼴Ϊ�����ġ�
 *
 * ע�⣺�˷���ʱ�临�ӹ���
 * ���г�ʱ:���ĳ���δ���ڹ涨ʱ�������н����������Ƿ�ѭ���д���㷨���Ӷȹ���
 * caseͨ����Ϊ60.00%
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

        // ����Ƚ�����x����y����
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

        // ʹ��ʵ��Comparable�ӿڵıȽϷ���������ֻͨ��50%�İ����������Arrays.sort(pointSet, new Comparator<Point>{});��
        // x����
        @Override
        public int compareTo(Point o){
            return o.x-x;
        }
    }
}
