package Lists_Stacks_Queues;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>{
	
	private BinaryNode<AnyType> root;
	
	private static class BinaryNode<AnyType>{
		
		AnyType element;
		BinaryNode<AnyType> left;
		BinaryNode<AnyType> right;
		
		BinaryNode(AnyType element){
			this(element,null,null);
		}
		
		BinaryNode(AnyType theElement, BinaryNode lt, BinaryNode rt) {
			element = theElement;
			left = lt;
			right = rt;
		}
	}
	
	BinarySearchTree(){
		root = null;
	}
	
	public void makeEmpty(){
		root = null;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public boolean contains(AnyType x){
		return contains(x, root);
	}

	private boolean contains(AnyType x, BinaryNode<AnyType> p) {
		
		if(p == null)
			return false;
		
		int compareResult = x.compareTo(p.element);
		if(compareResult < 0){
			p = p.left;
			return contains(x,p);
		}else if(compareResult > 0) {
			p = p.right;
			return contains(x,p);
		}else {
			return true;
		}
	}
	
	public AnyType findMin() throws Exception{
		if(isEmpty())
			throw new Exception();
		return findMin(root).element;
	}
	
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> p) {
		
		//�ݹ鷽��
//		if(p == null){
//			return null;
//		}else if (p.left == null) {
//			return p;
//		}
//		return findMin(p.left);
		
		//�ǵݹ鷽��
		if( p != null){
			while(p.left != null){
				p = p.left;
			}
			return p;
		}
		return null;
	}

	public AnyType findMax() throws Exception{
		if(isEmpty())
			throw new Exception();
		return findMax(root).element;
	}

	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> p) {
		if(p == null){
			return null;
		}else if (p.right == null) {
			return p;
		}
		return findMin(p.right);
	}
	
	public BinaryNode<AnyType> insert(AnyType x){
		return insert(x, root);
	}

	private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> p) {
		if(p == null)
			return new BinaryNode<AnyType>(x);
		
		int compareResult = x.compareTo(p.element);
		
	    if(compareResult < 0 ){
	    	p.left = insert(x, p.left);
	    }else if (compareResult > 0) {
			p.right = insert(x, p.right);
		}else {
			//��ȵ�ʱ�����κδ���
		}
	    return p;
	}
	
	public void remove(AnyType x){
		root = remove(x, root);
	}

	private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> p) {
		
		if(p == null)
			return p;
		
		int compareResult = x.compareTo(p.element);
		
		if(compareResult < 0){
			p.left = remove(x, p.left);
		}else if (compareResult > 0) {
			p.right = remove(x, p.right);
		}else if ( p.left != null && p.right != null) {   //ɾ���ڵ�����������
			p.element = findMin(p.right).element;
			p.right = remove(p.element, p.right);      
		}else{
			p = (p.left != null)?p.left:p.right;          //ɾ���ڵ�ֻ��һ������
		}
		return p;
	}
	
	//��ӡ��
	private void  printTree(){
		if(isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}

	//�������
	private void printTree(BinaryNode<AnyType> p) {
		if(p !=null){
			printTree(p.left);
			System.out.println(p.element);
			printTree(p.right);
		}
	}

	
	//ʹ�ú�������������ĸ߶�
	private int height(BinaryNode<AnyType> t){
		if( t == null )
			return -1;
		else
			return 1+Math.max(height(t.left), height(t.right));
	}
	
	
	
	
}
