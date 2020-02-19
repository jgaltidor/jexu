package jexu.converters;
import jexu.ast.PrimType;

public class PrimitiveConverter extends Converter
{	
	public PrimitiveConverter(PrimType jtype) { super(jtype); }

	public void preprocess() {} // nothing

	public void postprocess() {} // nothing

	public void processreturn() {}
	
	public boolean passingCppArg() { return true; }
	
	public String jnameSuffix() {
		throw new UnsupportedOperationException();
	}

	public String getCppTypeName() {
		switch((PrimType) jtype) {
			case INT:     return "int";
			case SHORT:   return "short";
			case LONG:    return "long";
			case FLOAT:   return "float";
			case DOUBLE:  return "double";
			case BOOLEAN: return "bool";
			case BYTE:    return "signed char";
			case CHAR:    return "char";
		}
		throw new AssertionError("Unknown primitive type: " + jtype);
	}
	
	public boolean castRequired() { return true; }
}
