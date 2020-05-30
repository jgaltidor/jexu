package examples;
public class Person
{
	String name;

	public Person(String n) { name = n; }

	public String getName() { return name; }
	public void setName(String n) { name = n; }
	
	public void copyPerson(Person p) {
		setName(p.getName());
	}
	
	public Person myself() { return this; }
	
	public static void printPerson(Person p) {
		System.out.println("Printing person: " + p.getName());
	}
}
