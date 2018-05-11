package Algorithm.BloomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.HashSet;
import java.util.Random;

/**
 * ��¡����
 * ��¡����������������k(k>1)k(k>1)���໥�����Ĺ�ϣ��������֤�ڸ����Ŀռ䡢�������£����Ԫ�����صĹ��̡�
 * ��k=3ʱ,
 * �������ݣ�����x���ɹ�ϣ����ӳ�佫������Bitmap�е�3��λ����Ϊ1
 * �жϴ��ڣ������� w ����ʱ������3����־λ��Ϊ1ʱ���ű�ʾw�ڼ����С�
 *
 * �ŵ㣺�洢�ռ�Ͳ��� / ��ѯʱ�䶼�ǳ���O(k)
 * ȱ�㣺
 * ���Ŵ����Ԫ���������ӣ���������֮���ӡ��������Ԫ������̫�٣���ʹ��ɢ�б����ӡ�
 * һ������²��ܴӲ�¡��������ɾ��Ԫ��.
 * ��Ϊɾ����Ӱ�쵽�����ַ�����ʵ����Ҫɾ���ַ����Ŀ���ʹ��Counting bloomfilter(CBF)������һ�ֻ���Bloom Filter�ı��壬CBF������Bloom Filterÿһ��Bit��Ϊһ���������������Ϳ���ʵ��ɾ���ַ����Ĺ����ˡ�
 */

public class BloomFilterTest {

    static int sizeOfNumberSet = Integer.MAX_VALUE >> 4;

    static Random generator = new Random();

    public static void main(String[] args) {

        int error = 0;
        HashSet<Integer> hashSet = new HashSet<Integer>();
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), sizeOfNumberSet);

        for(int i = 0; i < sizeOfNumberSet; i++) {
            int number = generator.nextInt();
            if(filter.mightContain(number) != hashSet.contains(number)) {
                error++;
            }
            filter.put(number);
            hashSet.add(number);
        }

        System.out.println("Error count: " + error + ", error rate = " + String.format("%f", (float)error/(float)sizeOfNumberSet));
    }

}
