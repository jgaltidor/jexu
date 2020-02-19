JAVA_HOME=/exp/rcf/share/X11R5/jdk
INCLUDE_JNI_FLAG=-I$JAVA_HOME/include\ -I$JAVA_HOME/include/linux
CPPFLAGS=-g

g++ $CPPFLAGS -c $INCLUDE_JNI_FLAG JexuHelper.cpp
g++ $CPPFLAGS -c $INCLUDE_JNI_FLAG JexuJavaException.cpp
g++ $CPPFLAGS -c ExuCppException.cpp
ar -rc jexu.a JexuHelper.o JexuJavaException.o ExuCppException.o
