JAVA_HOME=/exp/rcf/share/X11R5/jdk
INCLUDE_JNI_FLAG=-I$JAVA_HOME/include\ -I$JAVA_HOME/include/linux
CPPFLAGS=-g
LINK_JVM_FLAG=$JAVA_HOME/jre/lib/i386/client/libjvm.so

javac Student.java
g++ $CPPFLAGS $INCLUDE_JNI_FLAG -Ijexu_helpers -c main.cpp
g++ $CPPFLAGS $INCLUDE_JNI_FLAG -Ijexu_helpers -c Student.cpp
cd jexu_helpers && ./build.sh; cd ..
mv jexu_helpers/jexu.a .
g++ $CPPFLAGS $LINK_JVM_FLAG main.o Student.o jexu.a -o mainapp
