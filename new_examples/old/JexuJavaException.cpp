#include "JexuJavaException.h"
#include "jexufuncs.h"

JexuJavaException::JexuJavaException() {}

JexuJavaException::~JexuJavaException() {
	JavaVM *jvm;
	JNIEnv *env;
	jexu::getJVMAndEnv(jvm, env);
	env->DeleteGlobalRef(javaExceptionObject);
}
