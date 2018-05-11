package Algorithm.BigMap;

/**
 * Bigmaps算法
 * 例题：
 * 腾讯面试题：给20亿个不重复的unsigned int的整数，没排过序的，然后再给一个数，
 * 如何快速判断这个数是否在那40亿个数当中并且所耗内存尽可能的少？
 *
 *  所谓bitmap，就是用每一位来存放某种状态，适用于大规模数据，但数据状态又不是很多的情况。通常是用来判断某个数据存不存在的。
 *  例如，要判断一千万个人的状态，每个人只有两种状态：男人，女人，可以用0，1表示。那么就可以开一个int数组，一个int有32个位，就可以表示32个人。操作的时候可以使用位操作。
 */

/**
 * 使用场景 ———— 大数据去重
 * 其实，就是按int从小到大的顺序依次摆放到byte[]中，
 * 仅涉及到一些除以2的整次幂和对2的整次幂取余的位操作小技巧。
 * 很显然，对于小数据量、数据取值很稀疏，上面的方法并没有什么优势，
 * 但对于海量的、取值分布很均匀的集合进行去重，Bitmap极大地压缩了所需要的内存空间。
 *
 * 优点：空间复杂度不随原始集合内元素的个数增加而增加
 * 缺点：空间复杂度随集合内最大元素增大而线性增大。
 *
 */
public class Bitmap {

    public static final int _1MB = 1024 * 1024;

    // 512 MB = 2^29 Byte = 2^32 bit
    //理论上，可以存储2^32条数据（意思为只存储数据是否存在的标记）
    public static byte[] flags = new byte[ 512 * _1MB ];

    public static void main(String[] args) {

        int[] array = {255, 1024, 0, 65536,65536};

        int index = 0;
        for(int num : array) {
            if(!getFlags(num)) {
                //未出现的元素
                array[index] = num;
                index = index + 1;
                //设置标志位
                setFlags(num);
            }
        }
    }

    /**
     * |= : a|=b的意思就是把a和b按位或然后赋值给a 按位或的意思就是先把a和b都换成2进制，然后用或操作，相当于a=a|b
     */
    //存入数据
    public static void setFlags(int num) {
        flags[num >> 3] |= 0x01 << (num & (0x07));
    }

    //判断数据是否存在
    public static boolean getFlags(int num) {
        return (flags[num >> 3] >> (num & (0x07)) & 0x01 )!=0;
    }
}
