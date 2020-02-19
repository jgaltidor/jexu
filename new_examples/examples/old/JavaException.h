#ifndef JEXU_JAVAEXCEPTION_H
#define JEXU_JAVAEXCEPTION_H
#include <jni.h>

namespace jexu {

class JavaException
{
public:
	JavaException();
	jthrowable javaObject;
};
}
#endif

