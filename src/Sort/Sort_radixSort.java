package Lists_Stacks_Queues;

import java.util.ArrayList;

//用一个ArrayList做桶
public class Sort_radixSort {

	//字符串的基数排序
	//默认所有字符串的字符都是ASCII码，位于Unicode字符集的前256位
	//且所有字符串都一样长，参数stringLen为单个字符串长度
	public static void radixSort_String(String[] arr, int stringLen ){
		
		final int BUCKETS = 256;
		ArrayList<String> [] buckets = new ArrayList[BUCKETS];
		
		for(int i = 0; i<BUCKETS; i++)
			buckets[i] = new ArrayList<>();
		
		for(int pos = stringLen-1 ; pos>=0; pos--){
			
			for(String s: arr)
				buckets[s.charAt(pos)].add(s);
			
			int idx = 0;
			for(ArrayList<String> thisBucket : buckets){
				for(String s : thisBucket)
					arr[idx++] = s;
				
				thisBucket.clear();
			}
		}
	}
	
	
	//正整数的基数排序
	public static void radixSort_int(Integer[] arr){
		
		ArrayList<Integer> [] buckets = new ArrayList[10];
		for(int i = 0; i<10; i++){
			buckets[i] = new ArrayList<>();
		}
		
		int maxPos = 10 ;      //最大数字位数
		
		for(int pos=1; pos<=maxPos; pos++){     //从数字个位开始
			for(int i=0; i<arr.length; i++){
				//得到aarr[i]的pos位数的桶号
				int index = GetBucketPos(arr[i],pos);
				//将数据分发到相应的桶
				buckets[index].add(arr[i]);
			}
			
			//将桶里的数据按桶顺序重新放回数组
			int idx = 0;   //
			for(ArrayList<Integer> thisBucket : buckets){
				for(Integer a : thisBucket){
					arr[idx++] = a;
				}
				thisBucket.clear();
			}
		}
	}

	//得到数据第pos位的桶号
	private static int GetBucketPos(int number, int pos) {
		int temp = 1;
		for(int i = 0 ; i < pos - 1 ; ++i)
	        temp *= 10;
	    return (number / temp) % 10;
	}


	public static void main(String[] args) {
		Integer [] arr = {12,213,123,111,12323,23,44,344,453454,123,11};
		String [] arr2 = {"asdf","asdf","sddd","sdda","sdsa","dfgg"};
		radixSort_int(arr);
		radixSort_String(arr2, 4);
		for(Integer s : arr)
			System.out.print(s+"  ");
		System.out.println();
		for(String s : arr2)
			System.out.print(s+"  ");
	}
	
}
