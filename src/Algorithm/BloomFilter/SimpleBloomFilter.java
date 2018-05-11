package Algorithm.BloomFilter;

import java.util.BitSet;
public class  SimpleBloomFilter {

     // BitSet初始分配2^24个bit
     private static final  int  DEFAULT_SIZE  =2 << 24 ;
     // 不同哈希函数的种子，一般应取质数
     private static final  int [] seeds =new  int []{5,7, 11 , 13 , 31 , 37 , 61};
     private  BitSet bits= new  BitSet(DEFAULT_SIZE);
     // 哈希函数对象
     private  SimpleHash[]  func=new  SimpleHash[seeds.length];

     public static void  main(String[] args) {
        String value  = "asdasda@163.com" ;
        SimpleBloomFilter filter=new  SimpleBloomFilter();
        System.out.println(filter.contains(value));
        filter.add(value);
        System.out.println(filter.contains(value));
    }

     public SimpleBloomFilter() {
         for( int  i= 0 ; i< seeds.length; i ++ ) {
            func[i]=new  SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
    }

     // 将字符串标记到bits中
     public void add(String value) {
         for(SimpleHash f : func) {
            bits.set(f.hash(value),  true );
        }
    }

    // 判断字符串是否已经被bits标记
     public boolean contains(String value) {
         if(value ==null ) {
             return false ;
        }
         boolean ret = true ;
         for(SimpleHash f : func) {
            ret=ret&& bits.get(f.hash(value));
        }
         return ret;
    }

     // 哈希函数类
     public static class SimpleHash {
         private int  cap;
         private int  seed;
         public SimpleHash( int cap, int seed) {
             this.cap= cap;
             this.seed =seed;
        }
         public int hash(String value) {
             int  result=0 ;
             int  len= value.length();
             for  (int i= 0 ; i< len; i ++ ) {
                result =seed* result + value.charAt(i);
            }
             return (cap - 1 ) & result;
        }
    }
}