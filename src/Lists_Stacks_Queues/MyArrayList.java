package Lists_Stacks_Queues;

import java.util.Iterator;

//AarryList的实现
/**
 * 细节：
 * 1、保持基础数组、数组的容量，以及存储其中的当前项
 * 2、提供一种机制以改变基础数组的容量
 *    通过获得一个新数组，将老数组拷贝到新数组中来改变数组容量，允许虚拟机回收老数组
 * 3、提供get和set的实现
 * 4、提供基本例程：size、isEmpty、clear，它们是典型的单行程序
 *    还提供remove，以及两个版本的add
 *    如果数组大小和容量相同，那么这两个add将增加容量
 * 5、提供一个实现iterator接口的类，并实现next、haxNext和remove
 */
public class MyArrayList<AnyType> implements Iterable<AnyType>{
	
	private static final int DEFAULT_CAPACITY = 10;
	
	private int theSize;
	private AnyType [] theItems;
	
    public MyArrayList() {
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
    
    
	public Iterator<AnyType> iterator() {
		return new ArrayListIterator();
	}
	
	//ArrayListIterator作为内部类使用
	/**
	 * 或者另建一个ArrayListIterator类，且改MyArrayList的属性为public
	 * 不过违背了良好的面向对象编程的基本原则，它要求数据应尽可能隐蔽
	 */
	private class ArrayListIterator implements java.util.Iterator<AnyType>{
		private int current = 0;
		//多余的，下面讲到
		//private MyArrayList<AnyType> theList;
		
		public boolean hasNext(){
			return current < size();
		}
		
		public AnyType next(){
			if(!hasNext())
				throw new java.util.NoSuchElementException();
			return theItems[current++];
		}
		
		public void remove(){
			//防止迭代器的remove与MyArrayList的remove冲突，所有必须使用MyArrayList.this.remove()
			MyArrayList.this.remove(--current);
		}
	}
	
}
