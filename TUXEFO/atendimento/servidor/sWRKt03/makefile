.SILENT:

include ../../defines


include $(HOME)/TUXEFO/modulos.env
BINATD=$(TUXDIR_DES)/$(ATENDIMENTO_PTH)/bin
CFGATD=$(TUXDIR_DES)/$(ATENDIMENTO_PTH)/config

DIR=servidor
SERVER=sWRKt03
SRCDIR= $(BASEPATH)/$(DIR)/$(SERVER)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SERVER)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SERVER)/include/$(SERVER)
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages
CONFIGDIR=$(BASEPATH)/$(DIR)/$(SERVER)/config
LIBATD=$(TUXDIR_DES)/$(ATENDIMENTO_PTH)/lib


# originais, nao estao ativos. Tem que ser corrigidos
EXTDEP=REGCONTATOER
TUXSERVICES=REGCONTATOER

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
           -f " $(DESLIBDIRS) $(PLIBPATH) -L$(ORALIBDIR) -L$(LIBATD) "   \
           -f " $(FWLIB) $(XERCES_CLIB) " $(ORAXACMD)      \
           -s $(TUXSERVICES)
	@echo "$(EXTDEP)" > $(LIBDIR)/$(SERVER).txt 


install:
#	cp $(LIBDIR)/$(SERVER) $(TUXDIR_DES)/bin
	if ! [ -d $(BINATD) ]; then mkdir $(TUXDIR_DES)/$(ATENDIMENTO_PTH); mkdir $(BINATD); fi
	rm -f $(BINATD)/$(SERVER)
	cp $(LIBDIR)/$(SERVER) $(BINATD)
	if ! [ -d $(CFGATD) ]; then mkdir $(CFGATD); mkdir $(CFGATD)/env; mkdir $(CFGATD)/env/app; fi
	cp -f  $(CONFIGDIR)/$(SERVER).env  $(TUXDIR_DES)/$(ATENDIMENTO_PTH)/config/env/app          
	if ! [ -d $(CFGATD)/xml ]; then mkdir $(CFGATD)/xml; mkdir $(CFGATD)/xml/app; fi
	cp -f  $(CONFIGDIR)/$(SERVER).xml  $(TUXDIR_DES)/$(ATENDIMENTO_PTH)/config/xml/app

clean:
	rm -f $(LIBDIR)/$(SERVER)
	@-rm -f $(OBJ) 2>/dev/null
