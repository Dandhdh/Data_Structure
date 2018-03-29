package Lists_Stacks_Queues;

/*
 * 假设单链表使用一个头节点实现，但五尾节点，只保留它对该头节点的引用：
 * 并实现以下：
 * 1、返回链表大小的方法
 * 2、打印链表的方法
 * 3、测试值x是否含于链表的方法
 * 4、如果值x尚未含于链表，添加值x到该链表的方法
 * 5、如果含有值x，将值从该链表删除的方法
 */
public class SingleList {
	
	private Node<Object> head;
	private int theSize;
	
	private class Node<Object>{
		Object data;
		Node next;
		
		Node(){
			this(null,null);
		}
		Node(Object d){
			this(d,null);
		}
		Node(Object d, Node n){
			data = d;
			next = n;
		}
	}
	
	public SingleList() {
		init();
	}
	
	void init(){
		theSize = 0;
		head = new Node<Object>();
		head.next = null;
	}
	
	boolean add(Object d){
		if(contains(d)){
			return false;
		}
		Node<Object> p = new Node<>();
		p.next = head.next;
		head.next = p;
		theSize++;
		
		return true;
	}
	
	//习题3_12
	//以排序的顺序，即在插入数据时进行排序即可
//	boolean add(Comparable x){
//		if(contains(x)){
//			return false;
//		}
//		else{
//			Node<Comparable> p = head.next;
//			Node<Comparable> trailer = head;
//			
//			while( p!=null && p.data.compareTo(x)<0){
//				trailer = p;
//				p = p.next;
//			}
//			
//			trailer.next = new Node<Comparable>(x);
//			trailer.next.next = p;
//			theSize++;
//		}
//		return true;
//	}
	
	
	boolean remove(Object x){
		if(!contains(x)){
			return false;
		}else{
			Node<Object> p = head.next;
			Node<Object> trailer = head;
			
			while (!p.data.equals(x)){
				trailer = p;
				p = p.next;
			}
			trailer.next = p.next;
			theSize--;
		}
		return true;
	}
	
	int size(){
		return theSize;
	}
	
	boolean contains(Object x){
		Node<Object> p = head.next;
		while(p!=null){
			if(x.equals(p.data)){
				return true;
			}else {
				p = p.next;
			}
		}
		return false;
	}
	
	void print(){
		Node<Object> p = head.next;
		while (p != null){
			System.out.print(p.data + " ");
		    p = p.next;
		}
		System.out.println();
	}
}
