
ifndef BRANCH
BRANCH=lawley
endif

ifdef REVISION
ifneq (${REVISION},HEAD)
COMP_FLAGS=-DforceContextQualifier=${BRANCH}${REVISION} -DbuildLabel=${BRANCH}${REVISION} -DbuildId=${BRANCH}${REVISION}
endif
else
REVISION=HEAD
endif

BOOTCLASSPATH=-Dbootclasspath=/System/Library/Frameworks/JavaVM.framework/Versions/1.5.0/Home/../Classes/charsets.jar:/System/Library/Frameworks/JavaVM.framework/Versions/1.5.0/Home/../Classes/classes.jar:/System/Library/Frameworks/JavaVM.framework/Versions/1.5.0/Home/../Classes/dt.jar:/System/Library/Frameworks/JavaVM.framework/Versions/1.5.0/Home/../Classes/jce.jar:/System/Library/Frameworks/JavaVM.framework/Versions/1.5.0/Home/../Classes/jconsole.jar:/System/Library/Frameworks/JavaVM.framework/Versions/1.5.0/Home/../Classes/jsse.jar:/System/Library/Frameworks/JavaVM.framework/Versions/1.5.0/Home/../Classes/laf.jar:/System/Library/Frameworks/JavaVM.framework/Versions/1.5.0/Home/../Classes/ui.jar

default: build

init:
	/bin/rm -rf ~/eclipse.build
	mkdir -p ~/eclipse.build/plugins
	mkdir -p ~/eclipse.build/features

source: \
	plugins/tefkat.config \
	plugins/tefkat.config.edit \
	plugins/tefkat.config.editor \
	plugins/tefkat.model \
	plugins/tefkat.model.edit \
	plugins/tefkat.model.editor \
	plugins/tefkat.model.parser \
	plugins/tefkat.engine \
	plugins/tefkat.plugin \
	features/tefkat.feature \
	runAntlr

runAntlr:
	(cd ~/eclipse.build/plugins/tefkat.model.parser/src/tefkat/model/parser ; java -cp /Applications/eclipse-SDK-3.2/plugins/org.antlr_2.7.6/antlr.jar antlr.Tool tefkat.g)

build: init source compile

compile:
	java -cp /Applications/eclipse-SDK-3.2/startup.jar org.eclipse.core.launcher.Main -application org.eclipse.pde.build.Build -Dbase=/Applications/eclipse-SDK-3.2 -DbaseLocation=/Applications/eclipse-SDK-3.2 ${BOOTCLASSPATH} ${COMP_FLAGS}

vcompile:
	java -cp /Applications/eclipse-SDK-3.2/startup.jar org.eclipse.core.launcher.Main -application org.eclipse.pde.build.Build -Dbase=/Applications/eclipse-SDK-3.2 -DbaseLocation=/Applications/eclipse-SDK-3.2 ${BOOTCLASSPATH} -verbose ${COMP_FLAGS}

plugins/tefkat.%: ../tefkat.%
	/bin/rm -rf ~/eclipse.build/$@
	svn export -r${REVISION} $< ~/eclipse.build/$@

features/tefkat.%: ../tefkat.%
	/bin/rm -rf ~/eclipse.build/$@
	svn export -r${REVISION} $< ~/eclipse.build/$@
