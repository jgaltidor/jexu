#ifndef JEXULIB_H
#define JEXULIB_H
#include <jni.h>

namespace jexu
{
	void getJVMAndEnv(JavaVM*& jvm, JNIEnv*& env);
	
	bool isInstanceOf(JNIEnv* env, const jobject obj, const char* classname);
	
	void* getCppPointer(jobject obj);
	
	// Base class for jexu generated classes
	class JavaBaseClass
	{
		public:
			jobject getJavaObject();
			void setJavaObject(jobject jobj);
		protected:
			JavaBaseClass();
			JavaBaseClass(jobject jobj);
			jobject javaObject;
	};
	
	// Default Java exception class
	class JavaException
	{
	public:
		JavaException();
		virtual ~JavaException();
		jthrowable javaObject;
	};
}
#endif
