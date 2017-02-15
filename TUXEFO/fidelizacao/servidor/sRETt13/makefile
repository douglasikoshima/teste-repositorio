.SILENT:

include ../../defines
include $(HOME)/TUXEFO/modulos.env
LIBFID=$(TUXDIR_DES)/$(FIDELIZACAO_PTH)/lib
BINDIR=$(TUXDIR_DES)/$(FIDELIZACAO_PTH)/bin

DIR=servidor
SERVER=sRETt13
SRCDIR= $(BASEPATH)/$(DIR)/$(SERVER)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SERVER)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SERVER)/include/$(SERVER)
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

EXTDEP=cpputil orautil fidutil sinc_pp
TUXSERVICES=SINCPP

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
           -f " $(OBJ) " -f " $(ORALIBLINK) -brtl -g"         \
           $(CSOLNK)                                       \
           -f " $(DESLIBDIRS) -L$(LIBFID) $(PLIBPATH) -L$(ORALIBDIR) "             \
           -f " $(FWLIB) $(XERCES_CLIB) " $(ORAXACMD)      \
           -s $(TUXSERVICES)
	@echo "$(EXTDEP)" > $(LIBDIR)/$(SERVER).txt 
	strip $(LIBDIR)/$(SERVER)

install:
	if ! [ -d $(BINDIR) ]; then mkdir $(BINDIR); fi
	cp $(LIBDIR)/$(SERVER) $(TUXDIR_DES)/$(FIDELIZACAO_PTH)/bin

insbin:
#	cp -f $(LIBDIR)/$(SERVER) $(SVRPATH)/bin

clean:
	rm -f $(LIBDIR)/$(SERVER)
	@-rm -f $(OBJ) 2>/dev/null
	@-cd src; rm -f $(PCPP:.pcpp=.cpp); cd .. 2>/dev/null
	@-cd src; rm -f $(PCPP:.pcpp=.lis); cd .. 2>/dev/null
