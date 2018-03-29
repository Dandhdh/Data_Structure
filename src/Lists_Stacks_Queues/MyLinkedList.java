package Lists_Stacks_Queues;

import java.util.Iterator;

//LinkedList��ʵ��
/*
 * 1��MyLinkedList�౾���������˵�������Ĵ�С�Լ�һЩ����
 * 2��Node�࣬˽�е�Ƕ���ࡣһ���ڵ���������Լ�ǰһ���ڵ�����͵���һ���ڵ��������һЩ���췽��
 * 3��LinkedListIterator�࣬˽���࣬ʵ��iterator�ӿ�
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType>{
	
	private int theSize;
	/*
	 * modCount�����Դӹ������������������ĸı�Ĵ���
	 * ÿ�ζ�add��remove�ĵ��ö�������modCount
	 * 
	 * ���뷨���ڣ�
	 * ��һ����������������ʱ���������漯�ϵ�modCount
	 * ÿ�ζ�һ��������������next��remove���ĵ��ö����������ڵĵ�ǰmodCount����ڵ������ڴ洢��modCount
	 * ���ҵ�������������ƥ��ʱ�׳�һ���쳣
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
	
	//�ڽڵ�P֮ǰ����x
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
		
		//ͨ��if��ʹ��ִ������������
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
