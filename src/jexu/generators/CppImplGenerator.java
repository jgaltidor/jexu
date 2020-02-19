package jexu.generators;
import java.util.*;
import java.io.File;
import jexu.ast.*;
import jexu.util.*;
import static jexu.generators.GeneratorUtils.*;
import static jexu.util.Utils.*;
import jexu.converters.Converter;

public class CppImplGenerator extends TabPrintWrapper
{	
	public void generateImplFile(Class<?> cls) {
		generateImplFile((ClsDef) jexu.ast.ASTUtils.getType(cls));
	}

	public void generateImplFile(ClsDef cls) {
		File headerFile = getHeaderFile(cls);
		File implFile = getImplFile(cls);
		ensureParentDirExists(implFile);
		System.out.println("Generating implementation file: " + implFile);
		try {
			out = new TabPrintStream(implFile, "  ");
		}
		catch(java.io.FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		println(commentLine);
		println("// Generated on: " + getCurrentDateTimeStr());
		println("// Implementation of proxy class for Java class: " + cls.getName());
		println(commentLine);

		printf( "#include \"%s\"", headerFile.getName());
		println();
		println();
		
		processCls(cls);
		out.close();
		System.out.println("completed generation of impl file: "
			+ implFile.getName());
	}

	public void processCls(ClsDef cls) {
		classesGenerated.add(cls);
		println("examples::Person::Person(jobject jobj) : jexu::JavaBaseClass(jobj) {}");
		println();
		for(ConstructDef c : cls.getPublicConstructors())
			processFunction(c);
		for(ConstructDef c : cls.getProtectedConstructors())
			processFunction(c);
		for(MethDef m : cls.getPublicMethods()) processFunction(m);
		for(MethDef m : cls.getProtectedMethods()) processFunction(m);
	}
	
	
	public void processFunction(FuncDef func) {
		List<Decl> parameters = func.getParameters();
		List<ClsDef> exceptions = func.getExceptions();
		List<Pair<String, String>> paramNames = argNamesCppAndJava(parameters);
		Vector<Converter> converters = new Vector<Converter>();
		int i = 0;
		for(Decl decl : parameters) {
			Converter conv = type2converter(decl.typ);
			Pair<String, String> cppAndJavaName = paramNames.get(i++);
			conv.cname = cppAndJavaName.fst;
			conv.jname = cppAndJavaName.snd;
			conv.out = this.out;
			converters.add(conv);
		}
		JType returnType = null;
		Converter returnConverter = null;
		if(func instanceof MethDef) {
			MethDef meth = (MethDef) func;
			returnType = meth.getReturnType();
			if(returnType != VoidType.getInstance()) {
				returnConverter = type2converter(returnType);
				returnConverter.out = this.out;
			}
		}
		
		// function signature
		if(returnType != null) {
			if(returnType == VoidType.getInstance()) {
				print("void ");
			}
			else {
				print(returnConverter.getCppTypeName() + ' ');
			}
		}
		print(cppName(func));
		print('(');

		// argument declarations
		Iterator<Converter> itr = converters.iterator();
		i = 0;
		while(itr.hasNext()) {
			Converter conv = itr.next();
			String cppTypeName = conv.getCppTypeName();
			String cppArgName = conv.cname;
			print(cppTypeName + ' ' + cppArgName);
			if(itr.hasNext()) print(", ");
		}
		print(')');
		
		// throw declaration
		if(!exceptions.isEmpty()) {
			print("throw (");
			Iterator<ClsDef> excItr = exceptions.iterator();
			while(excItr.hasNext()) {
				print(cppName(excItr.next()));
				if(excItr.hasNext()) print(", ");
			}
			print(')');
		}
		
		println(" {");
		incrementTabs();
		
		// start method body
		println("// getting jvm and env");
		println("JavaVM *jvm;");
		println("JNIEnv *env;");
		println("jexu::getJVMAndEnv(jvm, env);");
		println();
		
		println("// get handle to enclosing class");
		if((func instanceof ConstructDef) || (func instanceof MethDef && ((MethDef) func).isStatic())) {
			printf("jclass clazz=env->FindClass(\"%s\");",
				jniName(func.getContext()));
			println();
		}
		else {
			println("jclass clazz = env->GetObjectClass(this->javaObject);");
		}
		
		// jni arg type signature of the method
		String argSig = "";
		for(Decl decl : parameters) {
			argSig += jniStrDescriptor(decl.typ);
		}
		
		// jni return type signature of the method
		String returnSig;
		if(func instanceof ConstructDef) {
			returnSig = "V";
		}
		else {
			returnSig = jniStrDescriptor(returnType);
		}
		
		// jni method type signature
		String methSig = String.format("(%s)%s", argSig, returnSig);

		println("// get handle to method");
		String getMethId = "GetMethodID";
		if(func instanceof MethDef && ((MethDef) func).isStatic()) {
			getMethId = "GetStaticMethodID";
		}
		printf("jmethodID methodID = env->%s(clazz, \"%s\", \"%s\");",
			getMethId, jniFuncName(func), methSig);
		println();
		println();

		// Pre-process arguments before method call
		for(Converter c : converters) {
			c.preprocess();
		}
		println();
		
		// method call/assignment statement
		if(func instanceof ConstructDef) {
			print("this->setJavaObject(env->NewObject(clazz, methodID");
		}
		else if(func instanceof MethDef) {
			MethDef meth = (MethDef) func;
			JNIType jniType = getJNIType(returnType);
			if(jniType != JNIType.VOIDTYPE) {
				printf("%s returnVal = ", jniType.getName());
				// print cast if necessary
				if(castReturnedRequired(jniType)) {
					printf("(%s) ", jniType.getName());
				}
			}
			printf("env->%s(", jniCallMethod(jniType, meth.isStatic()));
			if(meth.isStatic()) {
				print("clazz, ");
			}
			else {
				print("this->javaObject, ");
			}
			print("methodID");
		}
		// pass arguments to java method call
		for(Converter c : converters) {
			print(", " + c.jname);
		}
		print(')');
		if(func instanceof ConstructDef) print(')');
		println(';');
		
		// Post-process arguments before method call
		for(Converter c : converters) {
			c.postprocess();
		}
		println();
		
		// check for exceptions
		println("jthrowable exc = env->ExceptionOccurred();");
		println("if(exc) {");
		incrementTabs();
		println("env->ExceptionClear();");
		println();
		for(ClsDef exc : exceptions) {
			generateExcHandler(exc);
			println();
		}
		
		println("// handle undeclared exception");
		println("jexu::JavaException cppExc;");
		println("cppExc.javaObject = static_cast<jthrowable>(env->NewGlobalRef(exc));");
		println("throw cppExc;");
		decrementTabs();
		println('}');
		
		// process return
		if(returnType != null && returnType != VoidType.getInstance()) {
			returnConverter.processreturn();
		}
		decrementTabs();
		println('}');
		println();
	}
	
	/*
	 * Example exception handler:
	 * 
	 * // check for NumberFormatException exception
	 * exceptionClass = env->FindClass("java/lang/NumberFormatException");
	 * if (exceptionClass && env->IsInstanceOf(exc, exceptionClass))
	 * {
	 *   NumberFormatException cppExc;
	 *   cppExc.javaExceptionObject = env->NewGlobalRef(exc);
	 *   throw cppExc;
	 * }
	 */
	public void generateExcHandler(ClsDef cls) {
		String clsname = cls.getName();
		printf("// check for %s exception", clsname);
		println();
		printf("exceptionClass = env->FindClass(\"%s\");", jniName(cls));
		println();
		println("if(exceptionClass && env->IsInstanceOf(exc, exceptionClass))");
		println('{');
		incrementTabs();
		printf("%s cppExc;", clsname);
		println();
		println("cppExc.javaObject = env->NewGlobalRef(exc);");
		println("throw cppExc;");
		decrementTabs();
		println('}');
	}
	
	static boolean castReturnedRequired(JNIType jniType) {
		return (jniType.isSubTypeOf(JNIType.JARRAY) ||
				jniType == JNIType.JCLASS ||
				jniType == JNIType.JSTRING ||
				jniType == JNIType.JTHROWABLE);
	}
}
