package Lists_Stacks_Queues;

import java.util.Comparator;

public class BinarySearchTree_myCompare<AnyType>{
	
	private BinaryNode<AnyType> root;
	
	//注意
	private Comparator<? super AnyType> cmp;
	
	//自己写比较函数
	private int myCpmpare(AnyType x1 , AnyType x2){
		if(cmp!=null)
			return cmp.compare(x1, x2);
		else
			return ((Comparable)x1).compareTo(x2);
	}
	
	private static class BinaryNode<AnyType>{
		private AnyType element;
		private BinaryNode<AnyType> left;
		private BinaryNode<AnyType> right;
		
		BinaryNode(AnyType element){
			this(element,null,null);
		}

		public BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
			element = theElement;
			left = lt;
			right = rt;
		}
	}

}
