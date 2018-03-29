package Lists_Stacks_Queues;


/**
 * 归并排序（可看作分治的递归算法）
 * 合并两个已排序的表
 *
 *通过分治策略，将一个数组，通过划分下表left-center和center+1-right看作两个子列
 *再通过递归排序，然后进行归并排序
 */
public class Sort_mergesort<AnyType> {
	
	private static <AnyType extends Comparable<? super AnyType>>
	    void  mergeSort(AnyType [] a, AnyType [] tmpArray, int left, int right){
		
		if(left < right){
			int center = (left+right)/2;
			mergeSort(a, tmpArray, left, center);     //通过递归达到排序左子列的目的
			mergeSort(a, tmpArray, center+1, right);
			merge(a, tmpArray, left, center+1, right);  //再将排序好的左右子列进行合并排序
		}
	}

	public static <AnyType extends Comparable<? super AnyType>> void  mergeSort(AnyType [] a){
		
		AnyType[] tmpArray = (AnyType[]) new Comparable[a.length];
		
		mergeSort(a, tmpArray, 0 ,a.length-1);
		
	}
	
	/**
	 * 合并两个已排序好的子列
	 * @param a
	 * @param tmpArray
	 * @param leftPos   第一个子列的起始位置
	 * @param rightPos  第二个子列的起始位置
	 * @param rightEnd  子列最右边的坐标
	 */
	private static <AnyType extends Comparable<? super AnyType>>
	   void merge(AnyType[] a, AnyType [] tmpArray,
			   int leftPos, int rightPos, int rightEnd){
		
		int leftEnd = rightPos-1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos +1;
		
		while( leftPos<=leftEnd && rightPos<=rightEnd){
			if(a[leftPos].compareTo(a[rightPos]) <= 0 )
				tmpArray[tmpPos++]=a[leftPos++];
			else
				tmpArray[tmpPos++]=a[rightPos++];
		}
		
		while(leftPos<=leftEnd)
			tmpArray[tmpPos++] = a[leftPos++];
		
		while(rightPos<=rightEnd)
			tmpArray[tmpPos++] = a[rightPos++];
		
		for(int i = 0; i<numElements; i++,rightEnd--)
			a[rightEnd] = tmpArray[rightEnd];
	}
	
	public static void main(String[] args) {
		Integer[] a ={1,34,23,341,221,234,4545,324,3253,22,2};
		mergeSort(a);
		for(Integer aa : a)
			System.out.println(aa+" ");
		
	}
}
