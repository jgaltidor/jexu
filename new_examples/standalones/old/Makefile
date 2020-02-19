all: mainapp Person.class

mainapp: main.o Person.o jexufuncs.o
	g++ -g -framework JavaVM main.o Person.o jexufuncs.o -o mainapp

main.o: Person.h
	g++ -g -I$(JAVA_HOME)/include -c main.cpp

Person.o: Person.cpp 
	g++ -g -I$(JAVA_HOME)/include -c Person.cpp

jexufuncs.o:
	g++ -g -I$(JAVA_HOME)/include -c jexufuncs.cpp

Person.class:
	javac Person.java

clean:
	rm -f *.o

distclean: clean
	rm -rf mainapp Person.class
