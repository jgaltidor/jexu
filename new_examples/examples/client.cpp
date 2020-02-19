#include "Person.h"
#include <iostream>
using namespace std;

int main() {
	cout << "Using a Java class from C++" << endl;
	examples::Person p1("john");
	examples::Person* p1Ptr = p1.myself();
	examples::Person p2("mike");
	cout << "p1.getName(): " << p1.getName() << endl;
	cout << "p1Ptr->getName(): " << p1Ptr->getName() << endl;
	p1.setName("altidor");
	cout << "p1.getName(): " << p1.getName() << endl;
	cout << "p1Ptr->getName(): " << p1Ptr->getName() << endl;
	
	examples::Person* p2Ptr = new examples::Person("mike");
	p2Ptr->copyPerson(p1Ptr);
	p2Ptr->setName("smith");
	
	examples::Person::printPerson(p1Ptr);
	examples::Person::printPerson(p2Ptr);
	return 0;
}
