#ifndef JEXUHELPER_H
#define JEXUHELPER_H

#include <jni.h>

JavaVM *JNU_GetJavaVM(JNIEnv **penv);
JavaVM *JNU_CreateJavaVM(JNIEnv **penv);
char* JNU_GetStringNativeChars(JNIEnv *env, jstring jstr);

#endif

