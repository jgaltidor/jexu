package jexu.visitors;
import jexu.ast.*;

public interface TypeVisitor
{
	public void visitClsDef(ClsDef cls);
	public void visitArrType(ArrType typ);
	public void visitPrimType(PrimType typ);
	public void visitVoidType(VoidType typ);
}
