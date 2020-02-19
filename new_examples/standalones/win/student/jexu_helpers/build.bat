setlocal
set JAVA_HOME=C:\programs\Java\jdk1.6.0_18
set INCLUDE_JNI_FLAG=-I%JAVA_HOME%\include -I%JAVA_HOME%\include\win32
set CPPFLAGS=/EHsc

cl %CPPFLAGS% -c %INCLUDE_JNI_FLAG% JexuHelper.cpp
cl %CPPFLAGS% -c %INCLUDE_JNI_FLAG% JexuJavaException.cpp
cl %CPPFLAGS% -c ExuCppException.cpp
lib -out:jexu.lib JexuHelper.obj JexuJavaException.obj ExuCppException.obj
endlocal
