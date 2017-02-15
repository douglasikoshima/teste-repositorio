# 
# $Id: makefile,v 1.1.2.1 2007/07/23 20:23:57 akurak Exp $
#
.SILENT:

#include ../../defines

BASEPATH=$(HOME)/TUXEWS/vol
#CFLAGS=-cLINK=-G -o
CFLAGS=-cLINK=-G
TUXINCDIR=$(TUXDIR)/include
PROC=proc include=$(TUXINCDIR) code=cpp cpp_suffix=cpp parse=none
DESINCDIRS=-I$(TUXDIR_DES)/include
DESLIBDIRS=-L$(TUXDIR_DES)/lib
MKFLDHDR=mkfldhdr32

#minhas inclusoes
XERCES_CLIB=-L$(XERCESPATH)/xerces-c

FWLIB=-ltuxfw

DIR=servidor
SERVER=srvSMS
SRCDIR= $(BASEPATH)/$(DIR)/$(SERVER)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SERVER)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SERVER)/include/$(SERVER)
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

# originais, nao estao ativos. Tem que ser corrigidos
EXTDEP=SMS SMSENVIA SMSSTATUS
TUXSERVICES=SMSENVIA,SMSSTATUS

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
           -f " $(DESLIBDIRS) $(PLIBPATH) -L$(ORALIBDIR) "   \
           -f " $(FWLIB) $(XERCES_CLIB) " $(ORAXACMD)      \
           -s $(TUXSERVICES)
	@echo "$(EXTDEP)" > $(LIBDIR)/$(SERVER).txt 


install:
	cp $(LIBDIR)/$(SERVER) $(TUXDIR_DES)/bin
	@-cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SERVER) 2>/dev/null
	@-cp -f $(MSGDIR)/* $(TUXDIR_DES)/messages 2>/dev/null 

clean:
	rm -f $(LIBDIR)/$(SERVER)
	@-rm -f $(OBJ) 2>/dev/null
