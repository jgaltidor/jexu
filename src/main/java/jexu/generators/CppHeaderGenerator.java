package jexu.generators;
import java.util.*;
import java.io.File;
import jexu.ast.*;
import jexu.util.*;
import static jexu.generators.GeneratorUtils.*;
import static jexu.util.Utils.*;
import jexu.converters.Converter;

public class CppHeaderGenerator extends TabPrintWrapper
{	
	public void generateHeaderFile(Class<?> cls) {
		generateHeaderFile((ClsDef) jexu.ast.ASTUtils.getType(cls));
	}

	public void generateHeaderFile(ClsDef cls) {
		File headerFile = getHeaderFile(cls);
		ensureParentDirExists(headerFile);
		System.out.println("Generating header file: " + headerFile);
		try {
			out = new TabPrintStream(headerFile, "  ");
		}
		catch(java.io.FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		println(commentLine);
		println("// Generated on: " + getCurrentDateTimeStr());
		println("// Header of proxy class for Java class: " + cls.getName());
		println(commentLine);
		
		String macro = getClsMacro(cls);
		println("#ifndef " + macro);
		println("#define " + macro);
		println("#include \"jexulib.h\"");
		println();
		processCls(cls);
		println("#endif");
		out.close();
		System.out.println("completed generation of header file: "
				+ headerFile.getName());
	}
	
	public void processCls(ClsDef cls) {
		classesGenerated.add(cls);
		Context con = cls.getContext();
		String[] namespaces = null;
		if(con instanceof PkgDef) {
			namespaces = ((PkgDef) con).getName().split("\\.");
			for(String ns : namespaces) {
				printf("namespace %s {", ns);
				println();
			}
			println();
		}
		printf("class %s : public jexu::JavaBaseClass {", cls.getBaseName());
		println();
		println("public:");
		incrementTabs();
		printf("%s(jobject);", cls.getBaseName());
		println();
		
		// process members
		for(ConstructDef c : cls.getPublicConstructors())
			processFunction(c);
		for(ConstructDef c : cls.getProtectedConstructors())
			processFunction(c);
		for(MethDef m : cls.getPublicMethods()) processFunction(m);
		for(MethDef m : cls.getProtectedMethods()) processFunction(m);
		
		decrementTabs();
		println("};");
		if(namespaces != null) {
			for(int i = 0; i < namespaces.length; i++) print('}');
			println(" // closing namespace context");
		}
	}

	public void processFunction(FuncDef func) {
		List<Decl> parameters = func.getParameters();
		List<ClsDef> exceptions = func.getExceptions();
		// Added parameter classes and exception classes to queue of
		// classes to generate
		addClassesToGenerateFromDeclList(parameters);
		addClassesToGenerateFromClsList(exceptions);
		
		
		Vector<Converter> converters = new Vector<Converter>();
		for(Decl decl : parameters) {
			Converter conv = type2converter(decl.typ);
			converters.add(conv);
		}
		JType returnType = null;
		Converter returnConverter = null;
		if(func instanceof MethDef) {
			MethDef meth = (MethDef) func;
			returnType = meth.getReturnType();
			if(returnType != VoidType.getInstance()) {
				returnConverter = type2converter(returnType);
			}
		}
		
		// function signature
		if(func instanceof MethDef) {
			MethDef meth = (MethDef) func;
			if(meth.isStatic()) {
				print("static ");
			}
			if(returnType == VoidType.getInstance()) {
				print("void ");
			}
			else {
				print(returnConverter.getCppTypeName() + ' ');
			}
		}
		print(func.getBaseName());
		print('(');

		// argument type declarations
		Iterator<Converter> itr = converters.iterator();
		while(itr.hasNext()) {
			Converter conv = itr.next();
			print(conv.getCppTypeName());
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
		println(';');
	}
}
