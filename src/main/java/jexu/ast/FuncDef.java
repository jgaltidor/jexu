package jexu.ast;
import java.util.List;

public abstract class FuncDef extends Member
{
	public abstract boolean isPublic();
	public abstract boolean isProtected();
	public abstract List<Decl> getParameters();
	public abstract List<ClsDef> getExceptions();
	public abstract ClsDef getContext();
}
