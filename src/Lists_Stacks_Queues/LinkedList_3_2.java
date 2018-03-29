package Lists_Stacks_Queues;


/*
 * ͨ�����������������ݣ��������������ڵ�Ԫ��
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
	
	//������
	public static void swapWithNext_singleLinkedList(Node beforep){
		Node p,afterp;
		
		//�ֱ�ʹp��afterP֪����Ӧ��λ��
		p = beforep.next;
		afterp = p.next;
		
		//����
		p.next = afterp.next;
		afterp.next = p;
		beforep.next = afterp;
		
	}
	
	//˫����
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
