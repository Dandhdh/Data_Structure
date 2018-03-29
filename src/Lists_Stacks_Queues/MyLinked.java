package Lists_Stacks_Queues;


public class MyLinked {
	
	private static class Node<AnyType>{
		public Node(AnyType d, Node<AnyType> n){
			data = d;
			next = n;
		}
		
		public Node(){
		}
		
		public AnyType data;
		public Node<AnyType> next;
	}
	
	public static void process(Node node){
		Node n = node.next;
		while(n.next!=null || n!=node){
			n = n.next;
		}
		if(n.next==null){
			onCircleError();
			return ;
		}
	}

	private static void onCircleError() {
	}
	
	public static void demo(Node head){
		Node n = head.next;
		process(head);
		onComplete();
		return;
	}

	private static void onComplete() {
	}
}
