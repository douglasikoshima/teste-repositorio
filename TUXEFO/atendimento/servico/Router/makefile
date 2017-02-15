#Compile Router shared objects
#This makefile generate a shared object file
#
#Module Name:
#	 makefile
#
#Abstract:
#	 Compile ROUTER Core
#
#Author:
#	 Ivan Mentone 2004-06-30
#
#Environment:
#	 Router Core
#
#Revision History:
#	 2004-07-07 - Ivan Mentone - Remove ORALIBDIR local envr
#	 2004-07-12 - Ivan Mentone - Add new module RouterErrorImpl.cpp
#	 2004-07-14 - Ivan Mentone - Modify RouterDOMNODELoopImpl.cpp to RouterLoopImpl.cpp
#				Add new module RouterAssemble.cpp
#				Add new module RouterBaseLoopImpl.cpp
#				Add new module RouterSQLImpl.cpp
#	 2004-07-18 - Ivan Mentone - Add new module RouterExpressionImpl.cpp
#	 2004-07-26 - Ivan Mentone - Correct multiple depends
#	 2004-08-23 - Ivan Mentone - Rewrite to eliminate multiple depends
#   2004-08-26 - Ivan Mentone - Add new module RouterLogImpl.cpp
#
#

.SILENT:

include $(HOME)/TUXEFO/modulos.env
LIBATD=$(TUXDIR_DES)/$(ATENDIMENTO_PTH)/lib

#Fix Section
TUXINCDIR=$(TUXDIR)/include
export CFLAGS=-L$(ORALIBDIR) -L$(PLIBPATH) -L$(FWDIR)/lib
export CC='xlC -qrtti'
ORAINCDIR=-I$(ORACLE_HOME)/precomp/public
XERCES_CLIB=-lxerces-c
AUXOBJ=$(CUSTOBJ)
FWLIB=-ltuxfw
CC=$(SACCPP) -I. -Iinclude $(PINCLUDE) $(PLIBPATH) -G
SRC2OBJ=$(CC) -c
OBJ2BIN=$(CC) -o
PROC=proc include=$(TUXINCDIR) include=$(PWD)/include/ code=cpp cpp_suffix=cpp parse=none

all: Router

build: Router install

install:
	echo Installing lib/libRouter.so
	if ! [ -d $(LIBATD) ]; then mkdir $(TUXDIR_DES)/$(ATENDIMENTO_PTH); mkdir $(LIBATD); fi
	rm -f $(LIBATD)/libRouter.so
	cp lib/libRouter.so $(LIBATD)
#	cp -f include/*.h* $(TUXDIR_DES)/include/$(SUBDIR) 

clean:
	echo Clear all Objects and Binary files
	rm -rf lib >/dev/null
	rm -rf src/RouterCursorImplAux.cpp src/RouterCursorImplAux.lis >/dev/null
	rm -rf src/RouterSvcManAux.cpp src/RouterSvcManAux.lis >/dev/null
	rm -rf src/RouterStepImplAux.cpp src/RouterStepImplAux.lis >/dev/null

Router:	 AuxDOMImpl.o RouterAccumImpl.o RouterCodeImpl.o RouterFunctionCallImpl.o \
		RouterImpl.o RouterLibImpl.o RouterLibManImpl.o RouterStepImpl.o RouterSvc.o \
		RouterSvcMan.o RouterTaskImpl.o RouterTraceImpl.o RouterTransformImpl.o \
		TuxSvc.o RouterAux.o RouterVarNameImpl.o RouterLoopImpl.o RouterStepStockImpl.o \
		RouterCursorImpl.o RouterClientSupportImpl.o RouterXMLImpl.o RouterStreamImpl.o \
		RouterControl.o RouterConstants.o RouterExternCallImpl.o RouterCommandImpl.o \
		RouterErrorImpl.o RouterAssembleImpl.o RouterBaseLoopImpl.o RouterSQLImpl.o \
		RouterExpressionImpl.o RouterPatrolImpl.o RouterDOMImpl.o Token.o \
		RouterCursorImplAux.o RouterSvcManAux.o RouterStepImplAux.o RouterLogImpl.o
	echo Building library lib/libRouter.so
	$(CC) -o lib/libRouter.so lib/AuxDOMImpl.o lib/RouterAccumImpl.o lib/RouterCodeImpl.o \
				lib/RouterFunctionCallImpl.o lib/RouterImpl.o lib/RouterLibImpl.o \
				lib/RouterLibManImpl.o lib/RouterStepImpl.o lib/RouterSvc.o \
				lib/RouterSvcMan.o lib/RouterTaskImpl.o lib/RouterTraceImpl.o \
				lib/RouterTransformImpl.o lib/TuxSvc.o lib/RouterAux.o \
				lib/RouterVarNameImpl.o lib/RouterLoopImpl.o lib/RouterStepStockImpl.o \
				lib/RouterCursorImpl.o lib/RouterClientSupportImpl.o lib/RouterXMLImpl.o \
				lib/RouterStreamImpl.o lib/RouterControl.o lib/RouterConstants.o \
				lib/RouterExternCallImpl.o lib/RouterCommandImpl.o lib/RouterErrorImpl.o \
				lib/RouterAssembleImpl.o lib/RouterBaseLoopImpl.o lib/RouterSQLImpl.o \
				lib/RouterExpressionImpl.o lib/RouterPatrolImpl.o lib/RouterDOMImpl.o \
				lib/Token.o lib/RouterCursorImplAux.o lib/RouterSvcManAux.o lib/RouterStepImplAux.o \
				lib/RouterLogImpl.o $(FWLIB) $(XERCES_CLIB)
	if ! [ -d $(LIBATD) ]; then mkdir $(TUXDIR_DES)/$(ATENDIMENTO_PTH); mkdir $(LIBATD); fi
	rm -f $(LIBATD)/libRouter.so
	cp lib/libRouter.so $(LIBATD)

Token.o: $(@.o=.c)
	echo Making  $(@:.o=.c)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.c) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

AuxDOMImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterAccumImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterCodeImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterFunctionCallImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterLibImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterLibManImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterStepImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterSvc.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterSvcMan.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterTaskImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterTraceImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterTransformImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

TuxSvc.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterAux.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterVarNameImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterLoopImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterStepStockImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterCursorImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterClientSupportImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterXMLImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterStreamImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterControl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterConstants.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterExternCallImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterCommandImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterErrorImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterAssembleImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterBaseLoopImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterSQLImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterExpressionImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterPatrolImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterDOMImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterLogImpl.o: $(@.o=.cpp)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterCursorImplAux.cpp: $(@.cpp=.pcpp)
	echo Making  $(@:.cpp=.pcpp)
	$(PROC) src/$(@:.cpp=.pcpp)

RouterSvcManAux.cpp: $(@.cpp=.pcpp)
	echo Making  $(@:.cpp=.pcpp)
	$(PROC) src/$(@:.cpp=.pcpp)

RouterStepImplAux.cpp: $(@.cpp=.pcpp)
	echo Making  $(@:.cpp=.pcpp)
	$(PROC) src/$(@:.cpp=.pcpp)

RouterCursorImplAux.o: RouterCursorImplAux.cpp
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterSvcManAux.o: RouterSvcManAux.cpp
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

RouterStepImplAux.o: RouterStepImplAux.cpp
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) -DCVS src/$(@:.o=.cpp) -o lib/$@ $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR)

#depends
