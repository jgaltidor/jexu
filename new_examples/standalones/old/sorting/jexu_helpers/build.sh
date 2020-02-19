JAVA_HOME=/Library/Java/Home
INCLUDE_JNI_FLAG=-I$JAVA_HOME/include
CPPFLAGS=-g

g++ $CPPFLAGS -c $INCLUDE_JNI_FLAG JexuHelper.cpp
g++ $CPPFLAGS -c $INCLUDE_JNI_FLAG JexuJavaException.cpp
g++ $CPPFLAGS -c ExuCppException.cpp
ar -rc jexu.a JexuHelper.o JexuJavaException.o ExuCppException.o
