package OBJECT;

import java.util.MissingFormatArgumentException;

public class WrapperDemo {
	public static void main(String[] args) {
		MemoryCell m = new MemoryCell();
		
		m.write(new Integer(47));
		Integer wrapperVal = (Integer) m.read();
		int val = wrapperVal.intValue();
		System.out.println("Contents are: "+val);
	}
}
