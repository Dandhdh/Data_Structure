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
					System.out.println("����");
					return ;
				case -1:
					System.out.println("����");
					return ;
				case 0:
					System.out.println("����Ԫ��ȫ��һ��");
					return;
				}
			}
		}
		
		for(int i = 0; i<a.length-1; i++){
			if(!(a[i].compareTo(a[i+1])==result || a[i].compareTo(a[i+1])==0)){
				System.out.println("û������");
				return;
			}
		}
		if(result==1){
			System.out.println("����");
			return ;
		}else {
			System.out.println("����");
			return ;
		}
	}

	private static <AnyType> void print(AnyType[] a) {
		for(AnyType b : a){
			System.out.print(b+" ");
		}
	}
	
}