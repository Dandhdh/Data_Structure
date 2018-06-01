package Algorithm.LRU;

import java.util.HashMap;
import java.util.Map;

public class LruCacheTest {
    public static void main(String[] args) {
        LruCache<String, String> cache = new LruCache<String, String>(3);

        cache.put("k1", "v1");

        System.out.println("test1:"+cache);

        cache.put("k2", "v2");

        System.out.println("test2:"+cache);

        cache.put("k3", "v3");

        System.out.println("test3:"+cache);

        cache.put("k4", "v4");

        System.out.println("test4:"+cache);

        //��Ϊ�����ں��ٶ���ʱ��accessOrder����Ϊtrue������һ�� k2��k2��Ӧ��Ԫ�ؾͻ����ڶ�β��������������Ԫ��

        cache.get("k2");
        System.out.println("test4.5:"+cache);
        cache.get("k4");
        cache.get("k4");

        System.out.println("test5:"+cache);


        Map<String,String> multiKV = new HashMap<String,String>();

        multiKV.put("k5", "k5");

        multiKV.put("k6", "k6");

        cache.putAll(multiKV);

        System.out.println("test5:"+cache);
    }
}
