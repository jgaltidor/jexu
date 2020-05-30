package jexu.ast;
import java.util.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import static jexu.ast.ASTUtils.*;

public class MethDef extends FuncDef
{
	Method method;

	public MethDef(Method method) { this.method = method; }
	
	public String getBaseName() {
		return method.getName();
	}
	
	public ClsDef getContext() {
		return (ClsDef) getType(method.getDeclaringClass());
	}

	public void accept(jexu.visitors.MemberVisitor visitor) {
		visitor.visitMethDef(this);
	}

	public boolean isPublic() {
		return Modifier.isPublic(method.getModifiers());
	}

	public boolean isProtected() {
		return Modifier.isProtected(method.getModifiers());
	}
	
	public boolean isStatic() {
		return Modifier.isStatic(method.getModifiers());
	}

	public List<Decl> getParameters() {
		LinkedList<Decl> decls = new LinkedList<Decl>();
		for(Class<?> c : method.getParameterTypes()) {
			decls.add(new Decl(getType(c)));
		}
		return decls;
	}

	public List<ClsDef> getExceptions() {
		LinkedList<ClsDef> types = new LinkedList<ClsDef>();
		for(Class<?> c : method.getExceptionTypes()) {
			types.add((ClsDef) getType(c));
		}
		return types;
	}

	public JType getReturnType() {
		return getType(method.getReturnType());
	}
}
