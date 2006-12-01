
VIS=-vis tT -layout
VIS=

all: tests

tests: \
	tefkat.tefkat \
	tefkat_copy.tefkat \
	tefkat_arith.tefkat \
	tefkat_nested.tefkat \
	tefkat_test.tefkat \
	tefkat_test_map.tefkat \
	tefkat_test_strat.tefkat

%.tefkat: %.xml
	java -jar ../../tefkat.standalone/Tefkat_2.1.0.1.jar \
	    ${ARGS} ${VIS} \
	    -mapURI platform:/resource/transformation/ file:/Users/lawley/src/Tefkat-svn/tefkat.engine.tests/transformation/ \
	    -mapURI http:/// file:/Users/lawley/src/Tefkat-svn/tefkat.engine.tests/transformation/models/ \
	    -conf $<
