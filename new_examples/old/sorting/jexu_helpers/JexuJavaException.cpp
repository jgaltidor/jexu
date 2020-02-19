#include "JexuJavaException.h"
#include "JexuHelper.h"

JexuJavaException::JexuJavaException() {}

JexuJavaException::~JexuJavaException()
{
    JavaVM *jvm;
    JNIEnv *env;
    jvm = JNU_GetJavaVM(&env);
    if (jvm == NULL)
    {
        jvm = JNU_CreateJavaVM(&env);
    }
    env->DeleteGlobalRef(javaExceptionObject);
}
