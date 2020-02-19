set -ex
JAVA_HOME=`/usr/libexec/java_home`
INCLUDE_JNI_FLAG=-"I$JAVA_HOME/include -I$JAVA_HOME/include/darwin"
CPPFLAGS=-g
LINK_JVM_FLAG=-framework\ JavaVM

javac Student.java
g++ $CPPFLAGS $INCLUDE_JNI_FLAG -Ijexu_helpers -c main.cpp
g++ $CPPFLAGS $INCLUDE_JNI_FLAG -Ijexu_helpers -c Student.cpp
cd jexu_helpers && ./build.sh; cd ..
mv jexu_helpers/jexu.a .
g++ $CPPFLAGS $LINK_JVM_FLAG main.o Student.o jexu.a -o mainapp
