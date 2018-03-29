package Lists_Stacks_Queues;

public class Sort_insertSort{
	
	
    public static void main(String[] args) {
		
		Integer[] a = new Integer[1000000];
		
		for(int i=0; i<a.length; i++)
			a[i] = (1000000-i);
		
		
		Long time1 = System.currentTimeMillis();
		System.out.println(time1);
		shellSort(a);
		Long time2 = System.currentTimeMillis();
		System.out.println(time2-time1);
		
		
	}
	
	//插入排序
    /*
     * 插入排序由N-1趟排序组成
     * 对于p=1到N-1趟，插入排序保证从位置0到位置p上的元素为已排序状态
     * 
     * 如同插入排序
     * 通过交换相邻元素进行排序的任何算法平均都需要0(N^2)时间
     */
	public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType [] a){
		int j ;
		
		for( int p =1; p<a.length; p++){
			AnyType tmp = a[p];
			for(j=p; j>0 && tmp.compareTo(a[j-1])<0; j--){
				a[j] = a[j-1];
			}
			a[j] = tmp;
		}
	}
	
	
	/**
	 * 希尔排序（使用希尔增量的希尔排序）
	 * 通过比较相距一定间隔的元素来工作
	 * 各趟比较所用的距离随着算法的进行而减小
	 * 直到只比较相邻元素的最后一趟排序为止
	 * 
	 * 因此也叫：减缩增量排序
	 * 
	 * 增量序列一个流行的选择就是：希尔（Shell）原始步长序列：N / 2，N / 4，...，1（重复除以2）;
	 * 但是不够好
	 */
	public static <AnyType extends Comparable<? super AnyType>> void shellSort(AnyType [] a){
		int j ;
		
		for(int gap = a.length/2 ; gap>0; gap/=2)
			for(int i = gap; i<a.length; i++){
				
				AnyType tmp = a[i];
				for(j=i; j>=gap && tmp.compareTo(a[j-gap])<0; j-=gap)
					a[j] = a[j-gap];
				a[j] = tmp;
			}
	}
	
	

}
