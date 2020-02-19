package jexu.generators;
import jexu.ast.*;
import jexu.converters.Converter;
import jexu.util.Pair;

import java.io.File;
import java.util.*;

public class GeneratorUtils
{
	public static final Set<ClsDef> classesGenerated = new HashSet<ClsDef>();
	
	// classes to skip generation
	public static final void addClassesToSkip(Collection<? super ClsDef> collec) {
		collec.add(ClsDef.strCls);
	}
	
	public static final Queue<ClsDef> classesToGenerate = new LinkedList<ClsDef>();
	
	public static boolean registerClass(ClsDef cls) {
		boolean addClass = !classesGenerated.contains(cls);
		if(addClass) classesToGenerate.add(cls);
		return addClass;
	}
	
	static void addClassesToGenerateFromDeclList(List<Decl> decls) {
		for(Decl decl : decls) {
			if(decl.typ instanceof ClsDef) {
				registerClass((ClsDef) decl.typ);			
			}
		}
	}
	
	static void addClassesToGenerateFromClsList(List<ClsDef> classes) {
		for(ClsDef cls : classes) registerClass(cls);
	}
	
	public static final String commentLine = "// =============================================";
	
	public static File getHeaderFile(ClsDef cls) {
		Context con = cls.getContext();
		if(con instanceof ClsDef) {
			// return header file of the outermost class
			return getHeaderFile((ClsDef) con);
		}
		else {
			String filepath = cls.getName().replace('.', File.separatorChar);
			return new File(filepath + ".h");
		}
	}

	public static File getImplFile(ClsDef cls) {
		Context con = cls.getContext();
		if(con instanceof ClsDef) {
			// return impl file of the outermost class
			return getImplFile((ClsDef) con);
		}
		else {
			String filepath = cls.getName().replace('.', File.separatorChar);
			return new File(filepath + ".cpp");
		}
	}
	
	public static String getClsMacro(ClsDef cls) {
		return cls.getName().toUpperCase().replace('.', '_') + "_H";
	}
	
	public final static String[] reservedNames = {
		"jvm", "env", "clazz", "methodID", "returnVal", "javaObject",
		"exc", "jexu", "exceptionClass", "cppExc"
	};
	
	public static List<Pair<String,String>> argNamesCppAndJava(List<Decl> decls)
	{
		LinkedList<Pair<String,String>> argNames = new LinkedList<Pair<String,String>>();
		LinkedList<String> usedNames = new LinkedList<String>();
		for(String name : reservedNames) usedNames.add(name);
		int i = 0;
		for(Decl decl : decls) {
			String cname = decl.name;
			if(cname == null) {
				cname = "arg" + i;
			}
			cname = ensureFreshName(cname, usedNames);
			usedNames.add(cname);
			// Determine corresponding Java arg name
			Converter conv = type2converter(decl.typ);
			String jname;
			if(!conv.passingCppArg()) {
				String suffix = conv.jnameSuffix();
				jname = ensureFreshName(cname+suffix, usedNames);
			}
			else {
				jname = cname;
			}
			argNames.add(new Pair<String,String>(cname, jname));
			i++;
		}
		return argNames;
	}

	public static String ensureFreshName(String name, List<String> usedNames) {
		String freshName = name;
		String suffix = "_";
		while(usedNames.contains(freshName)) {
			freshName += suffix;
		}
		return freshName;
	}
	
	public static String cppName(Member mem) {
		return mem.getName().replace(".", "::");
	}
	
	public static String jniName(Member mem) {
		return mem.getName().replace('.', '/');
	}
	
	public static String jniFuncName(FuncDef func) {
		if(func instanceof ConstructDef) {
			return "<init>";
		}
		else if(func instanceof MethDef) {
			return func.getBaseName();
		}
		throw new AssertionError("Unknown type of FuncDef: " + func);
	}
	
	public static String jniCallMethod(JNIType jnitype, boolean isStatic) {
		if(isStatic) {
			switch(jnitype) {
				case VOIDTYPE:  return "CallStaticVoidMethod";
				case JOBJECT:   return "CallStaticObjectMethod";
				case JBOOLEAN:  return "CallStaticBooleanMethod";
				case JBYTE:     return "CallStaticByteMethod";
				case JCHAR:     return "CallStaticCharMethod";
				case JSHORT:    return "CallStaticShortMethod";
				case JINT:      return "CallStaticIntMethod";
				case JLONG:     return "CallStaticLongMethod";
				case JFLOAT:    return "CallStaticFloatMethod";
				case JDOUBLE:   return "CallStaticDoubleMethod";
				
				default: return jniCallMethod(JNIType.JOBJECT, true);
			}
		}
		else {
			switch(jnitype) {
				case VOIDTYPE:  return "CallVoidMethod";
				case JOBJECT:   return "CallObjectMethod";
				case JBOOLEAN:  return "CallBooleanMethod";
				case JBYTE:     return "CallByteMethod";
				case JCHAR:     return "CallCharMethod";
				case JSHORT:    return "CallShortMethod";
				case JINT:      return "CallIntMethod";
				case JLONG:     return "CallLongMethod";
				case JFLOAT:    return "CallFloatMethod";
				case JDOUBLE:   return "CallDoubleMethod";
				
				default: return jniCallMethod(JNIType.JOBJECT, false);
			}
		}
	}

	private static jexu.visitors.Type2ConverterVisitor convVisitor =
		new jexu.visitors.Type2ConverterVisitor();

	public static Converter type2converter(JType typ) {
		typ.accept(convVisitor);
		return convVisitor.result;
	}

	private static jexu.visitors.JNIStrDescriptor descVisitor =
		new jexu.visitors.JNIStrDescriptor();

	public static String jniStrDescriptor(JType typ) {
		typ.accept(descVisitor);
		return descVisitor.descriptor;
	}

	private static jexu.visitors.JType2JNITypeVisitor jniTypeVisitor =
		new jexu.visitors.JType2JNITypeVisitor();

	public static JNIType getJNIType(JType typ) {
		typ.accept(jniTypeVisitor);
		return jniTypeVisitor.jniType;
	}
}
