package jexu.ast;

public class PkgDef extends Member implements Context, JNode
{
	Package pkg;

	public PkgDef(Package pkg) { this.pkg = pkg; }
	
	public String getBaseName() { return pkg.getName(); }
	
	public String getName() { return pkg.getName(); }
	
	public Context getContext() { return null; }

	public void accept(jexu.visitors.MemberVisitor visitor) {
		visitor.visitPkgDef(this);
	}
}
