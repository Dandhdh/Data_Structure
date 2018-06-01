package Algorithm.LRU;

import java.util.HashMap;
import java.util.Map;

/**
 * ʵ��Ҫ�㣺
 *
 * ������ֱ������ �ڴ��HashMap ����ŵġ�
 * �ڲ�ʹ����һ��˫��������������ݵ�˳��������һ��ͷ��� header���Լ�β��� tailer��
 * ÿ��д��ͷ��㣬ɾ��β���ʱ���������� header tailer��
 *
 * ʹ�������ƶ�������ͷʱ��
 * ��һ������Ҫ��˫���������ҵ��ýڵ㡣��������ֳ�����������ˡ�����Ч�ʺܵͣ������Ҫ O(N)��
 * ֮�������ڵ�ǰ�ڵ�����ƶ���
 *
 * ��д��ͷ���ʱ���ж������С���� 2 ʱ��Ҫɾ����ʼ����ͷβ��㡣
 * ������Ϊ��ʼ��ʱ������������˫��ڵ㣬û������ֻ��Ϊ���γ�һ�����ݽṹ��
 * ����ʵ���ݽ���֮����Ҫɾ���Է�������Ĳ����������Լ����Ż�����
 * ���ϵ����в��������̲߳���ȫ�ģ���Ҫʹ�������п���
 *
 */
public class LRUMap<K, V> {
    private final Map<K, V> cacheMap = new HashMap<>();

    /**
     * ��󻺴��С
     */
    private int cacheSize;

    /**
     * �ڵ��С
     */
    private int nodeCount;


    /**
     * ͷ���
     */
    private Node<K, V> header;

    /**
     * β���
     */
    private Node<K, V> tailer;

    public LRUMap(int cacheSize) {
        this.cacheSize = cacheSize;
        //ͷ������һ�����Ϊ��
        header = new Node<>();
        header.next = null;

        //β������һ�����Ϊ��
        tailer = new Node<>();
        tailer.tail = null;

        //˫������ ͷ�����Ͻ��ָ��β���
        header.tail = tailer;

        //β�����½��ָ��ͷ���
        tailer.next = header;


    }

    public void put(K key, V value) {
        cacheMap.put(key, value);

        //˫����������ӽ��
        addNode(key, value);
    }

    public V get(K key){

        Node<K, V> node = getNode(key);

        //�ƶ���ͷ���
        moveToHead(node) ;

        return cacheMap.get(key);
    }

    private void moveToHead(Node<K,V> node){

        //���������һ���ڵ�
        if (node.tail == null){
            node.next.tail = null ;
            tailer = node.next ;
            nodeCount -- ;
        }

        //����Ǳ�������ͷ�ڵ� ��������
        if (node.next == null){
            return ;
        }

        //��������м�ڵ�
        if (node.tail != null && node.next != null){
            //������һ�ڵ�ָ��������һ�ڵ� Ҳ��ɾ����ǰ�ڵ�
            node.tail.next = node.next ;
            nodeCount -- ;
        }

        //�����ͷ�����ӵ�ǰ�ڵ�
        //ע��������Ҫ���� new һ�����󣬲�Ȼԭ����node ��������������ã�������ڴ������
        node = new Node<>(node.getKey(),node.getValue()) ;
        addHead(node) ;

    }

    /**
     * �����ѯ Ч�ʽϵ�
     * @param key
     * @return
     */
    private Node<K,V> getNode(K key){
        Node<K,V> node = tailer ;
        while (node != null){

            if (node.getKey().equals(key)){
                return node ;
            }

            node = node.next ;
        }

        return null ;
    }


    /**
     * д��ͷ���
     * @param key
     * @param value
     */
    private void addNode(K key, V value) {

        Node<K, V> node = new Node<>(key, value);

        //��������ɾ�����һ��
        if (cacheSize == nodeCount) {
            //ɾ��β���
            delTail();
        }

        //д��ͷ���
        addHead(node);

    }



    /**
     * ���ͷ���
     *
     * @param node
     */
    private void addHead(Node<K, V> node) {

        //д��ͷ���
        header.next = node;
        node.tail = header;
        header = node;
        nodeCount++;

        //���д������ݴ���2�� �ͽ���ʼ����ͷβ���ɾ��
        if (nodeCount == 2) {
            tailer.next.next.tail = null;
            tailer = tailer.next.next;
        }

    }    

    private void delTail() {
        //��β���ӻ�����ɾ��
        cacheMap.remove(tailer.getKey());

        //ɾ��β���
        tailer.next.tail = null;
        tailer = tailer.next;

        nodeCount--;

    }

    private class Node<K, V> {
        private K key;
        private V value;
        Node<K, V> tail;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder() ;
        Node<K,V> node = tailer ;
        while (node != null){
            sb.append(node.getKey()).append(":")
                    .append(node.getValue())
                    .append("-->") ;

            node = node.next ;
        }


        return sb.toString();
    }
}