set -ex
JAVA_HOME=`/usr/libexec/java_home`
INCLUDE_JNI_FLAG=-"I$JAVA_HOME/include -I$JAVA_HOME/include/darwin"
CPPFLAGS=-g
LINK_JVM_FLAG=-framework\ JavaVM

javac Student.java
g++ $CPPFLAGS $INCLUDE_JNI_FLAG -c jni_utils.cpp
g++ $CPPFLAGS $INCLUDE_JNI_FLAG -c Student.cpp
g++ $CPPFLAGS $INCLUDE_JNI_FLAG -c main.cpp
g++ $CPPFLAGS $LINK_JVM_FLAG main.o Student.o jni_utils.o -o mainapp
