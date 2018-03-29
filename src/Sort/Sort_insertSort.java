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
	
	//��������
    /*
     * ����������N-1���������
     * ����p=1��N-1�ˣ���������֤��λ��0��λ��p�ϵ�Ԫ��Ϊ������״̬
     * 
     * ��ͬ��������
     * ͨ����������Ԫ�ؽ���������κ��㷨ƽ������Ҫ0(N^2)ʱ��
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
	 * ϣ������ʹ��ϣ��������ϣ������
	 * ͨ���Ƚ����һ�������Ԫ��������
	 * ���˱Ƚ����õľ��������㷨�Ľ��ж���С
	 * ֱ��ֻ�Ƚ�����Ԫ�ص����һ������Ϊֹ
	 * 
	 * ���Ҳ�У�������������
	 * 
	 * ��������һ�����е�ѡ����ǣ�ϣ����Shell��ԭʼ�������У�N / 2��N / 4��...��1���ظ�����2��;
	 * ���ǲ�����
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
