#!/bin/sh

TEFKAT_VERSION=2.1.0.1
TEFKAT_JAR=Tefkat_$TEFKAT_VERSION.jar

PATH="/dstc/stow/j2sdk1.4.2/bin:$PATH"

JARS="lib/jgraph.jar lib/antlr.jar lib/ecore.jar lib/ecore.xmi.jar lib/common.jar"
export JARS

ECLIPSE_DIR=/dstc/eclipse3.1/plugins
EMF_VERSION=2.1.0

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
    $ECLIPSE_DIR/org.antlr_2.7.6.b2/antlr.jar \
    $ECLIPSE_DIR/org.eclipse.emf.ecore_$EMF_VERSION.jar \
    $ECLIPSE_DIR/org.eclipse.emf.ecore.xmi_$EMF_VERSION.jar \
    $ECLIPSE_DIR/org.eclipse.emf.common_$EMF_VERSION.jar \
    lib

cat > /tmp/wrap.manifest.mf <<EOF
Wrap-Class-Loader: com.simontuffs.onejar.ExternalClassLoader
EOF

jar -cfm wrap/wraploader.jar /tmp/wrap.manifest.mf com
jar -uvf wrap/wraploader.jar com

test -d onejar || mkdir onejar
cd onejar
jar -xf ../one-jar-boot-0.95.jar
cd ..

jar -cfm $TEFKAT_JAR onejar/boot-manifest.mf \
    -C onejar com \
    -C onejar doc \
    main/tefkat.jar \
    wrap/wraploader.jar \
    lib/*.jar

# cleanup
## /bin/rm -rf main lib onejar

