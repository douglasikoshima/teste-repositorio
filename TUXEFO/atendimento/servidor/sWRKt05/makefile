.SILENT:

DIR=server
SERVER=sWRKt05
CONFIGDIR=./config

#Variable Section
BIN=sWRKt05
CUSTOBJ=
CUSTSO=COREWORKFLOW
TUXSERVICES=COREWORKFLOW

include $(HOME)/TUXEFO/modulos.env
BINATD=$(TUXDIR_DES)/$(ATENDIMENTO_PTH)/bin
CFGATD=$(TUXDIR_DES)/$(ATENDIMENTO_PTH)/config
LIBATD=$(TUXDIR_DES)/$(ATENDIMENTO_PTH)/lib

#Fix Section
export CFLAGS=-L$(ORALIBDIR) -L/opt/esac/dvfo01rt/staging/lib -L/opt/tuxedo/current/lib -L/usr/lib -L/usr/local/lib -L/home/druida/imentone/libs -L../so -L$(LIBATD)
export CC='xlC -qrtti'
ORAINCDIR=-I$(ORACLE_HOME)/precomp/public
ORAXACMD=-r Oracle_XA
XERCES_CLIB=-l-lxerces-c
FWLIB=-f "-ltuxfw"
CC=$(SACCPP) $(PINCLUDE) $(PLIBPATH)
BUILDSERV=buildserver -o
CSOLNK=`echo $(CUSTSO)|awk '{flds=split($$0,v," ");for(i=1;i<=flds;i++)printf("-l-l%s ",v[i]);next;}'`

all: $(BIN)
$(BIN):
	echo Building $(BIN) Server...
	if ! [ -d bin ]; then mkdir bin; fi
	$(BUILDSERV) bin/$(BIN) -brtl -f " $(CUSTOBJ)" $(CSOLNK) $(FWLIB) $(XERCES_CLIB) $(ORAXACMD) -s $(TUXSERVICES)

install:
	echo Installing $(BIN)...
	if ! [ -d $(BINATD) ]; then mkdir $(TUXDIR_DES)/$(ATENDIMENTO_PTH); mkdir $(BINATD); fi
	rm -f $(BINATD)/$(BIN)
	cp bin/$(BIN) $(BINATD)
#	cp bin/$(BIN) $(TUXDIR_DES)/bin/$(BIN)
	if ! [ -d $(CFGATD) ]; then mkdir $(CFGATD); mkdir $(CFGATD)/env; mkdir $(CFGATD)/env/app; fi
	cp -f  $(CONFIGDIR)/$(SERVER).env  $(TUXDIR_DES)/$(ATENDIMENTO_PTH)/config/env/app
	if ! [ -d $(CFGATD)/xml ]; then mkdir $(CFGATD)/xml; mkdir $(CFGATD)/xml/app; fi
	cp -f  $(CONFIGDIR)/$(SERVER).xml  $(TUXDIR_DES)/$(ATENDIMENTO_PTH)/config/xml/app
	
clean:
	echo Cleaning $(BIN)...
	rm -f bin/$(BIN)
