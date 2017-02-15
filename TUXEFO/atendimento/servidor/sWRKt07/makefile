.SILENT:

#Variable Section
BIN=sWRKt07
CUSTOBJ=
CUSTSO=COREWORKFLOW RESPCLIENTE WFREPACAO WFREABPROC MarcarSusp WFINIACAO WFFECACAO ENCACAO WFDTRACAO ATDALTERPROT
TUXSERVICES=RESPCLIENTE,REPACAO,REABPROC,MARCARSUSP,INIACAO,FECHAMENTO,ENCACAO,DTRACAO

include $(HOME)/TUXEFO/modulos.env
BINATD=$(TUXDIR_DES)/$(ATENDIMENTO_PTH)/bin
LIBATD=$(TUXDIR_DES)/$(ATENDIMENTO_PTH)/lib

#Fix Section
export CFLAGS=-L$(ORALIBDIR) -L/opt/esac/dvfo01rt/staging/lib -L/opt/tuxedo/current/lib -L/usr/lib -L/usr/local/lib -L../so -L$(LIBATD)
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

clean:
	echo Cleaning $(BIN)...
	rm -f bin/$(BIN)
