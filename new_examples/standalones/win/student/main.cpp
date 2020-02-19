#include "Student.h"
#include <iostream>
using namespace std;

int main() {
	cout << "Program started" << endl;
	Student s("john", 26, 3.9);
	cout << "s.getAge(): " << s.getAge() << endl;
	return 0;
}
