package jexu.ast;
import java.util.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import static jexu.ast.ASTUtils.*;

public class ConstructDef extends FuncDef
{
	Constructor<?> con;

	public ConstructDef(Constructor<?> con) {
		this.con = con;
	}

	public String getBaseName() {
		return con.getDeclaringClass().getSimpleName();
	}

	public ClsDef getContext() {
		return (ClsDef) getType(con.getDeclaringClass());
	}

	public void accept(jexu.visitors.MemberVisitor visitor) {
		visitor.visitConstructDef(this);
	}

	public boolean isPublic() {
		return Modifier.isPublic(con.getModifiers());
	}

	public boolean isProtected() {
		return Modifier.isProtected(con.getModifiers());
	}

	public List<Decl> getParameters() {
		LinkedList<Decl> decls = new LinkedList<Decl>();
		for(Class<?> c : con.getParameterTypes()) {
			decls.add(new Decl(getType(c)));
		}
		return decls;
	}

	public List<ClsDef> getExceptions() {
		LinkedList<ClsDef> types = new LinkedList<ClsDef>();
		for(Class<?> c : con.getExceptionTypes()) {
			types.add((ClsDef) getType(c));
		}
		return types;
	}
}
