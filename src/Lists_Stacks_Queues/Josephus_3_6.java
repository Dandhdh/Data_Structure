package Lists_Stacks_Queues;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

//Josephus问题求解
/*
 * 编写一个程序解决M和N在一般值下的Josephus问题，使程序效率尽可能高，确保清楚各个单元
 * 程序的运行时间
 */
public class Josephus_3_6 {

	public static void Josephus(int m, int n){
		
		//步数step，剩余人数numLeft
		int i, j, step, numLeft;
		
		ArrayList<Integer> L = new ArrayList<Integer>();
		for (i=1; i<=n; i++)
			L.add(i);
		
		ListIterator<Integer> iter = L.listIterator();
		Integer item=0;
		
		for(Integer it : L){
			System.out.print(it+" ");
		}
		System.out.println();
		
	   numLeft = n;
	   
	   Long time1 = System.currentTimeMillis();
	   
	   for(i=0; i<n; i++){
		   step = m % numLeft;
		   if(iter.hasNext()){
			   item = iter.next();
		   }
		   for(j = 0; j<step; j++){
			   if(!iter.hasNext())
				   iter = L.listIterator();
			   item = iter.next();
		   }
			
		   System.out.println("remove："+item+" ");
		   iter.remove();
		   if(!iter.hasNext())
			   iter = L.listIterator();
		   for (Integer x:L)
				System.out.print(x + " ");
		   System.out.println();
		   numLeft--;
	   }
	   
	   System.out.println();
	   
	   Long time2 = System.currentTimeMillis();
	   System.out.println(time2-time1);
	}
	
	public static void main(String[] args) {
		Josephus(722, 3000);
	}
	
}
