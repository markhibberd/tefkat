
all: run

run:
	java -jar ../../tefkat.standalone/Tefkat_2.1.0.1.jar \
		-save \
		-mapURI http:///TefkatRuntime-1.0.ecore \
		        file:${PWD}/TefkatRuntime-1.0.ecore \
		-transformation compile.qvt \
		-source input.qvt \
		-target file:${PWD}/code.xmi \
		-trace  trace.xmi

