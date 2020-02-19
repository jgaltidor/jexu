#ifndef JEXUHELPER_H
#define JEXUHELPER_H

#include <jni.h>

JavaVM* JNU_GetJavaVM(JNIEnv **penv);
char* JNU_CreateCharsOfJString(JNIEnv *env, jstring jstr);
void JNU_handleJavaException(JNIEnv *env);

#endif

