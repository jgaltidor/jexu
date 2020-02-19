#ifndef JEXUJAVAEXCEPTION_H
#define JEXUJAVAEXCEPTION_H
#include <jni.h>

class JexuJavaException
{
public:
	JexuJavaException();
    virtual ~JexuJavaException();
	jthrowable javaExceptionObject;
};
#endif

