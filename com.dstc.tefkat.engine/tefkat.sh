#!/bin/sh

if [ -z "$JAVA_HOME" ]
then
    JAVA_HOME=/usr/java/j2sdk1.4.1_02
    export JAVA_HOME
fi

ECLIPSE_OSX=/Applications/eclipse-SDK-3.0
ECLIPSE_DSTC=/dstc/eclipse3.1
if [ -d $ECLIPSE_OSX ]
then
  ECLIPSE=$ECLIPSE_OSX
  JAVA_HOME=/Library/Java/Home
else
  ECLIPSE=$ECLIPSE_DSTC
fi
EMF_VERSION=2.1.0

DIR=`dirname $0`

PATH=$JAVA_HOME/bin:$PATH
export PATH

MAIN=com.dstc.tefkat.engine.Main

LOGGING_JAR=log4j-1_2_8.jar
LOGGING_JAR=

MODELS=http://www.dstc.edu.au:8080/qvt/models
CLASSPATH=$DIR/jgraph.jar:$DIR/runtime:$DIR/../com.dstc.tefkat.config/runtime:$DIR/../com.dstc.tefkat.model/runtime:$DIR/../com.dstc.tefkat.model.parser/runtime:$ECLIPSE/plugins/org.eclipse.emf.ecore_$EMF_VERSION.jar:$ECLIPSE/plugins/org.eclipse.emf.ecore.xmi_$EMF_VERSION.jar:$ECLIPSE/plugins/org.eclipse.emf.common_$EMF_VERSION.jar:"$LOGGING_JAR":$ECLIPSE/plugins/org.antlr_2.7.6.b2/antlr.jar

cd $DIR

java -classpath $CLASSPATH $MAIN $@

# java -classpath $CLASSPATH $MAIN $@ $MODELS/abc2xyz.qvt $MODELS/ABCInstance2.xmi
# java -classpath $CLASSPATH $MAIN $@ example.qvt $MODELS/UMLInstance2.simpleuml result.xmi
# java -classpath $CLASSPATH $MAIN $@ /home/lawley/workspace-pc/TestEMF/src/transforms/pkg2pkg.qvt $MODELS/UMLInstance2.simpleuml

