package jexu.ast;

public enum JNIType
{
	JOBJECT,
	JCLASS,
	JSTRING,
	JTHROWABLE,
	
	JBOOLEAN,
	JBYTE,
	JCHAR,
	JSHORT,
	JINT,
	JLONG,
	JFLOAT,
	JDOUBLE,
	
	JARRAY,
	JOBJECTARRAY,
	
	JBOOLEANARRAY,
	JBYTEARRAY,
	JCHARARRAY,
	JSHORTARRAY,
	JINTARRAY,
	JLONGARRAY,
	JFLOATARRAY,
	JDOUBLEARRAY,
	
	VOIDTYPE;
	
	public String getName() { return toString().toLowerCase(); }
	
	public boolean isSubTypeOf(JNIType other) {
		switch(other) {
			case JOBJECT: return true;
			case JARRAY:
				switch(this) {
					case JOBJECTARRAY:  return true;
					case JBOOLEANARRAY: return true;
					case JBYTEARRAY:    return true;
					case JCHARARRAY:    return true;
					case JSHORTARRAY:   return true;
					case JINTARRAY:     return true;
					case JLONGARRAY:    return true;
					case JFLOATARRAY:   return true;
					case JDOUBLEARRAY:  return true;
				}
			default: return (other == this);
		}
	}
}
