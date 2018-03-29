package Lists_Stacks_Queues;


/**
 * Val�����ʣ�
 * ���������������ĸ߶�֮��ľ���ֵ������1
 * ���е�ÿ��������������������AVL��
 */
public class AvlTree<AnyType extends Comparable<? super AnyType>> {
	
	private AvlNode<AnyType> root;
	private static final int ALLOWED_IMBALANCE = 1;

	//VAL���Ľڵ�����
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
	
	//�߶ȼ��㷽��
	private int height(AvlNode<AnyType> t){
		return t == null? -1 : t.height;
	}
	
	//��AVL���Ĳ�������
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

	//��ӻ�ɾ���ڵ㶼��ƽ����
	private AvlNode<AnyType> balance(AvlNode<AnyType> t){
		if(t==null)
			return t;
		
		if((height(t.left)-height(t.right)) > ALLOWED_IMBALANCE){  //ʵ���ϣ���ֵΪ2
			if(height(t.left.left) >= height(t.left.right))   //��if��������,��˵������-�����������t������ӵ������������˲��룩��Ӧ���е���ת
				t = rotateWithLeftChild(t);
			else
				t = doubleWithLeftChild(t);
		}
		if((height(t.right)-height(t.left)) > ALLOWED_IMBALANCE){
			if(height(t.right.right) >= height(t.right.left))   //��-�����
				t = rotateWithRightChild(t);
			else
				t = doubleWithRightChild(t);
		}
		
		/**
		 * ��Ϊ�ݹ飬
		 * �ڲ���ڵ�󣬺�ڵ�һ��һ������Ϸ��أ���˼���߶�
		 */
		t.height = Math.max(height(t.left), height(t.right))+1;
		return t ; 
	}

	
	//�������-������ĵ���ת
	//���ص���ƽ��������ĸ�
	private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> t) {
		
		//����תǰ
//		t
//		 left��t1
//		      l��t2
//		         l��t4
//		      r��t3
//		 right��t5
		
		//��
//		t1
//		  l��t2
//		     r��t4
//		  r��t
//		     l��t3
//		     r��t5
		AvlNode<AnyType> t1 = t.left;
		t.left = t1.right;
		t1.right = t;
		
		//��������߶�
		t.height = Math.max(height(t.left), height(t.right))+1;
		t1.height = Math.max(height(t1.left), height(t1.right))+1;
		
		return t1;
	}
	
	
	//�������-������ĵ���ת
	private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> t) {
		
		AvlNode<AnyType> t1 = t.right;
		
		t.right = t1.left;
		t1.left = t ;
		
		//��������߶�
		t.height = Math.max(height(t.left), height(t.right))+1;
		t1.height = Math.max(height(t1.left), height(t1.right))+1;
		
		return t1;
	}
	
	//�������-�������˫��ת
	private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> t){
		/*
		 * �ȶ�t����ڵ������-�ҵĵ���ת
		 * �ٶ�t������-��ĵ���ת
		 */
		t.left = rotateWithRightChild(t.left);
		return rotateWithLeftChild(t);
	}
	
	//�������-�������˫��ת
	private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> t){
		/*
		 * �ȶ�t���ҽڵ������-��ĵ���ת
		 * �ٶ�t������-�ҵĵ���ת
     	 */
		t.right = rotateWithLeftChild(t.right);
		return rotateWithRightChild(t);
	}
	
	
	//Avl����ɾ������
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
		//�ݹ鷽��
		if(p == null){
			return null;
		}else if (p.left == null) {
			return p;
		}
		return findMin(p.left);
	}

	
	
}

