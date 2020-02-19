package jexu.util;

public class TabPrintWrapper {
	public TabPrintStream out;

	public void printf(String s, Object... args) {
		out.printf(s, args);
	}

    public void print(boolean b) { out.print(b); }
    public void print(char c) { out.print(c); }
    public void print(char[] s) { out.print(s); }
    public void print(double d) { out.print(d); }
    public void print(float f) { out.print(f); }
    public void print(int i) { out.print(i); }
    public void print(long l) { out.print(l); }
	public void print(Object o) { out.print(o); }
	
	public void println() { out.println(); }
    public void println(boolean b) { out.println(b); }
    public void println(char c) { out.println(c); }
    public void println(char[] s) { out.println(s); }
    public void println(double d) { out.println(d); }
    public void println(float f) { out.println(f); }
    public void println(int i) { out.println(i); }
    public void println(long l) { out.println(l); }
	public void println(Object o) { out.println(o); }
	
	public void incrementTabs() { out.incrementTabs(); }
	public void decrementTabs() { out.decrementTabs(); }
}
