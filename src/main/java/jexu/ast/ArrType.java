package jexu.ast;

public class ArrType implements JType
{
	public JType refType;
	
	public ArrType(JType refType) { this.refType = refType; }
	
	public String getName() {
		return refType.getName() + "[]";
	}

	public void accept(jexu.visitors.TypeVisitor visitor) {
		visitor.visitArrType(this);
	}
}
