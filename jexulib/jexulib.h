#ifndef JEXULIB_H
#define JEXULIB_H
#include <jni.h>

namespace jexu
{
	void getJVMAndEnv(JavaVM*& jvm, JNIEnv*& env);
	
	bool isInstanceOf(JNIEnv* env, const jobject obj, const char* classname);
	
	void* getCppPointer(jobject obj);
	
	class JavaException
	{
	public:
		JavaException();
		virtual ~JavaException();
		jthrowable javaObject;
	};
}
#endif
