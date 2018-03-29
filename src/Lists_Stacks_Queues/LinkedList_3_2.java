package Lists_Stacks_Queues;


/*
 * 通过调整链（不是数据）来交换两个相邻的元素
 * 
 */
public class LinkedList_3_2<AnyType>{
	
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
	
	//单链表
	public static void swapWithNext_singleLinkedList(Node beforep){
		Node p,afterp;
		
		//分别使p，afterP知道对应的位置
		p = beforep.next;
		afterp = p.next;
		
		//交换
		p.next = afterp.next;
		afterp.next = p;
		beforep.next = afterp;
		
	}
	
	//双链表
	public static void swapWithNext_doubleLinkedList(Node beforep){
		Node p, afterp;
		
		p = beforep.next;
		beforep = p.prev;
		afterp = p.next;
		p = afterp.prev;
		
		
		p.next = afterp.next;
		p = p.next.prev;
		
		afterp = beforep.next;
		afterp.prev = beforep;
		
		afterp.next = p;
		p.prev = afterp;
		
		
	}
}
