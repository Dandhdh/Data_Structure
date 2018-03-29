package Lists_Stacks_Queues;

/*
 * ���赥����ʹ��һ��ͷ�ڵ�ʵ�֣�����β�ڵ㣬ֻ�������Ը�ͷ�ڵ�����ã�
 * ��ʵ�����£�
 * 1�����������С�ķ���
 * 2����ӡ����ķ���
 * 3������ֵx�Ƿ�������ķ���
 * 4�����ֵx��δ�����������ֵx��������ķ���
 * 5���������ֵx����ֵ�Ӹ�����ɾ���ķ���
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
	
	//ϰ��3_12
	//�������˳�򣬼��ڲ�������ʱ�������򼴿�
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
