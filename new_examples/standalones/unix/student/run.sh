#! /usr/bin/env sh
JAVA_HOME=/exp/rcf/share/X11R5/jdk
LD_LIBRARY_PATH=$JAVA_HOME/jre/lib/i386/client:$LD_LIBRARY_PATH
export LD_LIBRARY_PATH
./mainapp
