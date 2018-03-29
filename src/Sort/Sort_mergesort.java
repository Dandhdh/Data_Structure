package Lists_Stacks_Queues;


/**
 * �鲢���򣨿ɿ������εĵݹ��㷨��
 * �ϲ�����������ı�
 *
 *ͨ�����β��ԣ���һ�����飬ͨ�������±�left-center��center+1-right������������
 *��ͨ���ݹ�����Ȼ����й鲢����
 */
public class Sort_mergesort<AnyType> {
	
	private static <AnyType extends Comparable<? super AnyType>>
	    void  mergeSort(AnyType [] a, AnyType [] tmpArray, int left, int right){
		
		if(left < right){
			int center = (left+right)/2;
			mergeSort(a, tmpArray, left, center);     //ͨ���ݹ�ﵽ���������е�Ŀ��
			mergeSort(a, tmpArray, center+1, right);
			merge(a, tmpArray, left, center+1, right);  //�ٽ�����õ��������н��кϲ�����
		}
	}

	public static <AnyType extends Comparable<? super AnyType>> void  mergeSort(AnyType [] a){
		
		AnyType[] tmpArray = (AnyType[]) new Comparable[a.length];
		
		mergeSort(a, tmpArray, 0 ,a.length-1);
		
	}
	
	/**
	 * �ϲ�����������õ�����
	 * @param a
	 * @param tmpArray
	 * @param leftPos   ��һ�����е���ʼλ��
	 * @param rightPos  �ڶ������е���ʼλ��
	 * @param rightEnd  �������ұߵ�����
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
