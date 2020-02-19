public class Student
{
	String name;
	int age;
	float gpa;
	
	public Student(String n, int a, float g) {
		name = n; age = a; gpa = g;
	}

	public String getName() { return name; }
	public int    getAge()  { return age; }
	public float  getGPA()  { return gpa; }

	public void setName(String n) { name = n; }
	public void setAge(int a)     { age = a; }
	public void setGPA(float g)   { gpa = g; }
}
