#include "Person.h"
#include <iostream>
using namespace std;

int main() {
	cout << "Program started" << endl;
	Person p("john");
	cout << "p.getName(): " << p.getName() << endl;
	Person p2("smith");
	cout << "p2.getName(): " << p2.getName() << endl;
	p2.copyPerson(&p);
	cout << "p2.getName(): " << p2.getName() << endl;
	return 0;
}
