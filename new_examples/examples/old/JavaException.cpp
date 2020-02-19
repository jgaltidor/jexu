#include "JavaException.h"
#include "jexufuncs.h"

JavaException::JavaException() {}

JexuJavaException::~JexuJavaException() {
	JavaVM *jvm;
	JNIEnv *env;
	jexu::getJVMAndEnv(jvm, env);
	env->DeleteGlobalRef(javaObject);
}
