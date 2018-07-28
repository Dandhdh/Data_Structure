package Sort;

public class Sort_quickSelect {
	
	private static final int CUTOFF = 20;
	
	/**
	 * 
	 * @param a  选择数组
	 * @param k  需要返回第几小的元素
	 * @return
	 */
	public static <AnyType extends Comparable<? super AnyType>>
	   AnyType quickselect(AnyType [] a , int k){
		quickselect(a, 0, a.length, k);
		return a[k-1];
	}

	private static <AnyType extends Comparable<? super AnyType>>
	    void quickselect(AnyType[] a, int left, int right, int k) {
		
		if((left+CUTOFF) <= right){
			
			AnyType piovt = median3(a,left,right);
			
			int i = left;
			int j = right-1;
			
			for(;;){
				while(a[++i].compareTo(piovt)<0){}
				while(a[--i].compareTo(piovt)>0){}
				if(i<j)
					swapReferences(a, i, j);
				else {
					break;
				}
			}
			
			swapReferences(a, i, right-1);
			
			/**
			 * 如果k<=i，那么第k个最小元必然在a[left]-a[i-1]中
			 * 如果k>i+1,那么第k个最小元必然在a[i+2]-a[right]中
			 * 如果k=i，那么第k个最小元，就是a[i]
			 */
			if(k<=i)
				quickselect(a, left,i-1,k);
			else if(k>i+1)
				quickselect(a, i+1, right, k);
			
		}else{
			insertionSort(a,left,right);
		}
		
	}

	private static <AnyType extends Comparable<? super AnyType>> 
	    AnyType median3(AnyType[] a, int left, int right) {
		
		int center = (left+right)/2;
		
		if(a[center].compareTo(a[left])<0)
			swapReferences(a,left,center);
		return null;
	}

	private static <AnyType> void swapReferences(AnyType[] a, int k1, int k2) {
        AnyType tmp = a[k1];
        a[k1] = a[k2];
        a[k2] = tmp;
	}

	private static <AnyType extends Comparable<? super AnyType>> 
	    void insertionSort(AnyType[] a, int left, int right) {
		
		int j ;
		
		for(int p = 1 ; p<right; p++){
			AnyType tmp = a[p];
			for(j = p ; j>0 && tmp.compareTo(a[j-1])<0;j--){
				a[j] = a[j-1];
			}
			a[j] = tmp;
		}
		
	}

	public static void main(String[] args) {
		Integer[] a ={1,34,23,341,221,234,4545,324,3253,22,2};
		System.out.println(quickselect(a, 7));
	}
}
