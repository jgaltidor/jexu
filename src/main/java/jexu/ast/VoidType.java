package jexu.ast;

public class VoidType implements JType
{
	private VoidType() {}
	
	private static VoidType theInstance = new VoidType();
	
	public static VoidType getInstance() {
		return theInstance;
	}
	
	public String getName() { return "void"; }

	public void accept(jexu.visitors.TypeVisitor visitor) {
		visitor.visitVoidType(this);
	}
}
