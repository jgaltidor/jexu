package jexu.ast;

public class ASTUtils
{
	public static JType getType(Class<?> cls) {
		String name = cls.getName();
		
		// check for void type
		if(name.equals("void")) return VoidType.getInstance();
		
		// check for primitive types
		if(name.equals("int"))     return PrimType.INT;
		if(name.equals("short"))   return PrimType.SHORT;
		if(name.equals("long"))    return PrimType.LONG;
		if(name.equals("float"))   return PrimType.FLOAT;
		if(name.equals("double"))  return PrimType.DOUBLE;
		if(name.equals("boolean")) return PrimType.BOOLEAN;
		if(name.equals("byte"))    return PrimType.BYTE;
		if(name.equals("char"))    return PrimType.CHAR;

		// check for array types
		if(name.charAt(0) == '[') {
			int i = 1;
			while(name.charAt(i) == '[') i++;
			JType elemType = getType(name.substring(i));
			ArrType arrType = new ArrType(elemType);
			for(int j = 0; j < i; j++) {
				arrType = new ArrType(arrType);
			}
			return arrType;
		}
		
		// at this point, it must be a class or interface
		return new ClsDef(cls);
	}
	
	/** Returns the corresponding non-array type according
	  * to the encoding of element types as described in the
	  * Java doc for the method Class.getName(), repeated
	  * here quick lookup:
    *   boolean   Z
    *   byte      B
    *   char      C
    *   class or interface 	L<classname>;
    *   double    D
    *   float     F
    *   int       I
    *   long      J
    *   short     S 
    *
    * The class or interface name <classname> is the binary name
    * of the class specified above. 
	  */
	public static JType getType(String encoding) {
		switch(encoding.charAt(0)) {
			case 'Z': return PrimType.BOOLEAN;
			case 'B': return PrimType.BYTE;
			case 'C': return PrimType.CHAR;
			case 'D': return PrimType.DOUBLE;
			case 'F': return PrimType.FLOAT;
			case 'I': return PrimType.INT;
			case 'J': return PrimType.LONG;
			case 'S': return PrimType.SHORT;
			case 'L':
				String binaryClassName =
					encoding.substring(1, encoding.length()-1);
				try {
					return new ClsDef(Class.forName(binaryClassName));
				}
				catch (ClassNotFoundException e) {
					throw new RuntimeException(e);
				}
			default:
				throw new AssertionError("Unknown type of encoding: " + encoding);
		}
	}
}
