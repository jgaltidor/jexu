//This code is generated by Jexu.

#ifndef STUDENT_H
#define STUDENT_H
#include <jni.h>

class Student {
public:
	jobject javaObject;
	Student(const char*,jint,jfloat) throw ();
	char* getName() throw ();
	jint getAge() throw ();
	jfloat getGPA() throw ();
	void setName(char*) throw ();
	void setAge(jint) throw ();
	void setGPA(jfloat) throw ();
};
#endif
