package Lists_Stacks_Queues;

import java.util.Map.Entry;

/**
 *1. 每个结点至多拥有m棵子树；
  2. 根结点至少拥有两颗子树（存在子树的情况下）；
  3. 除了根结点以外，其余每个分支结点至少拥有 m/2 棵子树；
  4. 所有的叶结点都在同一层上；
  5. 有 k 棵子树的分支结点则存在 k-1 个关键码，关键码按照递增次序进行排列；
  6. 关键字数量需要满足ceil(m/2)-1 <= n <= m-1；
 */
public class B_Tree<Key extends Comparable<Key>, Value>{
	
	 //M阶
     private static final int M = 4;
	
     private Node root;
     private int height;
     private int n;           // number of key-value pairs in the B-tree   
     
     /**
      * 叶子结点为外部（internal_node）结点，保存内容，即key和value。
      * 
      * 其他结点为内部（external_node）结点，保存索引，即key和next
      * 内部结点的关键字key：K[1], K[2], …, K[M-1]；且K[i] < K[i+1]；
      * 内部结点的指针next：P[1], P[2], …, P[M]；
      * 其中P[1]指向关键字小于K[1]的子树，P[M]指向关键字大于K[M-1]的子树，其它P[i]指向关键字属于(K[i-1], K[i])的子树；
      */
     private static final class Node{
    	 private int keyNum;       //每个节点包含的关键字数
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
		
		//external node到最底层叶子节点，遍历
		if(ht == 0){
			for(int j = 0; j<x.keyNum; j++){
				if(equal(key,children[j].key))
					return (Value)children[j].val;
			}
		}
		//internal node递归查找next地址
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
