package jexu.ast;

import java.util.LinkedList;
import java.util.List;

public abstract class Member implements JNode
{
	public abstract String getBaseName();

	public abstract Context getContext();
	
	public abstract void accept(jexu.visitors.MemberVisitor visitor);
	
	public List<Member> getContextPath() {
		LinkedList<Member> l = new LinkedList<Member>();
		Context con = getContext();
		if(con == null || con == DefaultPkg.getInstance()) {
			// do nothing
		}
		else if(con instanceof Member) {
			l.addAll(((Member) con).getContextPath());
		}
		else {
			throw new RuntimeException("Unknown type of context: " + con);
		}
		l.add(this);
		return l;
	}

	public List<String> getNamePath() {
		List<Member> contextPath = getContextPath();
		LinkedList<String> namePath = new LinkedList<String>();
		for(Member m : contextPath) namePath.add(m.getBaseName());
		return namePath;
	}

	public String getName() {
		return jexu.util.Utils.joinelems(".", getNamePath());
	}
}
