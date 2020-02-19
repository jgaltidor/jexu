package jexu.visitors;
import jexu.ast.*;

public class JType2JNITypeVisitor implements TypeVisitor {
	
	public JNIType jniType = null;

	public void visitArrType(ArrType typ) {
		if(typ.refType instanceof PrimType) {
			switch((PrimType) typ.refType) {
				case INT:     jniType = JNIType.JINTARRAY;
				case SHORT:   jniType = JNIType.JSHORTARRAY;
				case LONG:    jniType = JNIType.JLONGARRAY;
				case FLOAT:   jniType = JNIType.JFLOATARRAY;
				case DOUBLE:  jniType = JNIType.JDOUBLEARRAY;
				case BOOLEAN: jniType = JNIType.JBOOLEANARRAY;
				case BYTE:    jniType = JNIType.JBYTEARRAY;
				case CHAR:    jniType = JNIType.JCHARARRAY;
			}
		}
		jniType = JNIType.JOBJECTARRAY;
	}

	public void visitClsDef(ClsDef clsdef) {
		if(clsdef.cls.equals(String.class)) {
			jniType = JNIType.JSTRING;
		}
		else {
			jniType = JNIType.JOBJECT;
		}
	}

	public void visitPrimType(PrimType typ) {
		switch(typ) {
			case INT:     jniType = JNIType.JINT;
			case SHORT:   jniType = JNIType.JSHORT;
			case LONG:    jniType = JNIType.JLONG;
			case FLOAT:   jniType = JNIType.JFLOAT;
			case DOUBLE:  jniType = JNIType.JDOUBLE;
			case BOOLEAN: jniType = JNIType.JBOOLEAN;
			case BYTE:    jniType = JNIType.JBYTE;
			case CHAR:    jniType = JNIType.JCHAR;
		}
	}

	public void visitVoidType(VoidType typ) {
		jniType = JNIType.VOIDTYPE;
	}
}
