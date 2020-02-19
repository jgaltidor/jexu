#ifndef PERSON_H
#define PERSON_H
#include <jni.h>

class Person {
public:
	jobject javaObject;
	Person(char*) throw ();
	char* getName() throw ();
	void setName(char*) throw ();
	void copyPerson(Person* para0) throw ();
};
#endif
