package Lists_Stacks_Queues;

/**
 * 计数基数排序
 * 是基数排序的另一种实现
 * 它避免了使用ArrayList，
 * 取而代之的是一个计数器，记录每个桶会装多少数据
 * 
 * 核心思想：对于某个数值，知道有多少个数比它小，即可知道它排序后的位置
 */
public class Sort_countingRadixSort {
	
	//字符串的基数排序
	//默认所有字符串的字符都是ASCII码，位于Unicode字符集的前256位
	//且所有字符串都一样长，参数stringLen为单个字符串长度
	public static void countingRadixSort(String [] arr,int stringLen){
		
		final int BUCKETS = 256;
		
		int N = arr.length;
		String [] buffer = new String[N];
		
		String [] in = arr;
		String [] out = buffer;
		
		for(int pos = stringLen-1; pos>=0; pos--){
			
			int [] count = new int[BUCKETS+1];
			
			for(int i=0; i<N; i++){
				//count[k+1]表示桶k中的元素个数
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
