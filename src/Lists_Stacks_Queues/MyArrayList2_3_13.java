package Lists_Stacks_Queues;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class MyArrayList2_3_13<AnyType> implements Iterable<AnyType>{
	
	/**
	 * 习题3——13
	 * 添加ListIterator对MyArrayList的支持
	 * 注意：
	 * 编写一个ListIterator方法返回新构造的ListIterator
	 * 且现存迭代器方法可以返回一个新构造的ListIterator而不是Iterator
	 */
	
	//编写一个ListIterator方法返回新构造的ListIterator
	public java.util.ListIterator<AnyType> ListIterator(){
		return new ArrayListIterator();
	}
	
	//现存迭代器方法可以返回一个新构造的ListIterator而不是Iterator
	public java.util.Iterator<AnyType> iterator(){
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements java.util.ListIterator<AnyType>{

		private int currnet = 0;
		boolean backwards = false;
		
		@Override
		public boolean hasNext() {
			return currnet<size();
		}

		@Override
		public AnyType next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			backwards = false;
			return theItems[currnet++];
		}

		@Override
		public boolean hasPrevious() {
			return currnet>0;
		}

		@Override
		public AnyType previous() {
			if(!hasPrevious())
				throw new NoSuchElementException();
			backwards = true;
			return theItems[--currnet];
		}

		@Override
		public int nextIndex() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public void remove() {
			if (backwards)
				MyArrayList2_3_13.this.remove(currnet--);   //previous时的remove
			else 
				MyArrayList2_3_13.this.remove(--currnet);   //next时的reomve
		}

		@Override
		public void set(AnyType e) {
			MyArrayList2_3_13.this.set(currnet, e);
		}

		@Override
		public void add(AnyType e) {
			MyArrayList2_3_13.this.add(currnet++,e);
		}
	}
	
	
	
	private static final int DEFAULT_CAPACITY = 10;
	
	private int theSize;
	private AnyType [] theItems;
	
    public MyArrayList2_3_13() {
    	doClear();
	}
    
    public void clear(){
    	doClear();
    }
    
    private void doClear(){
    	theSize = 0;
    	ensureCapacity(DEFAULT_CAPACITY);
    }
	
    public int size(){
    	return theSize;
    }
    public boolean isEmpty(){
    	return size()==0;
    }
    public void trimToSize(){
    	ensureCapacity(size());
    }
    
	public AnyType get(int idx){
    	if(idx < 0 || idx >= size())
    		throw new ArrayIndexOutOfBoundsException();
    	return theItems[idx];
    }

    public AnyType set(int idx, AnyType newVal){
    	if(idx < 0 || idx >= size())
    		throw new ArrayIndexOutOfBoundsException();
    	AnyType old = theItems[idx];
    	theItems[idx] = newVal;
    	return old;
    }
    
    private void ensureCapacity(int newCapacity) {
		if(newCapacity < theSize)
			return;
		
		AnyType [] old = theItems;
		theItems = (AnyType[]) new Object[newCapacity];
		for(int i = 0; i < size(); i++){
			theItems[i] = old[i];
		}
	}
    
    public boolean add(AnyType x){
    	add(size(),x);
    	return true;
    }
    
    public void add(int idx, AnyType x){
    	if(theItems.length == size() )
    		ensureCapacity(size()*2+1);
    	for(int i = theSize; i > idx; i--)
    		theItems[i] = theItems[i-1];
    	theItems[idx] = x;
    	
    	theSize++;
    }
    
    public AnyType remove(int idx){
    	AnyType removedItem = theItems[idx];
    	for(int i = idx; i < size()-1; i++)
    		theItems[i] = theItems[i+1];
    	theSize--;
    	return removedItem;
    }

	
    
}
