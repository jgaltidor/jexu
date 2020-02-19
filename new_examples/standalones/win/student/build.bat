setlocal
set JAVA_HOME=C:\programs\Java\jdk1.6.0_18
set INCLUDE_JNI_FLAG=-I%JAVA_HOME%\include -I%JAVA_HOME%\include\win32
set CPPFLAGS=/EHsc
set LINK_JVM_FLAG="%JAVA_HOME%\lib\jvm.lib"

rem Building Student.class
javac Student.java
rem Building main.obj
cl %CPPFLAGS% %INCLUDE_JNI_FLAG% -Ijexu_helpers -c main.cpp
rem Building Student.obj
cl %CPPFLAGS% %INCLUDE_JNI_FLAG% -Ijexu_helpers -c Student.cpp
rem Building jexu.lib
cd jexu_helpers && call build & cd ..
move jexu_helpers\jexu.lib
rem Building mainapp.exe
cl %CPPFLAGS% %LINK_JVM_FLAG% jexu.lib main.obj Student.obj -Femainapp.exe
endlocal
