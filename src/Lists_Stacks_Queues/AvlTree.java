package Lists_Stacks_Queues;


/**
 * Val树性质：
 * 左子树和右子树的高度之差的绝对值不超过1
 * 树中的每个左子树和右子树都是AVL树
 */
public class AvlTree<AnyType extends Comparable<? super AnyType>> {
	
	private AvlNode<AnyType> root;
	private static final int ALLOWED_IMBALANCE = 1;

	//VAL树的节点声明
	private static class AvlNode<AnyType>{
		AnyType element;
		AvlNode<AnyType> left;
		AvlNode<AnyType> right;
		int height;
		
		AvlNode(AnyType x) {
			this(x,null,null);
		}
		
		public AvlNode(AnyType x, AvlNode<AnyType> lt , AvlNode<AnyType> rt) {
			element = x;
			left = lt;
			right = rt;
		}
	}
	
	//高度计算方法
	private int height(AvlNode<AnyType> t){
		return t == null? -1 : t.height;
	}
	
	//向AVL树的插入例程
	private AvlNode<AnyType> insert(AnyType x, AvlNode<AnyType> t){
		
		if(t==null)
			return new AvlNode<AnyType>(x);
		
		int compareResult = x.compareTo(t.element);
		if(compareResult<0){
			t.left = insert(x, t.left);
		}else if (compareResult>0) {
			t.right = insert(x, t.right);
		}else {
			//do nothing
		}
		return balance(t);
	}

	//添加或删除节点都，平衡树
	private AvlNode<AnyType> balance(AvlNode<AnyType> t){
		if(t==null)
			return t;
		
		if((height(t.left)-height(t.right)) > ALLOWED_IMBALANCE){  //实际上，差值为2
			if(height(t.left.left) >= height(t.left.right))   //若if条件成立,则说明是左-左情况（即在t的左儿子的左子树进行了插入），应进行单旋转
				t = rotateWithLeftChild(t);
			else
				t = doubleWithLeftChild(t);
		}
		if((height(t.right)-height(t.left)) > ALLOWED_IMBALANCE){
			if(height(t.right.right) >= height(t.right.left))   //右-右情况
				t = rotateWithRightChild(t);
			else
				t = doubleWithRightChild(t);
		}
		
		/**
		 * 因为递归，
		 * 在插入节点后，后节点一层一层地往上返回，借此计算高度
		 */
		t.height = Math.max(height(t.left), height(t.right))+1;
		return t ; 
	}

	
	//解决：左-左情况的单旋转
	//返回的是平衡后子树的根
	private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> t) {
		
		//单旋转前
//		t
//		 left：t1
//		      l：t2
//		         l：t4
//		      r：t3
//		 right：t5
		
		//后
//		t1
//		  l：t2
//		     r：t4
//		  r：t
//		     l：t3
//		     r：t5
		AvlNode<AnyType> t1 = t.left;
		t.left = t1.right;
		t1.right = t;
		
		//重新求过高度
		t.height = Math.max(height(t.left), height(t.right))+1;
		t1.height = Math.max(height(t1.left), height(t1.right))+1;
		
		return t1;
	}
	
	
	//解决：右-右情况的单旋转
	private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> t) {
		
		AvlNode<AnyType> t1 = t.right;
		
		t.right = t1.left;
		t1.left = t ;
		
		//重新求过高度
		t.height = Math.max(height(t.left), height(t.right))+1;
		t1.height = Math.max(height(t1.left), height(t1.right))+1;
		
		return t1;
	}
	
	//解决：左-右情况的双旋转
	private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> t){
		/*
		 * 先对t的左节点进行右-右的单旋转
		 * 再对t进行左-左的单旋转
		 */
		t.left = rotateWithRightChild(t.left);
		return rotateWithLeftChild(t);
	}
	
	//解决：右-左情况的双旋转
	private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> t){
		/*
		 * 先对t的右节点进行左-左的单旋转
		 * 再对t进行右-右的单旋转
     	 */
		t.right = rotateWithLeftChild(t.right);
		return rotateWithRightChild(t);
	}
	
	
	//Avl树的删除例程
	private AvlNode<AnyType> reomve(AnyType x, AvlNode<AnyType> t){
		if(t==null)
			return t;
		
		int compareResult = x.compareTo(t.element);
		
		if(compareResult<0)
			t.left = reomve(x, t.left);
		else if (compareResult>0) {
			t.right = reomve(x, t.right);
		}else if (t.left != null && t.right != null) {
			t.element = findMin(t.right).element;
			t.right = reomve(t.element, t.right);
		}else {
			t = (t.left != null)?t.left:t.right;
		}
		return balance(t);
	}
	
	private AvlNode<AnyType> findMin(AvlNode<AnyType> p) {
		//递归方法
		if(p == null){
			return null;
		}else if (p.left == null) {
			return p;
		}
		return findMin(p.left);
	}

	
	
}

