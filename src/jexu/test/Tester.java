package jexu.test;
import jexu.generators.CppHeaderGenerator;
import jexu.generators.CppImplGenerator;

public class Tester
{
	public static void main(String[] args) throws Exception {
		/*
		if(args.length < 1) {
			System.err.println("usage: java jexu.Tester <class name>");
			System.exit(1);
		}
		String className = args[0];
		*/
		String className = "examples.Person";
		CppHeaderGenerator headerGen = new CppHeaderGenerator();
		CppImplGenerator implGen = new CppImplGenerator();
		Class<?> cls = Class.forName(className);
		headerGen.generateHeaderFile(cls);
		implGen.generateImplFile(cls);
	}
}
