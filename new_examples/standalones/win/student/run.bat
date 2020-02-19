@echo off
setlocal
set JAVA_HOME=C:\programs\Java\jdk1.6.0_18
rem Add JVM dll to the PATH
set PATH=%JAVA_HOME%\jre\bin\client;%PATH%
rem Run the application
.\mainapp.exe
endlocal
@echo on
