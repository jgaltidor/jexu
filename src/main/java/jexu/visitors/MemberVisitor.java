package jexu.visitors;
import jexu.ast.*;

public interface MemberVisitor
{
	public void visitClsDef(ClsDef cls);
	public void visitMethDef(MethDef meth);
	public void visitConstructDef(ConstructDef con);
	public void visitPkgDef(PkgDef pkg);
	
}
