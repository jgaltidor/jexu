JAVA_HOME=/Library/Java/Home
INCLUDE_JNI_FLAG=-I$(JAVA_HOME)/include
CPPFLAGS=-g
LINK_JVM_FLAG=-framework JavaVM

all: mainapp

mainapp: client.o Person.o jexulib.o
	g++ $(CPPFLAGS) $(LINK_JVM_FLAG) client.o Person.o jexulib.o -o mainapp

client.o: client.cpp Person.h
	g++ $(CPPFLAGS) $(INCLUDE_JNI_FLAG) -c client.cpp

Person.o: Person.cpp Person.h
	g++ $(CPPFLAGS) $(INCLUDE_JNI_FLAG) -c Person.cpp

jexulib.o: jexulib.cpp jexulib.h
	g++ $(CPPFLAGS) $(INCLUDE_JNI_FLAG) -c jexulib.cpp

clean:
	rm -f *.o

distclean: clean
	rm -f mainapp
