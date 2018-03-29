package Lists_Stacks_Queues;

public class Algorithm1 {

    public static void main(String[] args) {
       Integer [] a = {1,1,1,1,1,1,0};
       ifSort(a);
    }

	private static <AnyType extends Comparable<? super AnyType>>
	    void ifSort(AnyType[] a) {
		
		int result = 0;
		int j = 0;
		
		print(a);
		System.out.println();
		
		while (result==0) {
			result = a[0].compareTo(a[j++]);
			if(j==a.length-2){
				result = a[0].compareTo(a[++j]);
				switch(result){
				case 1:
					System.out.println("降序");
					return ;
				case -1:
					System.out.println("升序");
					return ;
				case 0:
					System.out.println("数组元素全部一样");
					return;
				}
			}
		}
		
		for(int i = 0; i<a.length-1; i++){
			if(!(a[i].compareTo(a[i+1])==result || a[i].compareTo(a[i+1])==0)){
				System.out.println("没有排序");
				return;
			}
		}
		if(result==1){
			System.out.println("降序");
			return ;
		}else {
			System.out.println("升序");
			return ;
		}
	}

	private static <AnyType> void print(AnyType[] a) {
		for(AnyType b : a){
			System.out.print(b+" ");
		}
	}
	
}