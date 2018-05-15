package Algorithm.Algorithm_RedPackage;

/**
 * 绳段切割法
 * 实现红包算法
 *
 * 把红包总金额想象成一条很长的线段，而每个人抢到的金额，则是这条主线段所拆分出的若干子线段。
 * 当N个人一起抢红包的时候，就需要确定N-1个切割点。
 * 因此，当N个人一起抢总金额为M的红包时，我们需要做N-1次随机运算，以此确定N-1个切割点。
 * 随机的范围区间是（1， M）。当所有切割点确定以后，子线段的长度也随之确定。这样每个人来抢红包的时候，只需要顺次领取与子线段长度等价的红包金额即可。
 * 这就是线段切割法的思路。
 *
 * 在这里需要注意以下两点：
 * (1)当随机切割点出现重复，如何处理   --- 重复了就重新切呗
 * (2)如何尽可能降低时间复杂度和空间复杂度 --- 这里我用链表，牺牲时间换取空间（排了个序），也可以牺牲空间节省时间（大数组）
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
            System.out.println("抢到的金额："+new BigDecimal(integer).divide(new BigDecimal(100)));
        }
    }

}
