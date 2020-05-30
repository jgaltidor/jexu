package jexu.ast;

/** Singleton Class */
public class DefaultPkg implements Context
{
	private DefaultPkg() {}
	
	private static DefaultPkg theInstance = new DefaultPkg();
	
	public static DefaultPkg getInstance() {
		return theInstance;
	}
}
