#include "Student.h"
#include "jni_utils.h"

Student::Student(const char* para0,jint para1,jfloat para2) throw () 
{
	JNIEnv* env;
	JavaVM* jvm = JNU_GetJavaVM(&env);
	jclass clazz = env->FindClass("Student");
	jmethodID methodID = env->GetMethodID(clazz,"<init>","(Ljava/lang/String;IF)V");
	javaObject = env->NewObject(clazz,methodID, env->NewStringUTF(para0), para1, para2);

	JNU_handleJavaException(env);

}

char* Student::getName() throw () 
{
	JNIEnv* env;
	JavaVM* jvm = JNU_GetJavaVM(&env);
	jclass clazz = env->FindClass("Student");
	jmethodID methodID = env->GetMethodID(clazz,"getName","()Ljava/lang/String;");
	char* returnThing = JNU_CreateCharsOfJString(env, (jstring) env->CallObjectMethod(javaObject,methodID));

	JNU_handleJavaException(env);

	return returnThing;
}

jint Student::getAge() throw () 
{
	JNIEnv* env;
	JavaVM* jvm = JNU_GetJavaVM(&env);
	jclass clazz = env->FindClass("Student");
	jmethodID methodID = env->GetMethodID(clazz,"getAge","()I");
	jint returnThing = env->CallIntMethod(javaObject,methodID);

	JNU_handleJavaException(env);

	return returnThing;
}

jfloat Student::getGPA() throw () 
{
	JNIEnv* env;
	JavaVM* jvm = JNU_GetJavaVM(&env);
	jclass clazz = env->FindClass("Student");
	jmethodID methodID = env->GetMethodID(clazz,"getGPA","()F");
	jfloat returnThing = env->CallFloatMethod(javaObject,methodID);

	JNU_handleJavaException(env);

	return returnThing;
}

void Student::setName(char* para0) throw () 
{
	JNIEnv* env;
	JavaVM* jvm = JNU_GetJavaVM(&env);
	jclass clazz = env->FindClass("Student");
	jmethodID methodID = env->GetMethodID(clazz,"setName","(Ljava/lang/String;)V");
	env->CallVoidMethod(javaObject,methodID, env->NewStringUTF(para0));

	JNU_handleJavaException(env);
}

void Student::setAge(jint para0) throw () 
{
	JNIEnv* env;
	JavaVM* jvm = JNU_GetJavaVM(&env);
	jclass clazz = env->FindClass("Student");
	jmethodID methodID = env->GetMethodID(clazz,"setAge","(I)V");
	env->CallVoidMethod(javaObject,methodID, para0);

	JNU_handleJavaException(env);
}

void Student::setGPA(jfloat para0) throw () 
{
	JNIEnv* env;
	JavaVM* jvm = JNU_GetJavaVM(&env);
	jclass clazz = env->FindClass("Student");
	jmethodID methodID = env->GetMethodID(clazz,"setGPA","(F)V");
	env->CallVoidMethod(javaObject,methodID, para0);

	JNU_handleJavaException(env);
}

