package Algorithm.BloomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.HashSet;
import java.util.Random;

/**
 * 布隆过滤
 * 布隆过滤器就是引入了k(k>1)k(k>1)个相互独立的哈希函数，保证在给定的空间、误判率下，完成元素判重的过程。
 * 当k=3时,
 * 存入数据：数据x经由哈希函数映射将各自在Bitmap中的3个位置置为1
 * 判断存在：当数据 w 出现时，仅当3个标志位都为1时，才表示w在集合中。
 *
 * 优点：存储空间和插入 / 查询时间都是常数O(k)
 * 缺点：
 * 随着存入的元素数量增加，误算率随之增加。但是如果元素数量太少，则使用散列表足矣。
 * 一般情况下不能从布隆过滤器中删除元素.
 * 因为删除会影响到其他字符串。实在需要删除字符串的可以使用Counting bloomfilter(CBF)，这是一种基本Bloom Filter的变体，CBF将基本Bloom Filter每一个Bit改为一个计数器，这样就可以实现删除字符串的功能了。
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
