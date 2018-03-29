package OBJECT;
public class MemoryCell{
	//public method
	public Object read(){
		return storedValue;
	}
	public void write(Object x){
		storedValue = x;
	}
	
	private Object storedValue;
}