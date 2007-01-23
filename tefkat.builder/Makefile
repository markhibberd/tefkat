
E_HOME=/Applications/eclipse-SDK-3.2
E_HOME=/Applications/eclipse

E_PLUGINS=${E_HOME}

#  Need to be smarter about launching Eclipse (see compile target) when there's and extra plugin dir
#
## E_PLUGINS=${HOME}/Documents/Eclipse-Plugins/eclipse

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
	(cd ~/eclipse.build/plugins/tefkat.model.parser/src/tefkat/model/parser ; java -cp ${E_PLUGINS}/plugins/org.antlr_2.7.6/antlr.jar antlr.Tool tefkat.g)

build: init source compile

compile:
	java -cp ${E_HOME}/startup.jar org.eclipse.core.launcher.Main -application org.eclipse.pde.build.Build -Dbase=${E_HOME} -DbaseLocation=${E_HOME} ${BOOTCLASSPATH} ${COMP_FLAGS}

vcompile:
	java -cp ${E_HOME}/startup.jar org.eclipse.core.launcher.Main -application org.eclipse.pde.build.Build -Dbase=${E_HOME} -DbaseLocation=${E_HOME} ${BOOTCLASSPATH} -verbose ${COMP_FLAGS}

plugins/tefkat.%: ../tefkat.%
	/bin/rm -rf ~/eclipse.build/$@
	svn export -r${REVISION} $< ~/eclipse.build/$@

features/tefkat.%: ../tefkat.%
	/bin/rm -rf ~/eclipse.build/$@
	svn export -r${REVISION} $< ~/eclipse.build/$@

munge:
	(cd /tmp; \
	 /bin/rm -rf tefkat; \
	 unzip ~/eclipse.build/${BRANCH}${REVISION}/tefkat.feature-${BRANCH}${REVISION}.zip; \
	 cd tefkat; \
	 scp -r plugins features lawley@shell.sf.net:tefkat/htdocs/update-site \
	 )

