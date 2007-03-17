#!/bin/sh

TEFKAT_VERSION=2.1.0.`date "+%Y%m%d%H%M"`
TEFKAT_JAR=Tefkat_$TEFKAT_VERSION.jar

PATH="/dstc/stow/j2sdk1.4.2/bin:$PATH"

JARS="lib/jgraph.jar lib/antlr.jar lib/ecore.jar lib/ecore.xmi.jar lib/common.jar"
export JARS

ECLIPSE_DIR=/dstc/eclipse3.1/plugins
EMF_VERSION=2.1.0

ECLIPSE_DIR=/Applications/eclipse-SDK-3.2/plugins
EMF_VERSION=2.2.0.v200606271057
EMF_VERSION=2.2.?.v200609210005

BUILD_BASE=..

# BUILD_BASE=~/eclipse.build/plugins

cat > /tmp/manifest.mf <<EOF
Manifest-Version: 1.0
Main-Class: tefkat.engine.Main
Class-Path: main/tefkat.jar lib/antlr.jar lib/jgraph.jar lib/org.eclipse.emf.ecore_$EMF_VERSION.jar lib/org.eclipse.emf.ecore.xmi_$EMF_VERSION.jar lib/org.eclipse.emf.common_$EMF_VERSION.jar
EOF

test -d main || mkdir main

jar -cfm main/tefkat.jar /tmp/manifest.mf
for BD in runtime bin @dot
do
  for PROJ in tefkat.engine tefkat.config tefkat.model tefkat.model.parser
  do
    test -d $BUILD_BASE/$PROJ/$BD && jar -uf main/tefkat.jar -C $BUILD_BASE/$PROJ/$BD tefkat
  done
done

#jar -uf main/tefkat.jar \
#    -C $BUILD_BASE/tefkat.engine/$BUILD_DIR tefkat \
#    -C $BUILD_BASE/tefkat.config/$BUILD_DIR tefkat \
#    -C $BUILD_BASE/tefkat.model/$BUILD_DIR tefkat \
#    -C $BUILD_BASE/tefkat.model.parser/$BUILD_DIR tefkat

test -d lib || mkdir lib
cp -f \
    ../tefkat.engine/jgraph.jar \
    $ECLIPSE_DIR/org.antlr_2.7.6/antlr.jar \
    $ECLIPSE_DIR/org.eclipse.emf.ecore_$EMF_VERSION.jar \
    $ECLIPSE_DIR/org.eclipse.emf.ecore.xmi_$EMF_VERSION.jar \
    $ECLIPSE_DIR/org.eclipse.emf.common_$EMF_VERSION.jar \
    $ECLIPSE_DIR/org.eclipse.xsd.ecore.exporter_$EMF_VERSION.jar \
    $ECLIPSE_DIR/org.eclipse.xsd.ecore.importer_$EMF_VERSION.jar \
    $ECLIPSE_DIR/org.eclipse.xsd_$EMF_VERSION.jar \
    lib

#cat > /tmp/wrap.manifest.mf <<EOF
#Wrap-Class-Loader: com.simontuffs.onejar.ExternalClassLoader
#EOF
#
#test -d wrap || mkdir wrap
#jar -cfm wrap/wraploader.jar /tmp/wrap.manifest.mf com
#jar -uvf wrap/wraploader.jar com

test -d onejar || mkdir onejar
cd onejar
jar -xf ../one-jar-boot-0.95-lawley.jar
cd ..

jar -cvfm $TEFKAT_JAR onejar/boot-manifest.mf \
    -C onejar com \
    -C onejar doc \
    main/tefkat.jar \
    lib/*.jar \

#    wrap/wraploader.jar

# cleanup
##/bin/rm -rf main lib onejar wrap

