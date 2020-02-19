package jexu.ui;
import jexu.generators.*;
import jexu.ast.ClsDef;
import static jexu.generators.GeneratorUtils.classesGenerated;
import static jexu.generators.GeneratorUtils.classesToGenerate;

public class MainApp
{
	public static void main(String args[]) {
		if(args.length < 1) {
			System.err.println("usage: java -jar jexu.jar <class name>");
			System.exit(1);
		}
		String className = args[0];
		CppHeaderGenerator headerGen = new CppHeaderGenerator();
		CppImplGenerator implGen = new CppImplGenerator();
		Class<?> cls = null;
		try {
			cls = Class.forName(className);
		}
		catch(ClassNotFoundException e) {
			System.err.println("ERROR: Class not found: " + className);
			System.exit(1);
		}
		ClsDef clsdef = new ClsDef(cls);
		if(clsdef.getContext() instanceof ClsDef) {
			System.err.println("ERROR: Input class is an inner or nested class: " + className);
			System.exit(1);
		}
		// add classes to not generate to classesGenerated
		GeneratorUtils.addClassesToSkip(classesGenerated);
		
		GeneratorUtils.registerClass(clsdef);
		
		while((clsdef = classesToGenerate.poll()) != null) {
			if(!classesGenerated.contains(clsdef)) {
				headerGen.generateHeaderFile(clsdef);
				implGen.generateImplFile(clsdef);
			}
		}
	}
}
