// =============================================
// Generated on: Tue Nov 23 21:08:11
// Implementation of proxy class for Java class: examples.Person
// =============================================
#include "Person.h"

examples::Person::Person(jobject jobj) : jexu::JavaBaseClass(jobj) {}

examples::Person::Person(char* arg0) {
  // getting jvm and env
  JavaVM *jvm;
  JNIEnv *env;
  jexu::getJVMAndEnv(jvm, env);

  // get handle to enclosing class
  jclass clazz=env->FindClass("examples/Person");
  // get handle to method
  jmethodID methodID = env->GetMethodID(clazz, "<init>", "(Ljava/lang/String;)V");

  // Pre-Process string argument: arg0
  jstring arg0Str = env->NewStringUTF(arg0);

  this->setJavaObject(env->NewObject(clazz, methodID, arg0Str));

  jthrowable exc = env->ExceptionOccurred();
  if(exc) {
    env->ExceptionClear();

    // handle undeclared exception
    jexu::JavaException cppExc;
    cppExc.javaObject = static_cast<jthrowable>(env->NewGlobalRef(exc));
    throw cppExc;
  }
}

char* examples::Person::getName() {
  // getting jvm and env
  JavaVM *jvm;
  JNIEnv *env;
  jexu::getJVMAndEnv(jvm, env);

  // get handle to enclosing class
  jclass clazz = env->GetObjectClass(this->javaObject);
  // get handle to method
  jmethodID methodID = env->GetMethodID(clazz, "getName", "()Ljava/lang/String;");


  jstring returnVal = (jstring) env->CallObjectMethod(this->javaObject, methodID);

  jthrowable exc = env->ExceptionOccurred();
  if(exc) {
    env->ExceptionClear();

    // handle undeclared exception
    jexu::JavaException cppExc;
    cppExc.javaObject = static_cast<jthrowable>(env->NewGlobalRef(exc));
    throw cppExc;
  }
  return (char*) env->GetStringUTFChars(returnVal, NULL);
}

void examples::Person::setName(char* arg0) {
  // getting jvm and env
  JavaVM *jvm;
  JNIEnv *env;
  jexu::getJVMAndEnv(jvm, env);

  // get handle to enclosing class
  jclass clazz = env->GetObjectClass(this->javaObject);
  // get handle to method
  jmethodID methodID = env->GetMethodID(clazz, "setName", "(Ljava/lang/String;)V");

  // Pre-Process string argument: arg0
  jstring arg0Str = env->NewStringUTF(arg0);

  env->CallVoidMethod(this->javaObject, methodID, arg0Str);

  jthrowable exc = env->ExceptionOccurred();
  if(exc) {
    env->ExceptionClear();

    // handle undeclared exception
    jexu::JavaException cppExc;
    cppExc.javaObject = static_cast<jthrowable>(env->NewGlobalRef(exc));
    throw cppExc;
  }
}

void examples::Person::copyPerson(examples::Person* arg0) {
  // getting jvm and env
  JavaVM *jvm;
  JNIEnv *env;
  jexu::getJVMAndEnv(jvm, env);

  // get handle to enclosing class
  jclass clazz = env->GetObjectClass(this->javaObject);
  // get handle to method
  jmethodID methodID = env->GetMethodID(clazz, "copyPerson", "(Lexamples/Person;)V");

  // Pre-Process object argument: arg0
  jobject arg0Obj = arg0->getJavaObject();

  env->CallVoidMethod(this->javaObject, methodID, arg0Obj);

  jthrowable exc = env->ExceptionOccurred();
  if(exc) {
    env->ExceptionClear();

    // handle undeclared exception
    jexu::JavaException cppExc;
    cppExc.javaObject = static_cast<jthrowable>(env->NewGlobalRef(exc));
    throw cppExc;
  }
}

examples::Person* examples::Person::myself() {
  // getting jvm and env
  JavaVM *jvm;
  JNIEnv *env;
  jexu::getJVMAndEnv(jvm, env);

  // get handle to enclosing class
  jclass clazz = env->GetObjectClass(this->javaObject);
  // get handle to method
  jmethodID methodID = env->GetMethodID(clazz, "myself", "()Lexamples/Person;");


  jobject returnVal = env->CallObjectMethod(this->javaObject, methodID);

  jthrowable exc = env->ExceptionOccurred();
  if(exc) {
    env->ExceptionClear();

    // handle undeclared exception
    jexu::JavaException cppExc;
    cppExc.javaObject = static_cast<jthrowable>(env->NewGlobalRef(exc));
    throw cppExc;
  }
  return new examples::Person(returnVal);
}

void examples::Person::printPerson(examples::Person* arg0) {
  // getting jvm and env
  JavaVM *jvm;
  JNIEnv *env;
  jexu::getJVMAndEnv(jvm, env);

  // get handle to enclosing class
  jclass clazz=env->FindClass("examples/Person");
  // get handle to method
  jmethodID methodID = env->GetStaticMethodID(clazz, "printPerson", "(Lexamples/Person;)V");

  // Pre-Process object argument: arg0
  jobject arg0Obj = arg0->getJavaObject();

  env->CallStaticVoidMethod(clazz, methodID, arg0Obj);

  jthrowable exc = env->ExceptionOccurred();
  if(exc) {
    env->ExceptionClear();

    // handle undeclared exception
    jexu::JavaException cppExc;
    cppExc.javaObject = static_cast<jthrowable>(env->NewGlobalRef(exc));
    throw cppExc;
  }
}

