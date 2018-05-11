package Algorithm.BigData;

import java.util.BitSet;

public class BitMapTest {

    byte[] tem;

    public BitMapTest(int length) {
        this.tem = new byte[length];
    }

    public void add(int num) {
        if (num < tem.length) {
            if (tem[num] != 1) {
                tem[num] = 1;
            }
        }
    }

    public boolean contain(int num) {
        if (num < tem.length) {
            if (tem[num] == 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        /*����ǰ�ڴ�*/
        long beforeMemory = Runtime.getRuntime().totalMemory();
        System.out.println("����ǰ�ڴ棺"+beforeMemory);
        long start1=System.currentTimeMillis();
        BitSet set = new BitSet(2000000000);
        for (int i = 0; i < 2000000000; i++) {
            /*����898989���������20�ڸ�������*/
            if (i != 898989) {
                set.set(i, true);
            }
        }
        /*����20�ڸ�������ռ�ڴ�*/
        long afterMemory = Runtime.getRuntime().totalMemory();
        long end1=System.currentTimeMillis();
        System.out.println("���к��ڴ棺"+afterMemory);
        System.out.println("�ܹ��ڴ�ʹ��:" + (afterMemory - beforeMemory) / 1024 / 1024 + "MB");
        System.out.println("�����ڴ��ʱ:"+(end1-start1)+"����");
        long start2 = System.currentTimeMillis();
        boolean isExit1=set.get(898989);
        boolean isExit2=set.get(900000);

        long end2 = System.currentTimeMillis();
        /*�����20�ڸ������ж�898989�Ƿ����������*/
        System.out.println(isExit1);
        System.out.println("20������"+(isExit1?"����":"������")+898989);
        System.out.println("20������"+(isExit2?"����":"������")+900000);
        System.out.println("��ѯ��ʱ:"+(end2 - start2)+"����");
    }

}
