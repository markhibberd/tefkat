#!/bin/sh

CLASSES=tefkat/WEB-INF/classes
ROOT=..
LN="ln -sf"
LN="cp -r"

$LN $ROOT/com.dstc.tefkat.engine/runtime/com $CLASSES
$LN $ROOT/com.dstc.tefkat.model/runtime/com $CLASSES
$LN $ROOT/com.dstc.tefkat.model.parser/runtime/com $CLASSES
#$LN $ROOT/AntiYacc/runtime/AntiYacc $CLASSES
#$LN $ROOT/AntiYacc/runtime/AntiYaccGrammar $CLASSES

