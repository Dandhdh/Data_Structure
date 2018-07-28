package Sort;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.Any;

public class Sort_quicksort2<AnyType>{
	
	//定义小数组的元素个数
	private static final int CUTOFF = 20;
	
	/**
	 * 快速排序
	 */
	public static <AnyType extends Comparable<? super AnyType>>
	        void quickSort(AnyType [] a){
		quicksort(a,0,a.length-1);
	}
	
	/**
	 * 执行三数中值分割法
	 * 即随机选取三个数，选择其中的中值作为枢纽元
	 */
	private static <AnyType extends Comparable<? super AnyType>>
	        AnyType median3(AnyType [] a, int left, int right){
		
		
		//这里分别选取left、center、right三个
		//并从小到大排序
		int center = (left+right)/2;
		if(a[center].compareTo(a[left])<0)
			swapReferences(a,left,center);
		if(a[right].compareTo(a[left])<0)
			swapReferences(a, left, right);
		if(a[right].compareTo(a[center])<0)
			swapReferences(a, center, right);
		
		//此时选取center作为枢纽元
		//并换在最后一个
		swapReferences(a, center, right-1);
		return a[right-1];
		
	}

	private static <AnyType extends Comparable<? super AnyType>>
	        void swapReferences(AnyType [] a, int pos1, int pos2) {
		AnyType tmp = a[pos1];
		a[pos1] = a[pos2];
		a[pos2] = tmp;
	}

	private static <AnyType extends Comparable<? super AnyType>>
	        void quicksort(AnyType[] a, int left, int right) {
		
		if(left+CUTOFF <= right){
			
			AnyType pivot = median3(a, left, right);
			int i = left;
			int j = right-1;
			
			for(;;){
				while(a[++i].compareTo(pivot)<0){}
				while(a[--j].compareTo(pivot)>0){}
				//还有缺陷，当a[i] = a[j] = 枢纽元时，会陷入无限循环
				if(i<j)
					swapReferences(a, i, j);
				else
					break;
			}
			
			swapReferences(a, i, right-1);
			
			quicksort(a, left, i-1);
			quicksort(a, i+1, right);
			
		}else{
			insertionSort(a,0,a.length);
		}
		
	}

	//插入排序
	private static <AnyType extends Comparable<? super AnyType>> 
	     void insertionSort(AnyType[] a, int left, int right) {
		
		int j ;
		
		for(int p=1; p <= right ;p++){
			AnyType tmp = a[p];
			for( j = p ; j>0 && tmp.compareTo(a[j-1])<0; j--){
				a[j] = a[j-1];
			}
			a[j] = tmp; 
		}
	}
}
