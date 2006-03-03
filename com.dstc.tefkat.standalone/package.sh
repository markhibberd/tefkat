#!/bin/sh

TEFKAT_VERSION=2.0.9.001
TEFKAT_JAR=Tefkat_$TEFKAT_VERSION.jar

PATH="/dstc/stow/j2sdk1.4.2/bin:$PATH"

JARS="lib/jgraph.jar lib/antlr.jar lib/ecore.jar lib/ecore.xmi.jar lib/common.jar"
export JARS

ECLIPSE_DIR=/dstc/eclipse3/plugins
EMF_VERSION=2.0.0

cat > /tmp/manifest.mf <<EOF
Manifest-Version: 1.0
Main-Class: com.dstc.tefkat.engine.Main
EOF

test -d main || mkdir main

jar -cfm main/tefkat.jar /tmp/manifest.mf
jar -uf main/tefkat.jar \
    -C ../com.dstc.tefkat.engine/runtime com \
    -C ../com.dstc.tefkat.config/runtime com \
    -C ../com.dstc.tefkat.model/runtime com \
    -C ../com.dstc.tefkat.model.parser/runtime com

test -d lib || mkdir lib
cp -f \
    ../com.dstc.tefkat.engine/jgraph.jar \
    $ECLIPSE_DIR/org.antlr_2.7.4/antlr.jar \
    $ECLIPSE_DIR/org.eclipse.emf.ecore_$EMF_VERSION/runtime/ecore.jar \
    $ECLIPSE_DIR/org.eclipse.emf.ecore.xmi_$EMF_VERSION/runtime/ecore.xmi.jar \
    $ECLIPSE_DIR/org.eclipse.emf.common_$EMF_VERSION/runtime/common.jar \
    lib

test -d onejar || mkdir onejar
cd onejar
jar -xf ../one-jar-boot-0.95.jar
cd ..

jar -cfm $TEFKAT_JAR onejar/boot-manifest.mf \
    -C onejar com \
    -C onejar doc \
    main/tefkat.jar \
    lib/*.jar

# cleanup
/bin/rm -rf main lib onejar

