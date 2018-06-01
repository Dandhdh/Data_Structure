package Algorithm.LRU;

import java.util.LinkedHashMap;

/**
 * 实现思路：
 *
 * 在 Java 中基于 LinkedHashMap 类，提供了一个自动清理最老元素的功能，
 * 基于这个特质，可以将改造成一个LRU（Least Recently Used ，表示最近最少使用）缓存使用。
 *
 * ?将 LinkedHashMap 改造成缓存，需要重写 LinkedHashMap 中 removeEldestEntry(Map.Entry<K,V> eldest)，
 * 这个方法，改方法是 protected 方法，不能直接调用，只能继承重写。
 * 当插入数据时（调用 put 或者 putAll 时）会调用这个方法用于判断是否移除最老元素，
 * 返回 true 表示删除，否则不删除，
 *
 * Java 源代码中，该方法直接返回 false，如下图所示，看来是专门留给开发者扩展额。
 *
 */
public class LruCache<K, V> extends LinkedHashMap<K, V> {
    private static final long serialVersionUID = 4504158311663914052L;

    private int maxCacheSize;

    public LruCache(int maxCacheSize) {

        // 第三个参数为 accessOrder，默认为false。表示按照按照访问顺序排列元素，最近访问的元素会排雷在队末尾

        super(maxCacheSize, 0.75f, true);

        this.maxCacheSize = maxCacheSize;

    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {

        // 当达到预设缓存上限时删除最老元素
        return this.size() >= maxCacheSize + 1;
    }


}