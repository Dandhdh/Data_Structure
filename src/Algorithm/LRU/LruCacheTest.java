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

        //因为我们在后再对象时，accessOrder设置为true，访问一次 k2，k2对应的元素就会排在队尾部，被看做最新元素

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
