package Interview_Question;

/**
 * ��һ���ɺܶ�ľ�����ɵļ��ϣ�ÿ��ľ���ж�Ӧ�ĳ��ȣ�
 * �����ܷ��ü����е���Щľ����ĳ��˳����β��������һ��������� 0 �ļ򵥶����
 * ������ľ����Ҫ���ϣ��򵥶���μ������Խ��Ķ���Ρ�
 *
 * ��ʼ�����ǿյģ������ֲ�����
 * Ҫô���������һ������Ϊ L ��ľ����Ҫôɾȥ�������Ѿ��е�ĳ��ľ����
 * ÿ�β����������㶼��Ҫ��֪�Ƿ����ü����е���Щľ������һ���򵥶���Ρ�
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

/**
 * ����������
 * ÿ���������������һ�����ݣ�ÿ�����ݵ�һ��Ϊһ�������� n ��ʾ����������(1 �� n �� 50000) ��
 * ��������n�У�ÿ�е�һ������Ϊ�������� i (i �� {1,2})��
 * �ڶ�������Ϊһ������ L(1 �� L �� 1,000,000,000)��
 * ��� i=1 �����ڼ����ڲ���һ������Ϊ L ��ľ������� i=2 ����ɾȥ�ڼ����ڵ�һ������Ϊ L ��ľ����
 * �������ݱ�֤ɾ��ʱ�����бض����ڳ���Ϊ L ��ľ��������������󼯺϶��Ƿǿյġ�
 *
 * ���������
 * ����ÿһ�β���������һ�������
 * ��������ڵ�ľ�����Թ��ɼ򵥶���Σ���� "Yes" ��������� "No"��
 *
 * �������ӣ�
 * 5
 * 1 1
 * 1 1
 * 1 1
 * 2 1
 * 1 2
 *
 * �������
 * No
 * No
 * Yes
 * No
 * No
 */

/**
 * ����˼·��
 * �жϼ������ܷ����һ���򵥶���εĻ�������Ϊ��ߵ�ֵmaxLenС�������ߵĺ�,
 * �༴��maxLen*2<sumLen(���бߵĺ�)
 */
public class WoodStick_puzzle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.nextLine();

        int maxLength = 0;
        HashMap<Integer,Integer> edges = new HashMap<>();

        for(int i=0; i<num; i++){
            int operate = sc.nextInt();
            int length = sc.nextInt();

            if (operate==1){
                maxLength = maxLength>length ? maxLength:length;
                if(edges.containsKey(length)){
                    edges.put(length,edges.get(length)+1);
                }else {
                    edges.put(length,1);
                }
            }else if(operate==2){
                if(edges.get(length)==1){
                    edges.remove(length);
                }else {
                    edges.put(length,edges.get(length)-1);
                }
            }else {
                return;
            }

            System.out.println(isPolygon(edges,maxLength));
        }
    }

    public static String isPolygon(HashMap<Integer,Integer> edges, int maxLength){
        int sumLength = 0;
        for (Integer length : edges.keySet()){
            sumLength += length*edges.get(length);
            if(sumLength>maxLength*2){
                return "YES";
            }
        }
        return "NO";
    }

}
