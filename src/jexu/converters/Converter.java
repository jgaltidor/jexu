package jexu.converters;
import jexu.ast.JType;

public abstract class Converter
{
	public JType jtype;
	public String jname;
	public String cname;
	public jexu.util.TabPrintStream out;
	
	protected Converter(JType jtype) {
		this.jtype = jtype;
	}
	
	public abstract void preprocess();
	public abstract void postprocess();
	public abstract void processreturn();
	public abstract String getCppTypeName();
	public abstract boolean passingCppArg();
	public abstract String jnameSuffix();
	public abstract boolean castRequired();
}
