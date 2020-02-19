#include "jexulib.h"
#include <cstdlib>
#include <string>
#include <map>

void createJVM(JavaVM*& jvm, JNIEnv*& env);
void getJVMAndEnv2(JavaVM*& jvm, JNIEnv*& env);

void jexu::getJVMAndEnv(JavaVM*& jvm, JNIEnv*& env) {
	getJVMAndEnv2(jvm, env);
	if(jvm == NULL) createJVM(jvm, env);
}

void createJVM(JavaVM*& jvm, JNIEnv*& env) {
	JavaVMInitArgs vm_args;
	vm_args.version = JNI_VERSION_1_2;
	vm_args.ignoreUnrecognized = JNI_TRUE;
	
	// JVM Options
	// classpath option
	char* classpath = getenv("CLASSPATH");
	std::string clspathOpt = "-Djava.class.path=";
	if(classpath != NULL) {
		clspathOpt += classpath;
	}
	else {
		clspathOpt += '.';
	}
	vm_args.nOptions = 1;
	JavaVMOption options[1];  // length of array should equal vm_args.nOptions
	options[0].optionString = (char*) clspathOpt.c_str();
	// option for printing JNI-related messages: -verbose:jni
	vm_args.options = options;

	// Create the Java VM
	jint res = JNI_CreateJavaVM(&jvm, (void **) &env, &vm_args);
	if (res < 0) {
		fprintf(stderr, "Can't create JavaVM\n");
		exit(1);
	}
}

void getJVMAndEnv2(JavaVM*& jvm, JNIEnv*& env) {
	jsize nVMs = -1;
	jint result = JNI_GetCreatedJavaVMs(&jvm, 1, &nVMs);
	if (result != 0) {
		fprintf(stderr, "Can't get created Java VMs\n");
		exit(1);
	}
	if (nVMs < 1) { // if there are no JVMs
		jvm = NULL;
	}
	else {
		// Get Java env
		result = jvm->GetEnv((void **) &env, JNI_VERSION_1_2);
		if (result != JNI_OK) {
				fprintf(stderr, "Can't GetEnv, rc=%d\n", result);
				exit(1);
		}
	}
}

bool jexu::isInstanceOf(JNIEnv* env, const jobject obj, const char* classname) {
	jclass otherClass = env->FindClass(classname);
	return (otherClass != NULL && env->IsInstanceOf(obj, otherClass));
}

// void jexu::throwCppException(jobject obj) { }

std::map<jobject, void*> javaObj2cppObj;

void registerObjs(jobject jobj, void* cobj) {
	javaObj2cppObj[jobj] = cobj;
}

void* jexu::getCppPointer(jobject obj) {
	return javaObj2cppObj[obj];
}


// Implementation of class jexu::JavaBaseClass
jexu::JavaBaseClass::JavaBaseClass() {}

jexu::JavaBaseClass::JavaBaseClass(jobject jobj) {
	this->setJavaObject(jobj);
}

jobject jexu::JavaBaseClass::getJavaObject() {
	return this->javaObject;
}

void jexu::JavaBaseClass::setJavaObject(jobject jobj) {
	registerObjs(jobj, this);
	this->javaObject = jobj;
}


// Implementation of class jexu::JavaException

jexu::JavaException::JavaException() {}

jexu::JavaException::~JavaException() {
	JavaVM *jvm;
	JNIEnv *env;
	jexu::getJVMAndEnv(jvm, env);
	env->DeleteGlobalRef(this->javaObject);
}

