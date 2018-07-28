package Algorithm.LRU;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ʵ��˼·��
 *
 * �������� HashMap һ���ı������ݷ�ʽ��ֻ���Լ��ֶ�ʵ����һ�����װ档
 * �ڲ�������һ������������ÿ��д������ݡ�
 * д���ʱ���жϻ����Ƿ��������ֵ N�������������ݶ��е� FIFO ���Խ�����ͷ������ɾ������Ϊ����ͷ�����ݿ϶������ȷŽ�ȥ�ġ�
 * �ٿ�����һ���ػ��߳������ж����ȷŽ�ȥ�������Ƿ��ڣ���Ϊ���㳬��Ҳ�����ȷŽ�ȥ���������п������㳬����������
 * ����Ϊ�ػ��߳̿��Ը��õı�����Ŀ�ģ��������£������һ���û��߳������п��ܵ��³����������˳�����Ϊ���߳�һֱ�����У��ػ��߳��򲻻�������������
 */

/**
 * ����:
 *
 * 1.���� key ���� hashcode ʱ���õ� HashMap �� hash ����
 * 2.���� put get ʱ��������� key ���ʱ��Ϊ�˼�û��ȥ�Ƚ� equal �� hashcode
 * 3.���ƴ�С�� map�����size��1024�� ����1024�󣬾���̭�����û�з��ʵ�kv ��ֵ�ԣ�
 * ����̭ʱ����Ҫ����һ��callback   lruCallback(K key, V value)
 * ������ÿ�� put ����ֵд��һ���ڲ����У�����ֻ��Ҫ�ж϶�����ĵ�һ�����ɡ�
 * 4.�߱���ʱ���ܣ� ����ֵ��1Сʱ��û�б����ʣ� �ͱ���̭��, ����̭ʱ�� ��Ҫ����һ��callback timeoutCallback(K key, V value);
 * ��ʱͬ����������һ���ػ�����������ȡ���Ƕ�����ĵ�һ�� ��Ϊ��һ��������Ž�ȥ�ġ�
 *
 * ������ HashMap ������ݣ������ڳ�����ֵ֮���û�п��ǽ�����
 *
 */

//���ϴ�����幦�������ˣ�������һ���������⡣
//�����������ʹ��û�����㣬ɾ�������ݶ������ȷ�������ݡ�

public class LRUAbstractMap extends java.util.AbstractMap {

    //private final static Logger LOGGER = LoggerFactory.getLogger(LRUAbstractMap.class);

    /**
     * ����Ƿ����߳�
     */
    private ExecutorService checkTimePool ;

    /**
     * map ���size
     */
    private final static int MAX_SIZE = 1024 ;

    private final static ArrayBlockingQueue<Node> QUEUE = new ArrayBlockingQueue<>(MAX_SIZE) ;

    /**
     * Ĭ�ϴ�С
     */
    private final static int DEFAULT_ARRAY_SIZE =1024 ;


    /**
     * ���鳤��
     */
    private int arraySize ;

    /**
     * ����
     */
    private Object[] arrays ;


    /**
     * �ж��Ƿ�ֹͣ flag
     */
    private volatile boolean flag = true ;


    /**
     * ��ʱʱ��
     */
    private final static Long EXPIRE_TIME = 60 * 60 * 1000L ;

    /**
     * ���� Map �Ĵ�С
     */
    private volatile AtomicInteger size  ;


    public LRUAbstractMap() {


        arraySize = DEFAULT_ARRAY_SIZE;
        arrays = new Object[arraySize] ;

        //����һ���̼߳�����ȷ�����е�ֵ�Ƿ���
        executeCheckTime();
    }

    /**
     * ����һ���̼߳�����ȷ�����е�ֵ�Ƿ��� ����Ϊ�ػ��߳�
     */
    private void executeCheckTime() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("check-thread-%d")
                .setDaemon(true)
                .build();
        checkTimePool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1),namedThreadFactory,new ThreadPoolExecutor.AbortPolicy());
        checkTimePool.execute(new CheckTimeThread()) ;

    }

    @Override
    public Set<Entry> entrySet() {
        return super.keySet();
    }

    @Override
    public Object put(Object key, Object value) {
        int hash = hash(key);
        int index = hash % arraySize ;
        Node currentNode = (Node) arrays[index] ;

        if (currentNode == null){
            arrays[index] = new Node(null,null, key, value);

            //д�����
            QUEUE.offer((Node) arrays[index]) ;

            sizeUp();
        }else {
            Node cNode = currentNode ;
            Node nNode = cNode ;

            //���ھ͸���
            if (nNode.key == key){
                cNode.val = value ;
            }

            while (nNode.next != null){
                //key ���� �͸��� ���ж�
                if (nNode.key == key){
                    nNode.val = value ;
                    break ;
                }else {
                    //�����ھ���������
                    sizeUp();
                    Node node = new Node(nNode,null,key,value) ;

                    //д�����
                    QUEUE.offer(currentNode) ;

                    cNode.next = node ;
                }

                nNode = nNode.next ;
            }

        }

        return null ;
    }


    @Override
    public Object get(Object key) {

        int hash = hash(key) ;
        int index = hash % arraySize ;
        Node currentNode = (Node) arrays[index] ;

        if (currentNode == null){
            return null ;
        }
        if (currentNode.next == null){

            //����ʱ��
            currentNode.setUpdateTime(System.currentTimeMillis());

            //û�г�ͻ
            return currentNode ;

        }

        Node nNode = currentNode ;
        while (nNode.next != null){

            if (nNode.key == key){

                //����ʱ��
                currentNode.setUpdateTime(System.currentTimeMillis());

                return nNode ;
            }

            nNode = nNode.next ;
        }

        return super.get(key);
    }


    @Override
    public Object remove(Object key) {

        int hash = hash(key) ;
        int index = hash % arraySize ;
        Node currentNode = (Node) arrays[index] ;

        if (currentNode == null){
            return null ;
        }

        if (currentNode.key == key){
            sizeDown();
            arrays[index] = null ;

            //�Ƴ�����
            QUEUE.poll();
            return currentNode ;
        }

        Node nNode = currentNode ;
        while (nNode.next != null){

            if (nNode.key == key){
                sizeDown();
                //���������ҵ��� ����һ���ڵ�� next ָ��ǰ�ڵ����һ���ڵ�
                nNode.pre.next = nNode.next ;
                nNode = null ;

                //�Ƴ�����
                QUEUE.poll();

                return nNode;
            }

            nNode = nNode.next ;
        }

        return super.remove(key);
    }

    /**
     * ����size
     */
    private void sizeUp(){

        //��putֵʱ����Ϊ����Ѿ���������
        flag = true ;

        if (size == null){
            size = new AtomicInteger() ;
        }
        int size = this.size.incrementAndGet();
        if (size >= MAX_SIZE) {
            //�ҵ�����ͷ������
            Node node = QUEUE.poll() ;
            if (node == null){
                throw new RuntimeException("data error") ;
            }

            //�Ƴ��� key
            Object key = node.key ;
            remove(key) ;
            lruCallback() ;
        }

    }

    /**
     * ������С
     */
    private void sizeDown(){

        if (QUEUE.size() == 0){
            flag = false ;
        }

        this.size.decrementAndGet() ;
    }

    @Override
    public int size() {
        return size.get() ;
    }

    /**
     * ����
     */
    private class Node{
        private Node next ;
        private Node pre ;
        private Object key ;
        private Object val ;
        private Long updateTime ;

        public Node(Node pre,Node next, Object key, Object val) {
            this.pre = pre ;
            this.next = next;
            this.key = key;
            this.val = val;
            this.updateTime = System.currentTimeMillis() ;
        }

        public void setUpdateTime(Long updateTime) {
            this.updateTime = updateTime;
        }

        public Long getUpdateTime() {
            return updateTime;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", val=" + val +
                    '}';
        }
    }


    /**
     * copy HashMap �� hash ʵ��
     * @param key
     * @return
     */
    public int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private void lruCallback(){
        //LOGGER.debug("lruCallback");
        System.out.println("lruCallback");
    }


    private class CheckTimeThread implements Runnable{

        @Override
        public void run() {
            while (flag){
                try {
                    Node node = QUEUE.poll();
                    if (node == null){
                        continue ;
                    }
                    Long updateTime = node.getUpdateTime() ;

                    if ((updateTime - System.currentTimeMillis()) >= EXPIRE_TIME){
                        remove(node.key) ;
                    }
                } catch (Exception e) {
                    //LOGGER.error("InterruptedException");
                    System.out.println("Interruptedexception!!");
                }
            }
        }
    }

}