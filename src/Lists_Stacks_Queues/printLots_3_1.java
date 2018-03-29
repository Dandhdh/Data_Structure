package Lists_Stacks_Queues;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * 习题3—1
 */
public class printLots_3_1 {
	
	/*
	 * 注意：P包含以升序的整数
	 */
	public static <AnyType> void printLots(List<AnyType> L, List<Integer> P){
		
		Iterator<AnyType> iterL = L.iterator();
		Iterator<Integer> iterP = P.iterator();
		
		AnyType itemL = null;
		Integer itemP = 0;
		int start = 0;
		
		while(iterL.hasNext() && iterP.hasNext()){
			itemP = iterP.next();
			System.out.println("Looking for position " + itemP);
			
			while(start<itemP && iterL.hasNext()){
				start++;
				itemL = iterL.next();
			}
			
			if(start==0)
				System.out.println(iterL.next());
			else {
				System.out.println(itemL);
			}
		}
		
	}
	
	public static void main(String[] args) {
		List<String> L = new ArrayList<>();
		List<Integer> P = new ArrayList<>();
		
		L.add("A0");
		L.add("A1");
		L.add("A2");
		L.add("A3");
		L.add("A4");
		L.add("A5");
		
		P.add(0);
		P.add(2);
		P.add(3);
		P.add(4);
		
		printLots(L, P);
	}
}
