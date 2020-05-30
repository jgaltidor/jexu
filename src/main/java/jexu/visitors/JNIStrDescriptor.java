package jexu.visitors;
import jexu.ast.*;

public class JNIStrDescriptor implements TypeVisitor {

	public String descriptor = null;

	public void visitClsDef(ClsDef cls) {
		descriptor = "L" + jexu.generators.GeneratorUtils.jniName(cls) + ";";
	}

	public void visitPrimType(PrimType typ) {
		switch(typ) {
			case INT:      descriptor = "I"; break;
			case SHORT:    descriptor = "S"; break;
			case LONG:     descriptor = "J"; break;
			case FLOAT:    descriptor = "G"; break;
			case DOUBLE:   descriptor = "D"; break;
			case BOOLEAN:  descriptor = "Z"; break;
			case BYTE:     descriptor = "B"; break;
			case CHAR:     descriptor = "C"; break;
		}
	}
	
	public void visitArrType(ArrType typ) {
		typ.refType.accept(this);
		descriptor = "[" + descriptor;
	}

	public void visitVoidType(VoidType typ) {
		descriptor = "V";
	}
}
