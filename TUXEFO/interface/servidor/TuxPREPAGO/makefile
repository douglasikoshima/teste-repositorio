# 
# $Id: makefile,v 1.1 2009/07/31 15:34:59 a5110702 Exp $
#
.SILENT:

include ../../defines

DIR=servidor
SERVER=TuxPREPAGO
SRCDIR= $(BASEPATH)/$(DIR)/$(SERVER)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SERVER)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SERVER)/include/$(SERVER)
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages
CONFIGDIR=$(BASEPATH)/$(DIR)/$(SERVER)/config

include $(HOME)/TUXEFO/modulos.env
BININTER=$(TUXDIR_DES)/$(INTERFACE_PTH)/bin
CONFIGINTER=$(TUXDIR_DES)/$(INTERFACE_PTH)/config

# originais, nao estao ativos. Tem que ser corrigidos
EXTDEP=TuxPrePagoBE
TUXSERVICES=TUXPREPAGOBE

ORALIBLINK=-lclntsh -lld -lm -lm -lc_r -lpthreads
ORAXACMD=-r Oracle_XA

CSOLNK=`echo $(EXTDEP)|awk '{flds=split($$0,v," ");for(i=1;i<=flds;i++)printf("-l-l%s ",v[i]);next}'`


#PCPP=lista de fontes a ser precompilado pelo PRO*C
#PCPP=a.cpp b.cpp
PCPP=
#CPP=lista de fontes a ser compilado
#CPP=c.cpp
CPP=
SRC=$(CPP) $(PCPP:.pcpp=.cpp)
#OBJ=lista de obj´s a ser linkado para gerar o servico
#OBJ=lib/a.o lib/b.o lib/c.o
OBJ=

all: $(SERVER)

#FML
messages/d.fml.h:messages/d.fml
	cd messages; MKFLDHDR messages/d.fml; cd ..

# Pre Compile
$(SRCDIR)/a.cpp:$(SRCDIR)/a.pcpp $(INCDIR)/a.hpp makefile
	$(PROC) $*.pcpp
$(SRCDIR)/b.cpp:$(SRCDIR)/b.pcpp $(INCDIR)/b.hpp $(INCDIR)/a.hpp makefile
	$(PROC) $*.pcpp

# Compile
lib/a.o:$(SRCDIR)/a.cpp $(INCDIR)/a.hpp makefile
	$(SACCPP) -Iinclude $(PINCLUDE) $(DESINCDIRS) $(CFLAGS) $(SRCDIR)/a.cpp -o $*.o
lib/b.o:$(SRCDIR)/b.cpp $(INCDIR)/b.hpp $(INCDIR)/a.hpp makefile
	$(SACCPP) -Iinclude $(PINCLUDE) $(DESINCDIRS) $(CFLAGS) $(SRCDIR)/a.cpp -o $*.o

# Link
$(SERVER): 
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(TUXDIR)/bin/buildserver -o $(LIBDIR)/$(SERVER)   \
           -f " $(OBJ) " -f " $(ORALIBLINK) -brtl"         \
           $(CSOLNK)                                       \
           -f " $(DESLIBDIRS) -L$(TUXDIR_DES)/interface/lib $(PLIBPATH) -L$(ORALIBDIR) "   \
           -f " $(FWLIB) $(XERCES_CLIB) " $(ORAXACMD)      \
           -s $(TUXSERVICES)
	@echo "$(EXTDEP)" > $(LIBDIR)/$(SERVER).txt 


install:
	if ! [ -d $(BININTER) ]; then mkdir $(TUXDIR_DES)/$(INTERFACE_PTH); mkdir $(BININTER); fi
	if ! [ -d $(CONFIGINTER) ]; then mkdir $(TUXDIR_DES)/$(INTERFACE_PTH); mkdir $(CONFIGINTER); fi
	if ! [ -d $(CONFIGINTER) ]; then mkdir $(TUXDIR_DES)/$(INTERFACE_PTH); mkdir $(CONFIGINTER)/xml; mkdir $(CONFIGINTER)/xml/app; fi
	if ! [ -d $(CONFIGINTER) ]; then mkdir $(TUXDIR_DES)/$(INTERFACE_PTH); mkdir $(CONFIGINTER)/env; mkdir $(CONFIGINTER)/env/app; fi
	cp $(LIBDIR)/$(SERVER) $(BININTER)
	@-cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SERVER) 2>/dev/null
	@-cp -f $(MSGDIR)/* $(TUXDIR_DES)/messages 2>/dev/null 
	cp -f  $(CONFIGDIR)/$(SERVER).env  $(TUXDIR_DES)/interface/config/env/app
	cp -f  $(CONFIGDIR)/$(SERVER).xml  $(TUXDIR_DES)/interface/config/xml/app
clean:
	rm -f $(LIBDIR)/$(SERVER)
	@-rm -f $(OBJ) 2>/dev/null
