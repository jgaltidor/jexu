//This code is generated by Jexu.

#ifndef SIMPLELISTITERATOR_H
#define SIMPLELISTITERATOR_H
#include <jni.h>
#include "NoSuchElementException.h"

class SimpleListIterator {
public:
	jobject javaObject;
	SimpleListIterator(SimpleList) throw ();
	jboolean hasNext() throw ();
	jint next() throw (NoSuchElementException);
};
#endif
