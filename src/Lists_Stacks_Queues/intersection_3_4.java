package Lists_Stacks_Queues;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 对给定的已排序的L1和L2
 * 求交集
 */
public class intersection_3_4 {
	
	public static <AnyType extends Comparable<? super AnyType>> 
	         void intersection(List<AnyType> L1, List<AnyType> L2, List<AnyType> Intersect){
		
		ListIterator<AnyType> iterL1 = L1.listIterator();
		ListIterator<AnyType> iterL2 = L2.listIterator();
		
		AnyType itemL1=null, itemL2=null;
		
		//取表的第一项
		if(iterL1.hasNext() && iterL2.hasNext()){
			itemL1 = iterL1.next();
			itemL2 = iterL2.next();
		}
		
		while(itemL1 != null && itemL2 !=null){
			int compareResult = itemL1.compareTo(itemL2);
			
			if(compareResult == 0){
				Intersect.add(itemL1);
				itemL1 = iterL1.hasNext()?iterL1.next():null;
				itemL2 = iterL2.hasNext()?iterL2.next():null;
			}else if (compareResult<0) {
				itemL1 = iterL1.hasNext()?iterL1.next():null;
			}else{
				itemL2 = iterL2.hasNext()?iterL2.next():null;
			}
		}
	}
	
	
	//验证
	public static void main(String[] args) {
		List<Integer> L1 = new ArrayList<Integer>();
		List<Integer> L2 = new ArrayList<Integer>();
		List<Integer> L3 = new ArrayList<Integer>();
		
		L1.add(1);
		L1.add(3);
		L1.add(5);
		L1.add(7);
		L1.add(9);
		L1.add(10);
		
		L2.add(1);
		L2.add(3);
		L2.add(10);
		L2.add(112);
		
		intersection(L1, L2, L3);
		
		Iterator<Integer> iter = L3.iterator();
		while(iter.hasNext()){
			System.out.print(iter.next()+"  ");
		}
		
	}

}
