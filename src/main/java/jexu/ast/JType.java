package jexu.ast;

public interface JType extends JNode
{
	public void accept(jexu.visitors.TypeVisitor visitor);
}
