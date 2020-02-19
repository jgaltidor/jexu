#ifndef JEXUFUNCS_H
#define JEXUFUNCS_H
#include <jni.h>

namespace jexu
{
	void getJVMAndEnv(JavaVM*& jvm, JNIEnv*& env);
	
	bool isInstanceOf(JNIEnv* env, const jobject obj, const char* classname);
}
#endif
