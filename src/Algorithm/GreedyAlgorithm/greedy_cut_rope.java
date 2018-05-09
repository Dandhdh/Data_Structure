package Algorithm.GreedyAlgorithm;

/**
 * ����������
 *
 * ������
 * ����һ������Ϊn�����ӣ�������Ӽ���m�Σ�m��n�����������Ҷ�����1��
 * ÿ�����ӵĳ��ȼ�ΪK[0],K[1],K[2]...K[m]������K[0]*k[1]..*k[m]���ܵ����˻��Ƕ��٣�
 */

/**
 * ���˼·��
 * ������ǰ������µĲ��Լ�����,��õ��ĸ������ӵĳ��ȵĳ˻������
 *
 * ��n>=5,���Ǿ����ܵؼ�����Ϊ3������;��ʣ�µ����ӳ���Ϊ4ʱ,�����Ӽ�Ϊ����Ϊ2������.
 *
 * ̰���㷨�ĺ�����ͨ���ֲ����Ž����õ�ȫ�����Ž�,���ڷָ�������˵,Ҫʹ�˻����,�������̰��˼���Ǿ�����ȥ��Ϊ����Ϊ3������!
 */
public class greedy_cut_rope {

    //������
    public static int greedy_cut_rope_1(int n)
    {
        if(n<2)
            return 0;
        if(n==2)
            return 1;
        if(n==3)
            return 2;
        //�����ܶ��ȥ������Ϊ3�����Ӷ�
        int timesOf3 = n/3;
        //���������ʣ�µĳ���Ϊ4��ʱ�򣬲�����ȥ��ȥ����Ϊ3�����Ӷ�
        if(n-timesOf3*3==1)
            timesOf3-=1;
        int timesOf2 =(n-timesOf3*3)/2;
        return (int) (Math.pow(3,timesOf3)*Math.pow(2,timesOf2));
    }

    //�ݹ鷨
    public static int greedy_cut_rope(int n)
    {
        if(n==2)
            return 2;
        if(n==3)
            return 3;
        if(n<2)
            return 1;
        //int timesOf3 = n/3;
        if(n==4)
            return 4;
        return 3*greedy_cut_rope(n-3);
    }

    public static void main(String[] args) {
        System.out.println(greedy_cut_rope(12));
        System.out.println(greedy_cut_rope_1(12));
    }

}
