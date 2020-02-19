#include "JexuHelper.h"
#include <stdlib.h>
#include <string.h>

JavaVM *JNU_GetJavaVM(JNIEnv **penv) 
{
    JavaVM *jvm = NULL;
    jsize   nVMs = -1;
    jint    result;

    result = JNI_GetCreatedJavaVMs(&jvm, 1, &nVMs);
    if (result != 0)
    {
        fprintf(stderr, "Can't get created Java VMs\n");
        exit(1);
    }
    if (nVMs < 1)
    {
        return NULL;
    }

    result = jvm->GetEnv(reinterpret_cast<void **>(penv), JNI_VERSION_1_2);
    if (result != JNI_OK)
    {
        fprintf(stderr, "Can't GetEnv, rc=%d\n", result);
        exit(1);
    }

    return jvm;
}

JavaVM *JNU_CreateJavaVM(JNIEnv **penv)
{
    JavaVM        *jvm;
    jint           res;
    JavaVMInitArgs vm_args;
    JavaVMOption   options[1];
    char          *classpath;

    classpath = getenv("CLASSPATH");
    if (classpath == NULL)
    {
        classpath = ".";
    }

    options[0].optionString = static_cast<char *>(malloc(strlen("-Djava.class.path=") + strlen(classpath) + 1));
    strcpy(options[0].optionString, "-Djava.class.path=");
    strcat(options[0].optionString, classpath);
	//options[1].optionString = "-verbose:jni";

    vm_args.version = JNI_VERSION_1_2;
    vm_args.options = options;
    vm_args.nOptions = 1;
    vm_args.ignoreUnrecognized = JNI_TRUE;

    /* Create the Java VM */
    res = JNI_CreateJavaVM(&jvm, reinterpret_cast<void**>(penv), &vm_args);
    if (res < 0)
    {
        fprintf(stderr, "Can't create JavaVM\n");
        exit(1);
    }

    return jvm;
}

char* JNU_GetStringNativeChars(JNIEnv *env, jstring jstr)
{
	if(jstr == NULL)
		return 0;
	const char* bytes = env->GetStringUTFChars(jstr, NULL);
	if(bytes == 0)
		return 0;
	jsize len = env->GetStringUTFLength(jstr);
	char* result = new char[len + 1];
	strncpy(result, (char*) bytes, len);
	env->ReleaseStringUTFChars(jstr,bytes);
	result[len] = '\0';
	return result;
}
