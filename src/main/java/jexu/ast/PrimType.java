package jexu.ast;

public enum PrimType implements JType
{
	INT,
	SHORT,
	LONG,
	FLOAT,
	DOUBLE,
	BOOLEAN,
	BYTE,
	CHAR;
	
	public String getName() { return toString().toLowerCase(); }

	public void accept(jexu.visitors.TypeVisitor visitor) {
		visitor.visitPrimType(this);
	}
}
