package Lists_Stacks_Queues;

import java.util.Iterator;

//LinkedList的实现
/*
 * 1、MyLinkedList类本身，包含两端的链、表的大小以及一些方法
 * 2、Node类，私有的嵌套类。一个节点包含数据以及前一个节点的链和到下一个节点的链，即一些构造方法
 * 3、LinkedListIterator类，私有类，实现iterator接口
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType>{
	
	private int theSize;
	/*
	 * modCount代表自从构造以来对链表所做的改变的次数
	 * 每次对add或remove的调用都将更新modCount
	 * 
	 * 其想法在于：
	 * 当一个迭代器被建立的时候，他将储存集合的modCount
	 * 每次对一个迭代器方法（next或remove）的调用都将该链表内的当前modCount检测在迭代器内存储的modCount
	 * 并且当这两个计数不匹配时抛出一个异常
	 */
	private int modCount = 0;
	private Node<AnyType> beginMarker;
	private Node<AnyType> endMarker;
	
	private static class Node<AnyType>{
		public Node(AnyType d, Node<AnyType> p, Node<AnyType> n){
			data = d;
			prev = p;
			next = n;
		}
		
		public AnyType data;
		public Node<AnyType> prev;
		public Node<AnyType> next;
	}
	
	public MyLinkedList(){
		doClear();
	}
	
	public void clear(){
		doClear();
	}
	private void doClear(){
		beginMarker = new Node<AnyType>(null,null,null);
		endMarker = new Node<AnyType>(null,beginMarker,null);
		beginMarker.next = endMarker;
		
		theSize = 0;
		modCount++;
	}
	
	public int size(){
		return theSize;
	}
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public boolean add(AnyType x){
		add(size(),x);
		return true;
	}
	public void add(int idx, AnyType x){
		addBefore(getNode(idx, 0, size()), x);
	}
	public AnyType get(int idx){
		return getNode(idx).data;
	}
	public AnyType set(int idx, AnyType newVal){
		Node<AnyType> p = getNode(idx);
		AnyType oldVal = p.data;
		p.data = newVal;
		return oldVal;
	}
	public AnyType remove(int idx){
		return remove(getNode(idx));
	}
	
	//在节点P之前插入x
	private void addBefore(Node<AnyType> p, AnyType x){
		Node<AnyType> newNode = new Node<AnyType>(x, p.prev,p );
		newNode.prev.next = newNode;
		p.prev = newNode;
		theSize++;
		modCount++;
	}
	
	private AnyType remove(Node<AnyType> p){
		p.next.prev = p.prev;
		p.prev.next = p.next;
		theSize--;
		modCount--;
		
		return p.data;
	}
	
	private Node<AnyType> getNode(int idx){
		return getNode(idx, 0, size()-1);
	}
	
	private Node<AnyType> getNode(int idx, int lower, int upper){
		Node<AnyType> p;
		
		if(idx<lower || idx>upper)
			throw new IndexOutOfBoundsException();
		
		//通过if，使用执行少数的搜索
		if(idx<size()/2){
			p = beginMarker.next;
			for(int i = 0; i<idx; i++)
				p = p.next;
		}else {
			p=endMarker;
			for(int i = size(); i > idx; i--)
				p = p.prev;
		}
		
		return p;
	}
	
	public java.util.Iterator<AnyType> iterator(){
		return new LinkedListIterator();
	}
	private class LinkedListIterator implements java.util.Iterator<AnyType>{
		
		private Node<AnyType> currnet = beginMarker.next;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;
		
		public boolean hasNext(){
			return currnet != endMarker;
		}
		
		public AnyType next(){
			if(modCount != expectedModCount)
				throw new java.util.ConcurrentModificationException();
			if(hasNext())
				throw new java.util.NoSuchElementException();
			
			AnyType nextItem = currnet.data;
			currnet = currnet.next;
			okToRemove = true;
			return nextItem;
		}
		
		public void remove(){
			if(modCount != expectedModCount )
				throw new java.util.ConcurrentModificationException();
			if(!okToRemove)
				throw new IllegalStateException();
			
			MyLinkedList.this.remove(currnet.prev);
			expectedModCount++;
			okToRemove = false;
		}
	}
	
}
