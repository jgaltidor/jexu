import java.util.*;

public class SimpleList
{
	final LinkedList numbers;
	
	public SimpleList() {
		numbers = new LinkedList();
	}

	public SimpleList(SimpleList other) {
		this();
		numbers.addAll(other.numbers);
	}

	public SimpleList(String stringOfNums)
		throws NumberFormatException
	{
		this();
		StringTokenizer st = new StringTokenizer(stringOfNums);
		while(st.hasMoreTokens()) {
			add(Integer.parseInt(st.nextToken()));
		}
	}

	public boolean add(String numStr) throws NumberFormatException {
		return add(Integer.parseInt(numStr));
	}

	public boolean add(int num) {
		return numbers.add(new Integer(num));
	}

	public SimpleList sort() {
		SimpleList l = new SimpleList(this);
		Collections.sort(l.numbers);
		return l;
	}

	public SimpleListIterator iterator() {
		return new SimpleListIterator(this);
	}

	public static void main(String[] args) {
		System.out.println("Running SimpleList.java");
		String s1 = "454 245 24 53";
		String s2 = "1 2 three 4";
		SimpleList list1 = new SimpleList(s1);
		SimpleList sortedList1 = list1.sort();
		
		System.out.println("list1 numbers:");
		SimpleListIterator itr = list1.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}

		System.out.println("list1 sorted:");
		itr = sortedList1.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}

    try { list1.add("four"); }
    catch(NumberFormatException e) {
    	System.err.println(e);
    }
    
    SimpleList list2 = new SimpleList(s2);
	}
}

