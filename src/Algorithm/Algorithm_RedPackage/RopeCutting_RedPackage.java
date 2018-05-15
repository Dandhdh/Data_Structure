package Algorithm.Algorithm_RedPackage;

/**
 * �����и
 * ʵ�ֺ���㷨
 *
 * �Ѻ���ܽ�������һ���ܳ����߶Σ���ÿ���������Ľ������������߶�����ֳ����������߶Ρ�
 * ��N����һ���������ʱ�򣬾���Ҫȷ��N-1���и�㡣
 * ��ˣ���N����һ�����ܽ��ΪM�ĺ��ʱ��������Ҫ��N-1��������㣬�Դ�ȷ��N-1���и�㡣
 * ����ķ�Χ�����ǣ�1�� M�����������и��ȷ���Ժ����߶εĳ���Ҳ��֮ȷ��������ÿ�������������ʱ��ֻ��Ҫ˳����ȡ�����߶γ��ȵȼ۵ĺ�����ɡ�
 * ������߶��и��˼·��
 *
 * ��������Ҫע���������㣺
 * (1)������и������ظ�����δ���   --- �ظ��˾���������
 * (2)��ξ����ܽ���ʱ�临�ӶȺͿռ临�Ӷ� --- ����������������ʱ�任ȡ�ռ䣨���˸��򣩣�Ҳ���������ռ��ʡʱ�䣨�����飩
 */

import java.math.BigDecimal;
import java.util.*;

/**
 *
 */
public class RopeCutting_RedPackage {

    public static List<Integer> cutRope(Integer totalAmount, Integer totalPeopleNum){

        Random random = new Random();
        HashSet set = new HashSet();
        List<Integer> cutPoint = new ArrayList<>();

        HashSet<Integer> seeds = new HashSet<>();
        for(int i=0; i<totalPeopleNum-1; i++){
            int seed = random.nextInt(totalAmount-1)+1;
            if(set.contains(seed)){
                seed = random.nextInt(totalAmount-1)+1;
            }
            seeds.add(seed);
            cutPoint.add(seed);
        }
        Collections.sort(cutPoint);

        return cutPoint;
    }

    public static List<Integer> getRedPackageMoney(Integer totalAmount,Integer totalPeopleNum){

        List<Integer> amoutnList = new ArrayList<>();
        List<Integer> list = cutRope(totalAmount,totalPeopleNum);
        int preCut = 0;
        int cutPoint = 0;
        Integer restMoney = totalAmount;

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            cutPoint = iterator.next();
            amoutnList.add(cutPoint-preCut);
            preCut = cutPoint;
            totalAmount--;
            if(totalAmount==1){
                break;
            }
        }
        amoutnList.add(totalAmount-cutPoint);
        return amoutnList;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list = getRedPackageMoney(1000,10);
        for(Integer integer : list){
            System.out.println("�����Ľ�"+new BigDecimal(integer).divide(new BigDecimal(100)));
        }
    }

}
