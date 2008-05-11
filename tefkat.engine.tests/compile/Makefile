
all: run

run: simple compiler

simple:
	java -jar ../../tefkat.standalone/Tefkat_2.1.0.1.jar \
		-save \
		-statistics \
		-mapURI http:///TefkatRuntime-1.0.ecore \
		        file:${PWD}/../../tefkat.engine/src/TefkatRuntime.ecore \
		-mapURI http://CompilerTracking.ecore \
		        file:${PWD}/CompilerTracking.ecore \
		-transformation file:${PWD}/compile.qvt \
		-source file:${PWD}/input.qvt \
		-target file:${PWD}/output.xmi \
		-trace  file:${PWD}/trace.xmi

compiler:
	java -jar ../../tefkat.standalone/Tefkat_2.1.0.1.jar \
		-save \
		-statistics \
		-mapURI http:///TefkatRuntime-1.0.ecore \
		        file:${PWD}/../../tefkat.engine/src/TefkatRuntime.ecore \
		-mapURI http://CompilerTracking.ecore \
		        file:${PWD}/CompilerTracking.ecore \
		-transformation file:${PWD}/compile.qvt \
		-source file:${PWD}/compile.qvt \
		-target file:${PWD}/compiled.xmi \
		-trace  file:${PWD}/trace.xmi

standalone:
	cd ../../tefkat.standalone; ./package.sh

