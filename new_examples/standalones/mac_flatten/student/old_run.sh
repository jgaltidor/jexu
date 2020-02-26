#! /usr/bin/env sh
DYLD_LIBRARY_PATH=$JAVA_HOME/jre/lib/server/:$DYLD_LIBRARY_PATH
export DYLD_LIBRARY_PATH
./mainapp
