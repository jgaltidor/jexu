package jexu.ast;

public class Decl
{
	public JType typ;
	public String name;
	
	public Decl(JType typ, String name) {
		this.typ = typ;
		this.name = name;
	}
	
	public Decl(JType typ) { this(typ, null); }
}
