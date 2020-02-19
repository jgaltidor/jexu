package jexu.ast;
import java.util.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ClsDef extends Member implements Context, JType
{
	public Class<?> cls;
	
	public ClsDef(Class<?> cls) { this.cls = cls; }
	
	public String getBaseName() { return cls.getSimpleName(); }

	public Context getContext() {
		Class<?> outerCls = cls.getDeclaringClass();
		if(outerCls != null) {
			return (ClsDef) ASTUtils.getType(outerCls);
		}
		Package pkg = cls.getPackage();
		return (pkg != null) ?
			new PkgDef(pkg) : DefaultPkg.getInstance();
	}

	public void accept(jexu.visitors.MemberVisitor visitor) {
		visitor.visitClsDef(this);
	}

	public void accept(jexu.visitors.TypeVisitor visitor) {
		visitor.visitClsDef(this);
	}

	public List<ConstructDef> getConstructors() {
		LinkedList<ConstructDef> cons = new LinkedList<ConstructDef>();
		for(Constructor<?> c : cls.getDeclaredConstructors())
			cons.add(new ConstructDef(c));
		return cons;
	}

	public List<MethDef> getMethods() {
		LinkedList<MethDef> meths = new LinkedList<MethDef>();
		for(Method m : cls.getDeclaredMethods())
			meths.add(new MethDef(m));
		return meths;
	}
	
	public List<FuncDef> getFuncs() {
		LinkedList<FuncDef> funcs = new LinkedList<FuncDef>();
		funcs.addAll(getConstructors());
		funcs.addAll(getMethods());
		return funcs;
	}
	
	
	public static void filterPublic(List<? extends FuncDef> funcs) {
		Iterator<? extends FuncDef> itr = funcs.iterator();
		while(itr.hasNext()) {
			if(!itr.next().isPublic()) itr.remove();
		}
	}
	
	public static void filterProtected(List<? extends FuncDef> funcs) {
		Iterator<? extends FuncDef> itr = funcs.iterator();
		while(itr.hasNext()) {
			if(!itr.next().isProtected()) itr.remove();
		}
	}

	public List<ConstructDef> getPublicConstructors() {
		List<ConstructDef> cons = getConstructors();
		filterPublic(cons);
		return cons;
	}

	public List<ConstructDef> getProtectedConstructors() {
		List<ConstructDef> cons = getConstructors();
		filterProtected(cons);
		return cons;
	}

	public List<MethDef> getPublicMethods() {
		List<MethDef> meths = getMethods();
		filterPublic(meths);
		return meths;
	}


	public List<MethDef> getProtectedMethods() {
		List<MethDef> meths = getMethods();
		filterProtected(meths);
		return meths;
	}


	public boolean isSubclassOf(ClsDef other) {
		return isSubclassOf(cls, other.cls);
	}

	public static boolean isSubclassOf(Class<?> cls1, Class<?> cls2) {
		try {
			cls1.asSubclass(cls2);
			return true;
		}
		catch(ClassCastException e) {
			return false;
		}
	}
	
	public boolean equals(Object other) {
		if(other instanceof ClsDef) {
			return cls.equals(((ClsDef) other).cls);
		}
		else return false;
	}
	
	public int hashCode() { return cls.hashCode(); }
	
	// Standard references to important classes
	public final static ClsDef strCls = new ClsDef(String.class);
}
