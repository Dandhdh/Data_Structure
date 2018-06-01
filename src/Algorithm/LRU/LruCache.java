package Algorithm.LRU;

import java.util.LinkedHashMap;

/**
 * ʵ��˼·��
 *
 * �� Java �л��� LinkedHashMap �࣬�ṩ��һ���Զ���������Ԫ�صĹ��ܣ�
 * ����������ʣ����Խ������һ��LRU��Least Recently Used ����ʾ�������ʹ�ã�����ʹ�á�
 *
 * ?�� LinkedHashMap ����ɻ��棬��Ҫ��д LinkedHashMap �� removeEldestEntry(Map.Entry<K,V> eldest)��
 * ����������ķ����� protected ����������ֱ�ӵ��ã�ֻ�ܼ̳���д��
 * ����������ʱ������ put ���� putAll ʱ�������������������ж��Ƿ��Ƴ�����Ԫ�أ�
 * ���� true ��ʾɾ��������ɾ����
 *
 * Java Դ�����У��÷���ֱ�ӷ��� false������ͼ��ʾ��������ר��������������չ�
 *
 */
public class LruCache<K, V> extends LinkedHashMap<K, V> {
    private static final long serialVersionUID = 4504158311663914052L;

    private int maxCacheSize;

    public LruCache(int maxCacheSize) {

        // ����������Ϊ accessOrder��Ĭ��Ϊfalse����ʾ���հ��շ���˳������Ԫ�أ�������ʵ�Ԫ�ػ������ڶ�ĩβ

        super(maxCacheSize, 0.75f, true);

        this.maxCacheSize = maxCacheSize;

    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {

        // ���ﵽԤ�軺������ʱɾ������Ԫ��
        return this.size() >= maxCacheSize + 1;
    }


}