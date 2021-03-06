//This code is generated by Jexu.

#ifndef SIMPLELIST_H
#define SIMPLELIST_H
#include <jni.h>
#include "NumberFormatException.h"

class SimpleList {
public:
	jobject javaObject;
	SimpleList() throw ();
	SimpleList(SimpleList) throw ();
	SimpleList(char*) throw (NumberFormatException);
	jboolean add(char*) throw (NumberFormatException);
	jboolean add(jint) throw ();
	SimpleList sort() throw ();
	SimpleListIterator iterator() throw ();
	static void main(char*Array) throw ();
};
#endif
