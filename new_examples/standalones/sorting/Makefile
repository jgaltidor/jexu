JAVA_HOME=/Library/Java/Home
INCLUDE_JNI_FLAG=-I$(JAVA_HOME)/include
CPPFLAGS=-g
LINK_JVM_FLAG=-framework JavaVM

all: mainapp Student.class

mainapp: main.o Student.o jexu.a
	g++ $(CPPFLAGS) $(LINK_JVM_FLAG) main.o Student.o jexu.a -o mainapp

main.o: SimpleList.h SimpleListIterator.h
	g++ $(CPPFLAGS) $(INCLUDE_JNI_FLAG) -Ijexu_helpers -c main.cpp

Student.o: Student.cpp jexu.a
	g++ $(CPPFLAGS) $(INCLUDE_JNI_FLAG) -Ijexu_helpers -c Student.cpp

jexu.a: jexu_helpers/jexu.a
	mv jexu_helpers/jexu.a .

jexu_helpers/jexu.a:
	cd jexu_helpers && make

Student.class:
	javac Student.java

clean:
	cd jexu_helpers && make clean
	rm -f *.o jexu.a

distclean: clean
	cd jexu_helpers && make distclean
	rm -f mainapp Student.class
