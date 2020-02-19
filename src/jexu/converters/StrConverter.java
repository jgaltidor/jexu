package jexu.converters;
import jexu.ast.*;

public class StrConverter extends Converter
{
	public StrConverter() { super(ClsDef.strCls); }
	
	public void preprocess() {
		out.println("// Pre-Process string argument: " + cname);
		out.printf("jstring %s = env->NewStringUTF(%s);",
			jname, cname);
		out.println();
	}

	public void postprocess() {} // nothing

	// Convert jstring back to char*
	public void processreturn() {
		out.println("return (char*) env->GetStringUTFChars(returnVal, NULL);");
	}
	
	public boolean passingCppArg() { return false; }
	
	public String jnameSuffix() { return "Str"; }
	
	public String getCppTypeName() { return "char*"; }
	
	public boolean castRequired() { return true; }
}
