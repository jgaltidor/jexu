import java.util.*;

public class SimpleListIterator
{
	private Iterator itr;
	
	public SimpleListIterator(SimpleList list) {
		itr = list.numbers.iterator();
	}

	public boolean hasNext() { return itr.hasNext(); }

	public int next() throws NoSuchElementException {
		Integer i = (Integer) itr.next();
		return i.intValue();
	}
}
