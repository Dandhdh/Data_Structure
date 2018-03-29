package Lists_Stacks_Queues;

/**
 * ������������
 * �ǻ����������һ��ʵ��
 * ��������ʹ��ArrayList��
 * ȡ����֮����һ������������¼ÿ��Ͱ��װ��������
 * 
 * ����˼�룺����ĳ����ֵ��֪���ж��ٸ�������С������֪����������λ��
 */
public class Sort_countingRadixSort {
	
	//�ַ����Ļ�������
	//Ĭ�������ַ������ַ�����ASCII�룬λ��Unicode�ַ�����ǰ256λ
	//�������ַ�����һ����������stringLenΪ�����ַ�������
	public static void countingRadixSort(String [] arr,int stringLen){
		
		final int BUCKETS = 256;
		
		int N = arr.length;
		String [] buffer = new String[N];
		
		String [] in = arr;
		String [] out = buffer;
		
		for(int pos = stringLen-1; pos>=0; pos--){
			
			int [] count = new int[BUCKETS+1];
			
			for(int i=0; i<N; i++){
				//count[k+1]��ʾͰk�е�Ԫ�ظ���
				count[in[i].charAt(pos)+1]++;
				System.err.println(pos);
				System.out.println(in[i]+" "+in[i].charAt(pos));
			}
			System.out.println("count1: ");
			for(int s = 96; s<124; s++ )
				System.out.print(count[s]+"  ");
			System.out.println();
			System.out.println();
			
			for(int b=1; b<=BUCKETS; b++)
				count[b]+=count[b-1];
			System.out.println("count3: ");
			for(int s = 96; s<124; s++ )
				System.out.print(count[s]+"  ");
			System.out.println();
			for(int i=0; i<N; i++){
				System.out.print( in[i].charAt(pos)+"  " );
				System.out.print((count[in[i].charAt(pos)]++) +"  ");
				//out[count[in[i].charAt(pos)]++] = in[i];
			}
			System.out.println("---");
			System.out.println();
			for(String s : out )
				System.out.print(s+"  ");
			
			System.out.println("----------------------------------");
			
			String [] tmp = in;
			in = out;
			out = tmp;
		}
		
		if(stringLen%2==1)
			for(int i=0; i<arr.length; i++)
				out[i] = in[i];
	}
	
	public static void main(String[] args) {
		String [] arr2 = {"asdf","asdf","sddd","sdda","sdsa","dfgg"};
		
		countingRadixSort(arr2,4);
		
		for(String s : arr2)
			System.out.print(s+"  ");
	
	}
	
}
