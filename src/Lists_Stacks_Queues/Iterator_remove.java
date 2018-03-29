package Lists_Stacks_Queues;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Iterator_remove {
	
	  public static void main(String[] args) {
		    List<Integer> lst = new ArrayList<Integer>();  
	        //Iterator<String> iterator = strList.iterator();
	        for (int i = 0; i < 10; i++)  
	        {  
	        	lst.add(i);  
	        }  
	        Iterator<Integer> itr = lst.iterator();
	        
	        while (itr.hasNext())  
	        {   
	        	if(itr.next()%2==0){
	        		itr.remove();
	        	}
	        }
	        
	        
	        //±¨´í
//	        for(Integer b : lst){
//	        	if(b%2==0)
//	        		lst.remove(b);
//	        }
	        
	        for(Integer b : lst){
	        	System.out.println(b);
	        }
	    }  

}
