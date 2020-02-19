JAVA_HOME=/Library/Java/Home
INCLUDE_JNI_FLAG=-I$(JAVA_HOME)/include
CPPFLAGS=-g

all: jexu.a

jexu.a: JexuHelper.o JexuJavaException.o ExuCppException.o
	ar -rc jexu.a JexuHelper.o JexuJavaException.o ExuCppException.o

JexuHelper.o:
	g++ $(CPPFLAGS) -c $(INCLUDE_JNI_FLAG) JexuHelper.cpp

JexuJavaException.o:
	g++ $(CPPFLAGS) -c $(INCLUDE_JNI_FLAG) JexuJavaException.cpp

ExuCppException.o:
	g++ $(CPPFLAGS) -c ExuCppException.cpp

clean:
	rm -f JexuHelper.o JexuJavaException.o ExuCppException.o

distclean: clean
	rm -f jexu.a

