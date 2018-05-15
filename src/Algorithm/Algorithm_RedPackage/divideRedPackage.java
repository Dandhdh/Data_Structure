package Algorithm.Algorithm_RedPackage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ������ֵ��
 *
 * ʣ�������ΪM��ʣ������ΪN��
 * ��ô�����¹�ʽ��ÿ�������Ľ�� = ������� ��0�� M / N * 2�������ʽ��
 * ��֤��ÿ���������ƽ��ֵ����ȵģ�������Ϊ��������Ⱥ�˳�����ɲ���ƽ��
 *
 * ���ӣ�
 * ������10���ˣ�����ܶ�100Ԫ��100/10*2 = 20,
 * ���Ե�һ���˵������Χ�ǣ�0��20 )��ƽ����������10Ԫ��
 * �����һ���������10Ԫ����ôʣ������100-10 = 90 Ԫ��90/9X2 = 20,
 *
 * ���Եڶ����˵������Χͬ���ǣ�0��20 )��ƽ����������10Ԫ��
 * ����ڶ����������10Ԫ����ôʣ������90-10 = 80 Ԫ��80/8X2 = 20,
 *
 * ���Ե������˵������Χͬ���ǣ�0��20 )��ƽ����������10Ԫ��
 *
 * �Դ����ƣ�ÿһ�������Χ�ľ�ֵ����ȵ�
 *
 */

/**
 * ���㣺
 * ��Ȼ��ƽ�����ǳ������һ�Σ��κ�һ�������Ľ�ҪС���˾���������
 * ���������������
 */

//������㷨���������Է�Ϊ��λ
public class divideRedPackage {

    public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum){
        List<Integer> amountList = new ArrayList<Integer>();
        Integer restAmount = totalAmount;
        Integer restPeopleNum = totalPeopleNum;

        Random random = new Random();

        for(int i=0;i<totalPeopleNum-1; i++){
            //�����Χ�ڣ�[1,ʣ���˾���������) -- ����ҿ�
            int amount = random.nextInt(restAmount / restPeopleNum*2-1)+1;
            restAmount-=amount;
            restPeopleNum--;
            amountList.add(amount);
        }

        amountList.add(restAmount);
        return  amountList;
    }

    public static void main(String[] args) {
        List<Integer> amountList = divideRedPackage(500,5);
        for(Integer amount : amountList){
            System.out.println("�����Ľ�"+new BigDecimal(amount).divide(new BigDecimal(100)));
        }
    }

}
