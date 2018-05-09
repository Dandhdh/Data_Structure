package Algorithm.GreedyAlgorithm;

/**
 * ����������
 *
 *������һ����Ҫʹĳһ��Դ��n�����ɵļ���S={a1��a2��a3...an}��
 * ����Դһ��ֻ�ܱ�һ���ռ�ã�ÿ���ai��һ����ʼʱ��Si�ͽ���ʱ��Fi����0<=Si<Fi<�ޡ�
 * һ����ѡ��󣬻ai��ռ�ݰ뿪ʱ������[Si,Fi)��
 * �������[Si��Fi)�� [Sj��Fj)�����ص����ƻai��aj�Ǽ��ݵġ�
 *
 * �ѡ���������Ҫѡ���һ���ɻ�����ݵ�������ɵ���󼯺ϡ�
 */

/**
 * ����˼·��
 *
 * ��������ǿ�������Sij����am��Sij�о����������ʱ��Ļ��
 * ��������fm=min{fk:ak��Sij}
 *
 * ��ô��
 * 1.�am��Sij��ĳ�����ݻ�Ӽ��б�ʹ�á�
 * 2.������SimΪ�գ�����ѡ��amʹ������SmjΪΨһ���ܷǿյ�������
 * ����
 * �ڽ��������ʱ��ѡ��am��һ���ɱ��Ϸ����ȡ������������ʱ��Ļ��
 * ��ֱ�������������ֻѡ�񷽷���һ��̰����������������ʣ�µĴ��������������˾����ܶ�Ļ��ᡣ
 * Ҳ����˵���˴���̰��ѡ��ʹ��ʣ�µġ�δ���ȵ�ʱ����󻯡�
 */
public class greedy_activity_selector {

    public static void greedy_activity_selector(int[] s,int[] f,boolean[] b)
    {
        int n = s.length-1;
        b[1]=true;
        int j=1;
        for(int i =2;i<=n;i++)
        {
            if(s[i]>f[j])
            {
                b[i]=true;
                j=i;
            }else
                b[i]=false;
        }
        for(int i=1;i<b.length;i++)
            System.out.println(b[i]);
    }

}
