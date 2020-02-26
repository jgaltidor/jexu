#include "jni_utils.h"
#include <cstdlib>
#include <cstring>

const char* getClassPath()
{
    const char* classpath = getenv("CLASSPATH");
    if (classpath == NULL)
    {
        classpath = ".";
    }
    return classpath;
}

char* createClassPathJVMArg()
{
    const char* classpath = getClassPath();
    int classpathJVMArgArrLen =
        strlen("-Djava.class.path=") + strlen(classpath) + 1;
    char* classpathJVMArg =
        static_cast<char *>(malloc(classpathJVMArgArrLen));
    strcpy(classpathJVMArg, "-Djava.class.path=");
    strcat(classpathJVMArg, classpath);
    return classpathJVMArg;
}

JavaVM* JNU_CreateJavaVM(JNIEnv **penv)
{
    JavaVM        *jvm;
    jint           res;
    JavaVMInitArgs vm_args;
    JavaVMOption   options[1];
    options[0].optionString = createClassPathJVMArg();
    //options[1].optionString = "-verbose:jni";

    vm_args.version = JNI_VERSION_1_6;
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

JavaVM* JNU_GetExistingJavaVM(JNIEnv **penv) 
{
    JavaVM *jvm;
    jsize   nVMs;
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

    result = jvm->GetEnv(reinterpret_cast<void **>(penv), JNI_VERSION_1_6);
    if (result != JNI_OK)
    {
        fprintf(stderr, "Can't GetEnv, rc=%d\n", result);
        exit(1);
    }

    return jvm;
}


JavaVM* JNU_GetJavaVM(JNIEnv **penv)
{
    JavaVM* jvm = JNU_GetExistingJavaVM(penv);
    if (jvm == NULL)
    {
        jvm = JNU_CreateJavaVM(penv);
    }
    return jvm;
}

char* JNU_CreateCharsOfJString(JNIEnv *env, jstring jstr)
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

void JNU_handleJavaException(JNIEnv *env)
{
    jthrowable exc = env->ExceptionOccurred();
    if(exc)
    {
        fprintf(stderr, "Java exception occurred\n");
        env->ExceptionDescribe(); // print the stack trace
        env->ExceptionClear();
        exit(1);
    }
}
