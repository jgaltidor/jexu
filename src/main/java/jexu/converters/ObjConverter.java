package jexu.converters;
import jexu.ast.*;
import static jexu.generators.GeneratorUtils.cppName;

public class ObjConverter extends Converter
{	
	public ObjConverter(ClsDef jtype) { super(jtype); }
	
	public void preprocess() {
		out.println("// Pre-Process object argument: " + cname);
		// jobject para0Obj = para0->getJavaObject();
		out.printf("jobject %s = %s->getJavaObject();", jname, cname);
		out.println();
	}
	
	// Do nothing
	public void postprocess() {}
	
	public void processreturn() {
		// return new Person(returnVal);
		out.printf("return new %s(returnVal);", cppName((ClsDef) jtype));
		out.println();
	}
	
	public boolean passingCppArg() { return false; }
	
	public String jnameSuffix() { return "Obj"; }

	public String getCppTypeName() {
		return cppName((ClsDef) jtype) + "*";
	}
	
	public boolean castRequired() { return false; }
}
