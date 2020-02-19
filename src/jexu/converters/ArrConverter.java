package jexu.converters;
import jexu.ast.*;

public class ArrConverter extends Converter
{	
	public ArrConverter(ArrType jtype) { super(jtype); }
	
	public void preprocess() {
		throw new UnsupportedOperationException();
	}

	public void postprocess() {
		throw new UnsupportedOperationException();
	}

	public void processreturn() {
		throw new UnsupportedOperationException();
	}
	
	public String getCppTypeName() {
		throw new UnsupportedOperationException();
	}

	public boolean passingCppArg() { return false; }

	public String jnameSuffix() { return "Arr"; }
	
	public boolean castRequired() { return false; }
}
