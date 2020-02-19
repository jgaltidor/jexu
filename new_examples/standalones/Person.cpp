#include "Person.h"
#include "jexufuncs.h"

Person::Person(char* para0) throw () 
{
	JavaVM *jvm;
	JNIEnv *env;
	jexu::getJVMAndEnv(jvm, env);

	jclass clazz=env->FindClass("Person");
	jmethodID methodID=env->GetMethodID(clazz,"<init>","(Ljava/lang/String;)V");

	// Pre-Process string argument: para0
	jstring para0Str = env->NewStringUTF(para0);

	// call constructor
	javaObject=env->NewObject(clazz,methodID, para0Str);
}

char* Person::getName() throw () 
{
	JavaVM *jvm;
	JNIEnv *env;
	jexu::getJVMAndEnv(jvm, env);

	jclass clazz=env->FindClass("Person");
	jmethodID methodID=env->GetMethodID(clazz,"getName","()Ljava/lang/String;");
	
	// call method
	jstring returnThing = (jstring) env->CallObjectMethod(javaObject,methodID, NULL);

	return (char*) env->GetStringUTFChars(returnThing, NULL);
}

void Person::setName(char* para0) throw () 
{
	JavaVM *jvm;
	JNIEnv *env;
	jexu::getJVMAndEnv(jvm, env);

	jclass clazz=env->FindClass("Person");
	jmethodID methodID=env->GetMethodID(clazz,"setName","(Ljava/lang/String;)V");
	
	// Pre-Process string argument: para0
	jstring para0Str = env->NewStringUTF(para0);
	
	// call method
	env->CallVoidMethod(javaObject,methodID, para0Str);
}

void Person::copyPerson(Person* para0) throw () 
{
	JavaVM *jvm;
	JNIEnv *env;
	jexu::getJVMAndEnv(jvm, env);

	jclass clazz=env->FindClass("Person");
	jmethodID methodID=env->GetMethodID(clazz,"copyPerson","(LPerson;)V");
	
	
	//Pre-Process object argument
	jobject para0Obj = para0->javaObject;
		// call method
	env->CallVoidMethod(javaObject,methodID, para0Obj);
}

Person* Person::myself() throw()
{
	JavaVM *jvm;
	JNIEnv *env;
	jexu::getJVMAndEnv(jvm, env);

	jclass clazz=env->FindClass("Person");
	jmethodID methodID=env->GetMethodID(clazz,"myself","()LPerson;");
	
	jobject returnVal = env->CallObjectMethod(javaObject, methodID);
	return (Person*) jexu::getCppPointer(returnVal);
}
