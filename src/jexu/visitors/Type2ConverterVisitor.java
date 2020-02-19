package jexu.visitors;
import jexu.ast.*;
import jexu.converters.*;

public class Type2ConverterVisitor implements TypeVisitor
{
	public Converter result = null;
	
	public void visitPrimType(PrimType typ) {
		result = new PrimitiveConverter(typ);
	}

	public void visitClsDef(ClsDef clsdef) {
		if(clsdef.cls.equals(String.class)) {
			result = new StrConverter();
		}
		else {
			result = new ObjConverter(clsdef);
		}
	}

	public void visitArrType(ArrType typ) {
		result = null;
	}

	public void visitVoidType(VoidType typ) {
		result = null;
	}
}
