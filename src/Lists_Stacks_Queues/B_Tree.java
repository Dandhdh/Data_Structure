package Lists_Stacks_Queues;

import java.util.Map.Entry;

/**
 *1. ÿ���������ӵ��m��������
  2. ���������ӵ��������������������������£���
  3. ���˸�������⣬����ÿ����֧�������ӵ�� m/2 ��������
  4. ���е�Ҷ��㶼��ͬһ���ϣ�
  5. �� k �������ķ�֧�������� k-1 ���ؼ��룬�ؼ��밴�յ�������������У�
  6. �ؼ���������Ҫ����ceil(m/2)-1 <= n <= m-1��
 */
public class B_Tree<Key extends Comparable<Key>, Value>{
	
	 //M��
     private static final int M = 4;
	
     private Node root;
     private int height;
     private int n;           // number of key-value pairs in the B-tree   
     
     /**
      * Ҷ�ӽ��Ϊ�ⲿ��internal_node����㣬�������ݣ���key��value��
      * 
      * �������Ϊ�ڲ���external_node����㣬������������key��next
      * �ڲ����Ĺؼ���key��K[1], K[2], ��, K[M-1]����K[i] < K[i+1]��
      * �ڲ�����ָ��next��P[1], P[2], ��, P[M]��
      * ����P[1]ָ��ؼ���С��K[1]��������P[M]ָ��ؼ��ִ���K[M-1]������������P[i]ָ��ؼ�������(K[i-1], K[i])��������
      */
     private static final class Node{
    	 private int keyNum;       //ÿ���ڵ�����Ĺؼ�����
    	 private Entry[] children = new Entry[M];
    	 
    	 private Node(int k){
    		 keyNum = k;
    	 }
     }
     
     private static class Entry{
    	 private Comparable key;
    	 private Object val;
    	 private Node next;
    	 
    	 public Entry(Comparable key, Object val, Node next){
    		 this.key = key;
    		 this.val = val;
    		 this.next = next;
    	 }
     }
     
     public B_Tree(){
    	 root = new Node(0);
     }
     
     public boolean isEmpty(){
    	 return size()==0;
     }
     
     public int size(){
    	 return n;
     }
     
     public int height(){
    	 return height;
     }
     
     public Value get(Key key){
    	 if(key == null){
    		 throw new NullPointerException("key must not be null");
    	 }
    	 return search(root,key,height);
     }

	private Value search(Node x, Key key, int ht) {
		Entry[] children = x.children;
		
		//external node����ײ�Ҷ�ӽڵ㣬����
		if(ht == 0){
			for(int j = 0; j<x.keyNum; j++){
				if(equal(key,children[j].key))
					return (Value)children[j].val;
			}
		}
		//internal node�ݹ����next��ַ
		else{
			for(int j = 0; j<x.keyNum; j++){
				
			}
		}
		return null;
	}

	private boolean equal(Key key, Comparable key2) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
