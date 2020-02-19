#include "SimpleList.h"
#include "SimpleListIterator.h"
#include <iostream>
#include <string>
using namespace std;

int main(int argc, char *argv[])
{
		cout << "Running main.cpp" << endl;
		SimpleList* list = new SimpleList;
		for(int i = 0; i < argc; i++) {
			list->add(argv[i]);
		}

		// Printing out list		
		cout << "list: ";
		SimpleListIterator* itr = list.iterator();
		while(itr->hasNext()) {
			int num = itr->next();
			cout << num << ' ';
		}
		cout << endl;

		// Printing out list2
		cout << "list sorted: ";
		SimpleList* sortedList1 = list.sort();
		itr = sortedList1.iterator();
		while(itr->hasNext()) {
			int num = itr->next();
			cout << num << ' ';
		}
		cout << endl;

		// Reading in badly formatted strings
		char* str = "four";
		try {
			list->add(str);
		}
		catch(NumberFormatException* e) {
			cout << "Caught a NumberFormatException thrown from Java" << endl;
		}

		str = "1 2 three 4";
		try {
			list = new SimpleList(str);
		}
		catch(NumberFormatException* e) {
			cout << "Caught a NumberFormatException thrown from Java" << endl;
		}
		cout << "C++/Java program completed successfully" << endl;
}
