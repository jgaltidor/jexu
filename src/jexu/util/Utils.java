package jexu.util;
import java.util.*;
import java.io.*;

public class Utils
{	
	public static String joinelems(String sep, List<?> elems) {
		String s = "";
		Iterator<?> itr = elems.iterator();
		if(itr.hasNext()) s = itr.next().toString();
		while(itr.hasNext()) {
			s += sep + itr.next().toString();
		}
		return s;
	}
	
	public static void ensureParentDirExists(File file) {
		String parentPath = file.getParent();
		if(parentPath != null) {
			File parent = new File(parentPath);
			if(parent.isFile()) {
				throw new RuntimeException(String.format(
					"Cannot create directory %s because it is an existing file",
					parent));
			}
			if(!parent.exists()) parent.mkdirs();
		}
	}

	public static String getCurrentDateTimeStr() {
		return String.format("%1$ta %1$tb %1$td %1$tT", new java.util.Date());
	}
}
